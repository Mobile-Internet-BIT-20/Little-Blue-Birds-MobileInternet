// User Implement
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */

//  读书使我快乐
//                .-~~~~~~~~~-._       _.-~~~~~~~~~-.
//            __.'              ~.   .~              `.__
//          .'//  但移动互联真的好难 \./                  \\`.
//        .'//                     |                     \\`.
//      .'// .-~"""""""~~~~-._     |     _,-~~~~"""""""~-. \\`.
//    .'//.-"                 `-.  |  .-'                 "-.\\`.
//  .'//______.============-..   \ | /   ..-============.______\\`.
//.'______________________________\|/______________________________`.

package com.LLLT.MobileInternet.Service.serviceImp;

import com.LLLT.MobileInternet.Entity.User;
import com.LLLT.MobileInternet.Entity.UserPublicInformation;
import com.LLLT.MobileInternet.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

// 接口实现类
@Service("UserService")
public class UserImp implements UserService {

    private final MongoTemplate mongoTemplate;
    public UserImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // 用于更新用户帖子
    // Modified by SeeChen Lee @ 10-Jan-2023 17:28
    @Override
    public void publishPost(String userId, String postId) {

        Query query = new Query(Criteria.where("userId").is(userId));

        User publishUser = mongoTemplate.findOne(query, User.class, "user");

        assert publishUser != null;
        publishUser.getUserPost().add(postId);

        Update update = new Update();
        update.set("userPost", publishUser.getUserPost());

        mongoTemplate.upsert(query, update, User.class, "user");

    }


    // 新用户保存函数
    // Modified by SeeChen Lee @ 11-Jan-2023 08:53
    @Override
    public String createUser(String userEmail, String userPass) {

        String userName, userId, dayOfBirth, userIntro;
        Integer userSexIndex = 0;   // 默认未设置
        List<String> userPost = List.of();

        List<String> likePost = List.of();

        List<UserPublicInformation> userFollower  = List.of();
        List<UserPublicInformation> userFollowing = List.of();

        // 新建一个用户
        User newUser = new User(userEmail, userPass);

        // 下列为默认设置 可以在创建用户完成后重新设置
        userId     = "U" + new ObjectId();
        userName   = "用户" + userId;
        dayOfBirth = "1900-01-01";
        userIntro  = "~ 这个小可爱很神秘，不想介绍自己 ~ ^_^";

        // 进行设置
        newUser.setUserId(userId);
        newUser.setUserName(userName);
        newUser.setDayOfBirth(dayOfBirth);
        newUser.setUserIntro(userIntro);
        newUser.setUserSexIndex(userSexIndex);

        newUser.setUserFollower(userFollower);
        newUser.setUserFollowing(userFollowing);

        newUser.setLikePost(likePost);

        // 设置帖子数据 初始为空
        newUser.setUserPost(userPost);

        mongoTemplate.insert(newUser);

        return userId;
    }

    // 用户登录函数
    // Last Modified by SeeChen Lee @ 10-Jan-2023 13:58
    @Override
    public String userLogin(String userEmail, String userPass) {

        if (!emailExists(userEmail)) {

            return "UserNotExists";
        } else {

            Query query = new Query(Criteria.where("email").is(userEmail));

            User loginUser = mongoTemplate.findOne(query, User.class, "user");

            assert loginUser != null;
            if (userPass.equals(loginUser.getUserPass())) {

                return loginUser.getUserId();
            } else {

                return "WrongPassword";
            }
        }
    }

    // 用户更改邮箱
    // Last Modified by SeeChen Lee @ 11-Jan-2023 07:57
    @Override
    public String updateEmail(String userId, String newEmail, String userPass) {

        Query query      = new Query(Criteria.where("userId").is(userId));
        User  updateUser = mongoTemplate.findOne(query, User.class, "user");

        assert updateUser != null;
        if (!updateUser.getUserPass().equals(userPass)) {

            return "WrongPass";
        } else {

            Update update = new Update();
            update.set("email", newEmail);

            mongoTemplate.upsert(query, update, User.class, "user");

            return "Updated";
        }
    }

    // 用户修改密码
    // Last Modified by SeeChen Lee @ 11-Jan-2023 07:36
    @Override
    public String updatePassword(String userId, String oldPass, String newPass) {

        Query query = new Query(Criteria.where("userId").is(userId));
        User updateUser = mongoTemplate.findOne(query, User.class, "user");

        assert updateUser != null;
        if (!updateUser.getUserPass().equals(oldPass)) {

            return "WrongPassword";
        } else {

            Update update = new Update();
            update.set("userPass", newPass);

            mongoTemplate.upsert(query, update, User.class, "user");

            return "Updated";
        }
    }

    // 返回用户的个人资料 应用场景例子 用户的主页面
    // Last Modified by SeeChen @ 10-Jan-2023 11:46
    @Override
    public User getUserInfo(String userId) {

        // 定义一开始为空值
        User userInfo = new User();
        User findUser;

        Query query = new Query(Criteria.where("userId").is(userId));

        // 从 user 数据库里面搜索 userId
        findUser = mongoTemplate.findOne(query, User.class, "user");

        // 判断返回值是否为 null 为 null 表示没有此用户
        if (findUser != null) {

            // 将找到的值赋值给返回值变量
            BeanUtils.copyProperties(findUser, userInfo);
        } else {

            // 定义未找到的值的用户名未 UserNotFound
            userInfo.setUserName("UserNotFound");
        }

        return userInfo;
    }

    // 判断当前邮箱是否已经被注册 True 表示已被注册 False 表示尚未被注册
    // Last Modified by SeeChen Lee @ 10-Jan-2023 00:00
    @Override
    public Boolean emailExists(String email) {

        Query query   = Query.query(Criteria.where("email").is(email));
        User  isEmpty = mongoTemplate.findOne(query, User.class, "user");

        // 为 null 表示当前邮箱未被使用
        return isEmpty != null;
    }

    // 用户关注其它用户
    // Last Modified by SeeChen Lee @ 11-Jan-2023 09:25
    @Override
    public Boolean userFollow(String followerId, String targetId) {

        // 自己不能关注自己
        if (followerId.equals(targetId)) return false;

        Query followerQuery = new Query(Criteria.where("userId").is(followerId));
        Query targetQuery   = new Query(Criteria.where("userId").is(targetId));

        User followerUser = mongoTemplate.findOne(followerQuery, User.class, "user");
        User targetUser   = mongoTemplate.findOne(targetQuery  , User.class, "user");

        if (followerUser != null && targetUser != null) {

            followerUser.getUserFollowing().add(new UserPublicInformation(targetUser.getUserId(), targetUser.getUserName()));
            targetUser.getUserFollower().add(new UserPublicInformation(followerUser.getUserId(), followerUser.getUserName()));

            mongoTemplate.upsert(followerQuery, new Update().set("userFollowing", followerUser.getUserFollowing()), User.class, "user");
            mongoTemplate.upsert(targetQuery  , new Update().set("userFollower" , targetUser.getUserFollower()) , User.class, "user");

            return true;
        } else {

            return false;
        }
    }

    // 用于更新用户的基本资料 ( 非保密性 )
    // Last Modified by ViHang Tan @ 10-Jan-2023 19:56
    @Override
    public Boolean updateUser(User updateUser) {

        Query query = new Query(Criteria.where("userId").is(updateUser.getUserId()));

        Update update = new Update();

        update.set(  "userName"   , updateUser.getUserName())
                .set("dayOfBirth" , updateUser.getDayOfBirth())
                .set("usrSexIndex", updateUser.getUserSexIndex())
                .set("userIntro"  , updateUser.getUserIntro());

        mongoTemplate.upsert(query, update, User.class, "user");

        return true;
    }

    // 用于删除用户账号 返回 True 表示已经成功删除 False 表示删除失败
    // Last Modified by SeeChen Lee @ 11-Jan-2023 07:00
    @Override
    public Boolean userDelete(String userEmail, String userPass, String userId) {

        Query query      = new Query(Criteria.where("userId").is(userId));
        User  deleteUser = mongoTemplate.findOne(query, User.class, "user");

        assert deleteUser != null;
        String correctEmail = deleteUser.getEmail();
        String correctPass  = deleteUser.getUserPass();

        if (correctEmail.equals(userEmail) && correctPass.equals(userPass)) {

            // 删除账户
            mongoTemplate.remove(query, User.class, "user");

            return true;
        }

        return false;
    }

    // 用于用户点赞 返回 True 表示已经点赞 False 表示点赞失败
    // Last Modified by SeeChen Lee @ 12-Jan-2023 11:12
    @Override
    public Boolean likePost(String userId, String postId) {

        Query query    = new Query(Criteria.where("userId").is(userId));
        User  likeUser = mongoTemplate.findOne(query, User.class, "user");

        assert likeUser != null;
        likeUser.getLikePost().add(postId);

        mongoTemplate.upsert(query, new Update().set("likePost", likeUser.getLikePost()), User.class, "user");

        return true;
    }

    @Override
    public String getUserName(String userId) {

        Query query = new Query(Criteria.where("userId").is(userId));
        User  user  = mongoTemplate.findOne(query,User.class);

        return user.getUserName();
    }
}
