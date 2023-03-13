package com.LLLT.MobileInternet.Dao;

import com.LLLT.MobileInternet.Entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;

public class UserDao extends BaseDao<User> {

    private String collectionName = "User";
    public UserDao(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    private User findOneByKey(String Key, String Value) {
        return super.findOneByKey(Key, Value, collectionName);
    }
    public User findOneById(String userId) {
        return findOneByKey("userId", userId);
    }
    public User findOneByName(String userName) {
        return findOneByKey("userName", userName);
    }
    public void Save(User user) {
        super.Save(user, collectionName);
    }
    public void RemoveById(String userId) {
        super.RemoveByKey("userId", userId, collectionName);
    }
    public void Update(String userId, HashMap<String, String> data) {
        super.UpdateByKey("userId", userId, collectionName, data);
    }
}
