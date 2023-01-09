// Controller
/*
*   @Author : SeeChen Lee, ViHang Tan
*   @Contact: leeseechen@petalmail.com,
 */
package com.LLLT.MobileInternet.Controller;

import com.LLLT.MobileInternet.DAO.UserDao;
import com.LLLT.MobileInternet.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
    // Modified by SeeChen Lee @ 09-Jan-2023 15:24
    @CrossOrigin    // 允许跨域
    @PostMapping("/register")
    public String userRegister(HttpServletRequest httpServletRequest) {

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        User newUser = new User(userEmail, userPass);

        mongoTemplate.insert(newUser);

        return "ok";
    }
}