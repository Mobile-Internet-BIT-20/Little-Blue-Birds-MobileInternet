// User Implement
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com,
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
import com.LLLT.MobileInternet.Entity.UserPI;
import com.LLLT.MobileInternet.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

// 接口实现类
@Service("UserService")
public class UserImp implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;


    // 新用户保存函数
    // Modified by SeeChen Lee @ 10-Jan-2023 00:16
    @Override
    public String createUser(String userEmail, String userPass) {

        String userName, userId, dayOfBirth;
        Integer userSexIndex = 0;   // 默认未设置
        List<String> userPost = List.of();
        List<UserPI> follower = List.of();
        List<UserPI> following = List.of();

        // 新建一个用户
        User newUser = new User(userEmail, userPass);

        // 下列为默认设置 可以在创建用户完成后重新设置
        userId = new ObjectId().toString();
        userName = "用户" + userId;
        dayOfBirth = "1900-01-01";

        // 进行设置
        newUser.setUserId(userId);
        newUser.setUserName(userName);
        newUser.setDayOfBirth(dayOfBirth);
        newUser.setUserSexIndex(userSexIndex);
        newUser.setFollower(follower);
        newUser.setFollowing(following);


        // 设置帖子数据 初始为空
        newUser.setUserPost(userPost);

        mongoTemplate.insert(newUser);

        return userId;
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

        Query query = Query.query(Criteria.where("email").is(email));
        User isEmpty = mongoTemplate.findOne(query, User.class, "user");

        // 为 null 表示当前邮箱未被使用
        return isEmpty != null;
    }

    // 用户登录实现
    // Last Modified by ViHang Tan @ 10-Jan-2023 14:22
    @Override
    public String userLogin(String email, String pass) {

        Query query = Query.query(Criteria.where("email").is(email));
        User user = mongoTemplate.findOne(query, User.class);
        String password = user.getUserPass();

        if (pass.equals(password)) {
            return user.getUserId();
        } else {
            return "false";
        }

    }

    // 用户删除
    // Last Modified by ViHang Tan @ 10-Jan-2023 14:22
    @Override
    public String userDelete(String userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        mongoTemplate.remove(query, User.class);
        return "User deleted from data";
    }

    // 更新用户基本信息
    // Last Modified by ViHang Tan @ 10-Jan-2023 19:56
    @Override
    public Boolean updateUserBasicInfo(User user) {

        Query query = Query.query(Criteria.where("userId").is(user.getUserId()));
        System.out.println(user.getUserId());
        if (query == null) {
            return false;
        }

        Update update = new Update();
        update.set("userName", user.getUserName()).set("dayOfBirth", user.getDayOfBirth()).set("userSexIndex", user.getUserSexIndex());
        mongoTemplate.upsert(query, update, User.class);

        return true;
    }

    // 更新用户密码
    // Last Modified by ViHang Tan @ 10-Jan-2023 19:56
    @Override
    public Boolean updateUserPassword(User user) {

        Query query = Query.query(Criteria.where("userId").is(user.getUserId()));
        User user1 = mongoTemplate.findOne(query, User.class);

        if (user1 != null) {
            Update update = new Update();
            update.set("userPass",user.getUserPass());

            mongoTemplate.upsert(query,update, User.class);

            return true;
        }

        return false;
    }

    // 更新用户邮箱
    // Last Modified by ViHang Tan @ 10-Jan-2023 19:56
    @Override
    public Boolean updateUserEmail(User user) {
        Query query = Query.query(Criteria.where("userId").is(user.getUserId()));
        User user1 = mongoTemplate.findOne(query, User.class);

        if (user1 != null) {
            Update update = new Update();
            update.set("email",user.getEmail());

            mongoTemplate.upsert(query,update, User.class);

            return true;
        }

        return false;
    }

    // 更新用户关注
    // Last Modified by ViHang Tan @ 10-Jan-2023 20:40
    @Override
    public Boolean updateFollow(String myId, String followId) {
        Query query1 = Query.query(Criteria.where("userId").is(myId));
        Query query2 = Query.query(Criteria.where("userId").is(followId));

        User user = mongoTemplate.findOne(query1,User.class);
        User followUser = mongoTemplate.findOne(query2,User.class);

        //获取用户最基本信息
        UserPI userPi = new UserPI(user.getUserId(),user.getUserName());
        UserPI followUserPi = new UserPI(followUser.getUserId(),followUser.getUserName());

        if(user !=null && followUser!=null){
            //关注的人
            user.getFollowing().add(followUserPi);

            Update update = new Update();
            update.set("following",user.getFollowing());

            mongoTemplate.upsert(query1,update,User.class);

            //被关注的人
            followUser.getFollower().add(userPi);
            update.set("follower",followUser.getFollower());

            mongoTemplate.upsert(query2,update, User.class);

            return true;

        }

        return null;
    }
}
