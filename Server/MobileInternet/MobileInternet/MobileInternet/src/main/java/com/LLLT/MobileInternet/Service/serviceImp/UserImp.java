//  读书使我快乐
//                .-~~~~~~~~~-._       _.-~~~~~~~~~-.
//            __.'              ~.   .~              `.__
//          .'//  但移动互联真的好难 \./                  \\`.
//        .'//                     |                     \\`.
//      .'// .-~"""""""~~~~-._     |     _,-~~~~"""""""~-. \\`.
//    .'//.-"                 `-.  |  .-'                 "-.\\`.
//  .'//______.============-..   \ | /   ..-============.______\\`.
//.'______________________________\|/______________________________`.

/*
 *  com.LLLT.MobileInternet.Service.serviceImp.UserImp.java
 *  用户服务的代码内容
 *  Author : SeeChen Lee, ViHang Tan
 *  Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */

package com.LLLT.MobileInternet.Service.serviceImp;

import com.LLLT.MobileInternet.Entity.Post;
import com.LLLT.MobileInternet.Entity.Response;
import com.LLLT.MobileInternet.Entity.User;
import com.LLLT.MobileInternet.Service.PostService;
import com.LLLT.MobileInternet.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("UserService")
public class UserImp implements UserService {

    private final MongoTemplate mongoTemplate;
    public UserImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Response userRegister(String userEmail, String userPass) {

        /*
         *  userRegister
         *  用户注册函数
         *  参数:
         *      userEmail : 用户注册邮箱
         *      userPass  : 用户注册密码, 通过前端进行 MD5 加密后
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 18-Jan-2023 02:40
         * ---------------- 修改内容 -----------------------------------------
         *  18-Jan-2023 02:40
         *      1. 修改了返回的类型
         *      2. 增加了一些变量
         */

        String       userId, userPhoto, userName, userIntro, dayOfBirth;
        int          userSexIndex;
        List<String> userPost, userFollower, userFollowing, userLike, userFav;
        String       joinDay;

        userId        = "U" + new ObjectId();

        userPhoto     = "None";
        userName      = "用户 " + userId;
        userIntro     = "~ 这位小可爱想保留自己的神秘感, 不想介绍自己哦 ~";
        dayOfBirth    = "1900-01-01";
        userSexIndex  = 0;

        userPost      = List.of();
        userFollower  = List.of();
        userFollowing = List.of();

        userLike      = List.of();
        userFav       = List.of();

        joinDay       = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Response response = new Response();

        if (emailExists(userEmail)) {

            response.setVerifyCode(-1);
            response.setResponseMessage("EmailExists");
        } else {

            User newUser  = new User(userEmail, userPass);

            newUser.setUserId(userId);
            newUser.setUserEmail(userEmail);
            newUser.setUserPass(userPass);

            newUser.setUserPhoto(userPhoto);
            newUser.setUserName(userName);
            newUser.setUserIntro(userIntro);
            newUser.setDayOfBirth(dayOfBirth);
            newUser.setUserSexIndex(userSexIndex);

            newUser.setUserPost(userPost);
            newUser.setUserFollower(userFollower);
            newUser.setUserFollowing(userFollowing);

            newUser.setUserLike(userLike);
            newUser.setUserFav(userFav);

            newUser.setJoinDay(joinDay);

            response.setVerifyCode(1);
            response.setResponseMessage(userId);

            mongoTemplate.insert(newUser);
        }

        return response;
    }

    @Override
    public Response userLogin(String userEmail, String userPass) {

        /*
         *  userLogin
         *  用户登录函数
         *  参数:
         *      userEmail : 用户注册邮箱
         *      userPass  : 用户注册密码, 通过前端进行 MD5 加密后
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 18-Jan-2023 02:40
         * ---------------- 修改内容 -----------------------------------------
         *  18-Jan-2023 02:40
         *      1. 修改了返回的类型
         *      2. 增加了一些变量
         */

        Response response = new Response();

        if (!emailExists(userEmail)) {

            response.setVerifyCode(-2);
            response.setResponseMessage("UserNotExists");
        } else {

            Query loginQuery = new Query(Criteria.where("userEmail").is(userEmail));
            User  loginUser  = mongoTemplate.findOne(loginQuery, User.class, "user");

            assert loginUser != null;
            if (loginUser.getUserPass().equals(userPass)) {

                response.setVerifyCode(2);
                response.setResponseMessage(loginUser.getUserId());
            } else {

                response.setVerifyCode(-3);
                response.setResponseMessage("WrongPassword");
            }

        }

        return response;
    }

    @Override
    public Response userDelete(String userId, String userEmail, String userPass) {

        /*
         *  userDelete
         *  用户删除账号函数
         *  参数:
         *      userId    : 用户 ID
         *      userEmail : 用户注册邮箱
         *      userPass  : 用户注册密码, 通过前端进行 MD5 加密后
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 18-Jan-2023 20:48
         * ---------------- 修改内容 -----------------------------------------
         *  18-Jan-2023 20:48
         *      1. 修改了返回的类型
         */

        Response response = new Response();

        Query deleteQuery = new Query(Criteria.where("userId").is(userId));
        User  userDelete  = mongoTemplate.findOne(deleteQuery, User.class, "user");

        assert userDelete != null;
        String correctEmail = userDelete.getUserEmail();
        String correctPass  = userDelete.getUserPass();

        if (userEmail.equals(correctEmail) && userPass.equals(correctPass)) {

            mongoTemplate.remove(deleteQuery, User.class, "user");

            response.setVerifyCode(3);
            response.setResponseMessage("DeleteSuccess");
        } else if (!userEmail.equals(correctEmail)) {

            response.setVerifyCode(-4);
            response.setResponseMessage("WrongEmail");
        } else if (!userPass.equals(correctPass)) {

            response.setVerifyCode(-3);
            response.setResponseMessage("WrongPassword");
        }

        return response;
    }

    @Override
    public Response getUserAllInfo(String userId) {

        /*
         *  getUserAllInfo
         *  获取用户的所有信息
         *  参数:
         *      userId    : 用户 ID
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 19-Jan-2023 16:50
         * ---------------- 修改内容 -----------------------------------------
         *  19-Jan-2023 16:50
         *      1. 修改了返回的类型
         */

        Response infoResponse = new Response();

        User userInfo = new User();
        User findUser;

        Query infoQuery = new Query(Criteria.where("userId").is(userId));

        findUser = mongoTemplate.findOne(infoQuery, User.class, "user");

        if (findUser != null) {

            BeanUtils.copyProperties(findUser, userInfo);

            infoResponse.setVerifyCode(4);
            infoResponse.setResponseMessage("RequestSuccess");
            infoResponse.setUserResponse(userInfo);
        } else {

            infoResponse.setVerifyCode(-5);
            infoResponse.setResponseMessage("UserNotFound");
        }

        return infoResponse;
    }

    @Override
    public Response getVisitorInfo(String userId) {

        /*
         *  getVisitorInfo
         *  获取用户访客视角可以看到的信息
         *  参数:
         *      userId    : 用户 ID
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 19-Jan-2023 17:12
         * ---------------- 修改内容 -----------------------------------------
         *  19-Jan-2023 17:12
         *      1. 新增此函数
         */

        Response response = new Response();

        User visitorView = new User();
        User findUser;

        Query query = new Query(Criteria.where("userId").is(userId));

        findUser = mongoTemplate.findOne(query, User.class, "user");

        if (findUser != null) {

            BeanUtils.copyProperties(findUser, visitorView);
            visitorView.setUserFollower (List.of());
            visitorView.setUserFollowing(List.of());
            visitorView.setUserLike     (List.of());
            visitorView.setUserFav      (List.of());

            response.setVerifyCode(5);
            response.setResponseMessage("RequestSuccess");
            response.setUserResponse(visitorView);
        } else {

            response.setVerifyCode(-5);
            response.setResponseMessage("UserNotFound");
        }

        return response;
    }

    @Override
    public Response getUserPost(String userId) {

        /*
         *  getUserPost
         *  获取用户所有的帖子
         *  参数:
         *      userId    : 用户 ID
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 20-Jan-2023 02:01
         * ---------------- 修改内容 -----------------------------------------
         *  20-Jan-2023 02:01
         *      1. 修改返回的类型
         */

        Response response = new Response();

        List<String> findPost;

        User findUser;

        Query query = new Query(Criteria.where("userId").is(userId));

        findUser = mongoTemplate.findOne(query, User.class, "user");

        if (findUser != null) {

            findPost = findUser.getUserPost();

            response.setVerifyCode(6);
            response.setResponseMessage("RequestSuccess");
            response.setUserPostResponse(findPost);
        } else {

            response.setVerifyCode(-5);
            response.setResponseMessage("UserNotFound");
        }

        return response;
    }

    @Override
    public Response updateUserBasicInfo(User updateMsg) {

        /*
         *  getUserPost
         *  获取用户所有的帖子
         *  参数:
         *      updateMsg    : User 数据结构的更新信息
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 20-Jan-2023 02:01
         * ---------------- 修改内容 -----------------------------------------
         *  20-Jan-2023 02:01
         *      1. 修改返回的类型
         */

        Response response = new Response();

        Query query      = new Query(Criteria.where("userId").is(updateMsg.getUserId()));
        User  updateUser = mongoTemplate.findOne(query, User.class, "user");


        if (updateUser == null) {

            response.setVerifyCode(-5);
            response.setResponseMessage("UserNotFound");
        } else if (!updateUser.getUserEmail().equals(updateMsg.getUserEmail())) {

            response.setVerifyCode(-4);
            response.setResponseMessage("WrongEmail");
        } else if (!updateUser.getUserPass().equals(updateMsg.getUserPass())) {

            response.setVerifyCode(-3);
            response.setResponseMessage("WrongPass");
        } else {

            Update update = new Update();

            String updateUserName   = updateMsg.getUserName().equals("")   ? updateUser.getUserName()   : updateMsg.getUserName()  ;
            String updateUserPhoto  = updateMsg.getUserPhoto().equals("")  ? updateUser.getUserPhoto()  : updateMsg.getUserPhoto() ;
            String updateUserIntro  = updateMsg.getUserIntro().equals("")  ? updateUser.getUserIntro()  : updateMsg.getUserIntro() ;
            String updateDayOfBirth = updateMsg.getDayOfBirth().equals("") ? updateUser.getDayOfBirth() : updateMsg.getDayOfBirth();

            update.set(  "userName"    , updateUserName)
                    .set("userPhoto"   , updateUserPhoto)
                    .set("userIntro"   , updateUserIntro)
                    .set("dayOfBirth"  , updateDayOfBirth)
                    .set("userSexIndex", updateMsg.getUserSexIndex());

            mongoTemplate.upsert(query, update, User.class, "user");

            response.setVerifyCode(7);
            response.setResponseMessage("UpdateSuccess");
        }

        return response;
    }

    @Override
    public Response updateEmail(String userId, String oldEmail, String newEmail, String userPass) {

        /*
         *  updateEmail
         *  更新用户的电子邮箱
         *  参数:
         *      userId      : 用户的 UID
         *      oldEmail    : 用户旧的电子邮箱  ( 需用户自行输入 )
         *      newEmail    : 用户新的电子邮箱  ( 需用户自行输入 )
         *      userPass    : 用户的密码       ( 需用户自行输入 )
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 21-Jan-2023 01:40
         * ---------------- 修改内容 -----------------------------------------
         *  21-Jan-2023 01:40
         *      1. 修改返回的类型
         */

        Response response = new Response();

        Query query = new Query(Criteria.where("userId").is(userId));
        User  user  = mongoTemplate.findOne(query, User.class, "user");

        if (user == null) {

            response.setVerifyCode(-5);
            response.setResponseMessage("UserNotFound");
        } else if (user.getUserEmail().equals(oldEmail) && user.getUserPass().equals(userPass)) {

            Update update = new Update();
            update.set("userEmail", newEmail);

            mongoTemplate.upsert(query, update, User.class, "user");

            response.setVerifyCode(8);
            response.setResponseMessage("UpdatedEmail");
        } else if (!user.getUserEmail().equals(oldEmail)) {

            response.setVerifyCode(-4);
            response.setResponseMessage("WrongEmail");
        } else if (!user.getUserPass().equals(userPass)) {

            response.setVerifyCode(-3);
            response.setResponseMessage("WrongPassword");
        }

        return response;
    }

    @Override
    public Response updatePassword(String userId, String oldPass, String newPass) {

        /*
         *  updatePassword
         *  更新用户的密码
         *  参数:
         *      userId      : 用户的 UID
         *      newPass     : 用户的旧密码
         *      oldPass     : 用户的新密码
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 21-Jan-2023 01:51
         * ---------------- 修改内容 -----------------------------------------
         *  21-Jan-2023 01:51
         *      1. 修改返回的类型
         */

        Response response = new Response();

        Query query = new Query(Criteria.where("userId").is(userId));
        User  user  = mongoTemplate.findOne(query, User.class, "user");

        if (user == null) {

            response.setVerifyCode(-5);
            response.setResponseMessage("UserNotFound");
        } else if (user.getUserPass().equals(oldPass)) {

            Update update = new Update();
            update.set("userPass", newPass);

            mongoTemplate.upsert(query, update, User.class, "user");

            response.setVerifyCode(9);
            response.setResponseMessage("UpdatedPassword");
        } else if (!user.getUserPass().equals(oldPass)) {

            response.setVerifyCode(-3);
            response.setResponseMessage("WrongPassword");
        }

        return response;
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

    /*
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

     */

    // 判断当前邮箱是否已经被注册 True 表示已被注册 False 表示尚未被注册
    // Last Modified by SeeChen Lee @ 10-Jan-2023 00:00
    @Override
    public Boolean emailExists(String email) {

        Query query   = Query.query(Criteria.where("userEmail").is(email));
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

            //followerUser.getUserFollowing().add(new UserPublicInformation(targetUser.getUserId(), targetUser.getUserName()));
            //targetUser.getUserFollower().add(new UserPublicInformation(followerUser.getUserId(), followerUser.getUserName()));

            mongoTemplate.upsert(followerQuery, new Update().set("userFollowing", followerUser.getUserFollowing()), User.class, "user");
            mongoTemplate.upsert(targetQuery  , new Update().set("userFollower" , targetUser.getUserFollower()) , User.class, "user");

            return true;
        } else {

            return false;
        }
    }

    // 用于用户点赞 返回 True 表示已经点赞 False 表示点赞失败
    // Last Modified by SeeChen Lee @ 12-Jan-2023 11:12
    @Override
    public Boolean likePost(String userId, String postId) {

        Query query    = new Query(Criteria.where("userId").is(userId));
        User  likeUser = mongoTemplate.findOne(query, User.class, "user");

        assert likeUser != null;
        likeUser.getUserLike().add(postId);

        mongoTemplate.upsert(query, new Update().set("likePost", likeUser.getUserLike()), User.class, "user");

        return true;
    }

    @Override
    public String getUserName(String userId) {

        Query query = new Query(Criteria.where("userId").is(userId));
        User  user  = mongoTemplate.findOne(query,User.class);

        assert user != null;
        return user.getUserName();
    }
}
