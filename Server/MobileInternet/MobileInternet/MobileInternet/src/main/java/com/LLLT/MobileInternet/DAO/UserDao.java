package com.LLLT.MobileInternet.DAO;

import com.LLLT.MobileInternet.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public String CreateUser(String userEmail, String userPass) {

        User newUser = new User(userEmail, userPass);

        mongoTemplate.insert(newUser);

        return "ok";
    }
}
