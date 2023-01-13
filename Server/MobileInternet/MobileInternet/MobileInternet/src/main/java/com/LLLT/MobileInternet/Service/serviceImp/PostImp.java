// Post Implement
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */
/*      啊啊啊啊啊啊啊啊 哦哦哦哦哦哦哦哦哦
 *_______________#########_______________________
 *______________############_____________________
 *______________#############____________________
 *_____________##__###########___________________
 *____________###__######_#####__________________
 *____________###_#######___####_________________
 *___________###__##########_####________________
 *__________####__###########_####_______________
 *________#####___###########__#####_____________
 *_______######___###_########___#####___________
 *_______#####___###___########___######_________
 *______######___###__###########___######_______
 *_____######___####_##############__######______
 *____#######__#####################_#######_____
 *____#######__##############################____
 *___#######__######_#################_#######___
 *___#######__######_######_#########___######___
 *___#######____##__######___######_____######___
 *___#######________######____#####_____#####____
 *____######________#####_____#####_____####_____
 *_____#####________####______#####_____###______
 *______#####______;###________###______#________
 *________##_______####________####______________
 */
package com.LLLT.MobileInternet.Service.serviceImp;

import com.LLLT.MobileInternet.Entity.Comment;
import com.LLLT.MobileInternet.Entity.Post;
import com.LLLT.MobileInternet.Service.PostService;
import com.LLLT.MobileInternet.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.CodecRegistryProvider;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PostService")
public class PostImp implements PostService {

    private final MongoTemplate mongoTemplate;
    private final UserService userService;
    public PostImp(MongoTemplate mongoTemplate, UserService userService) {
        this.mongoTemplate = mongoTemplate;
        this.userService = userService;
    }

    // 创建新帖子函数
    // Last Modified by SeeChen Lee @ 10-Jan-2023 17:42
    @Override
    public String publishPost(String holderId, String postTitle, String postContent) {

        Post newPost = new Post(holderId, postTitle, postContent);

        List<String>  likeUser;
        List<Comment> commentInfo;
        Integer       likeNum;

        String postId = "P" + new ObjectId().toString();

        likeUser      = List.of();
        commentInfo   = List.of();
        likeNum       = 0;

        newPost.setPostId(postId);
        newPost.setLikeUser(likeUser);
        newPost.setCommentInfo(commentInfo);
        newPost.setLikeNum(likeNum);

        mongoTemplate.insert(newPost);

        // 更新用户数据库 添加帖子 ID
        userService.publishPost(holderId, postId);

        return postId;
    }

    // 用于帖子被点赞 返回 True 表示已经点赞 False 表示点赞失败
    // Last Modified by ViHang Tan @ 11-Jan-2023 17:00
    @Override
    public Boolean likePost(String postId, String userId) {

        Query query      = new Query(Criteria.where("postId").is(postId));
        Post  targetPost = mongoTemplate.findOne(query, Post.class, "post");

        assert targetPost != null;
        targetPost.setLikeNum(targetPost.getLikeNum() + 1);
        targetPost.getLikeUser().add(userId);

        Update update = new Update();
        update.set("likeUser", targetPost.getLikeUser()).set("likeNum", targetPost.getLikeNum());

        mongoTemplate.upsert(query, update, Post.class, "post");

        userService.likePost(userId,postId);

        return true;
    }

    // 用户评论帖子
    // Last Modified by SeeChen Lee @ 12-Jan-2023 11:18
    @Override
    public Boolean commentPost(String postId, String userId, String commentContent) {

        Query query       = new Query(Criteria.where("postId").is(postId));
        Post  currentPost = mongoTemplate.findOne(query, Post.class, "post");

        Comment newComment = new Comment(userId, commentContent);

        assert currentPost != null;
        currentPost.getCommentInfo().add(newComment);

        mongoTemplate.upsert(query, new Update().set("commentInfo", currentPost.getCommentInfo()), Post.class, "post");

        return true;
    }

    // 返回帖子内容
    // Last Modified by SeeChen Lee @ 10-Jan-2023 19:57
    @Override
    public Post postContent(String postId) {

        Post postContent = new Post();
        Post findPost;

        Query query = new Query(Criteria.where("postId").is(postId));

        findPost = mongoTemplate.findOne(query, Post.class, "post");

        if (findPost != null) {

            BeanUtils.copyProperties(findPost, postContent);
        } else {

            postContent.setPostContent("PostNotFound");
        }

        return postContent;
    }

    // 从最后一条记录返回帖子 适用场景 home page
    // Last Modified by SeeChen Lee @ 12-Jan-2023 16:17
    @Override
    public List<Post> newPost(Integer requestNum) {

        long skipNum = mongoTemplate.findAll(Post.class, "post").size() - (20L * requestNum);

        Query query = new Query(Criteria.where("").is("")).skip(skipNum).limit(20);

        return mongoTemplate.find(query, Post.class, "post");
    }
}
