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

    // 判断当前邮箱是否已经被注册 True 表示已被注册 False 表示尚未被注册
    // Last Modified by SeeChen Lee @ 10-Jan-2023 00:00
    @Override
    public Boolean emailExists(String email) {

        Query query   = Query.query(Criteria.where("email").is(email));
        User  isEmpty = mongoTemplate.findOne(query, User.class, "user");

        return isEmpty != null;
    }
}
