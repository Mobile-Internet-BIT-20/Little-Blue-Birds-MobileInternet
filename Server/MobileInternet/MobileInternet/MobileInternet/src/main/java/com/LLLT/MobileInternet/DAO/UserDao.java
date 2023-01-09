// User Dao
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com,
 */

package com.LLLT.MobileInternet.DAO;

import com.LLLT.MobileInternet.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;


}
