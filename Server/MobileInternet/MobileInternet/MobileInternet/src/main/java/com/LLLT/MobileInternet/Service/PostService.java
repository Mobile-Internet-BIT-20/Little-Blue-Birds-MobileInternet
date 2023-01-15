// Post Service Interface
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */
package com.LLLT.MobileInternet.Service;

import com.LLLT.MobileInternet.Entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    public String     publishPost(String holderId, String postTitle, String postContent);   // 用户发布帖子
    public String     publisherId(String postId);                                           // 返回发布者的 ID

    public Boolean    likePost(String postId, String userId);                               // 用户点赞帖子

    public Boolean    editPost(String postId, String newPostTitle, String newPostContent);  // 用户编辑帖子

    public Boolean    commentPost(String postId, String userId, String commentContent);     // 用户评论帖子

    public Post       postContent(String postId);                                           // 获取帖子的内容
    public List<Post> allPost(Integer requestNum);                                          // 获取新帖子
}
