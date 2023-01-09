// Controller
/*
 *   @Author : SeeChen Lee, ViHang Tan
 *   @Contact: leeseechen@petalmail.com,
 */

/*  代码和人 有一个能跑就行
*                     ,----------------,              ,---------,
*                ,-----------------------,          ,"        ,"|
*              ,"                      ,"|        ,"        ,"  |
*             +-----------------------+  |      ,"        ,"    |
*             |  .-----------------.  |  |     +---------+      |
*             |  |                 |  |  |     | -==----'|      |
*             |  |  I LOVE DOS!    |  |  |     |         |      |
*             |  |  Bad command or |  |  |/----|`---=    |      |
*             |  |  C:\>rm -rf /*  |  |  |   ,/|==== ooo |      ;
*             |  |                 |  |  |  // |(((( [33]|    ,"
*             |  `-----------------'  |," .;'| |((((     |  ,"
*             +-----------------------+  ;;  | |         |,"
*                /_)______________(_/  //'   | +---------+
*           ___________________________/___  `,
*          /  oooooooooooooooo  .o.  oooo /,   \,"-----------
*         / ==ooooooooooooooo==.o.  ooo= //   ,`\--{)B     ,"
*        /_==__==========__==_ooo__ooo=_/'   /___________,"
*
*/
package com.LLLT.MobileInternet.Controller;

import com.LLLT.MobileInternet.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private MongoTemplate mongoTemplate;

    // MainPage 函数 网页版默认主页
    // Modified by SeeChen Lee @ 09-Jan-2023 11:36
    @RequestMapping("/")
    public String MainPage() {

        return "Hello World";
    }

    // 注册函数
    // Modified by SeeChen Lee @ 09-Jan-2023 19:04
    @CrossOrigin    // 允许跨域
    @PostMapping("/register")
    public String userRegister(HttpServletRequest httpServletRequest) {

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        // 用于检测是否重复登录
        Query query = Query.query(Criteria.where("email").is(userEmail));
        User checkEmpty = mongoTemplate.findOne(query, User.class, "user");

        if (checkEmpty != null) {

            // 若用户存在 则返回信息
            return "User Exists";
        } else {

            String userName, userId, dayOfBirth;
            Integer userSexIndex = 0;   // 默认未设置
            List<String> userPost = List.of();

            // 用户不存在 新建一个用户
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

            // 存储数据
            mongoTemplate.insert(newUser);

            return userId;
        }
    }
}