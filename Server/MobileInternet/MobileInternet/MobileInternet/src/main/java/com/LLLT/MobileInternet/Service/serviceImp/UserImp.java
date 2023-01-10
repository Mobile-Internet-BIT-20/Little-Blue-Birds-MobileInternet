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
import com.LLLT.MobileInternet.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

        Query query   = Query.query(Criteria.where("email").is(email));
        User  isEmpty = mongoTemplate.findOne(query, User.class, "user");

        // 为 null 表示当前邮箱未被使用
        return isEmpty != null;
    }
}
