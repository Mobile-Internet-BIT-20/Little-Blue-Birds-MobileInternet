package com.LLLT.MobileInternet.Dao;

import com.LLLT.MobileInternet.Entity.UserIndex;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;
import java.util.Map;

public class UserIndexDao extends BaseDao<UserIndex> {

    private String collectionName = "UserIndex";

    public UserIndexDao(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    public UserIndex findOneByKey(String Key, String Value) {
        return super.findOneByKey(Key, Value, collectionName);
    }
    public UserIndex findOneById(String userId) {
        return findOneByKey("userId", userId);
    }
    public UserIndex findOneByEmail(String userEmail) {
        return findOneByKey("userEmail", userEmail);
    }
    public void Save(UserIndex userIndex) {
        super.Save(userIndex, collectionName);
    }
    public void RemoveById(String userId) {
        super.RemoveByKey("userId", userId, collectionName);
    }
    public void Update(String userId, String Key, String Value) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(Key, Value);
        super.UpdateByKey("userId", userId, collectionName, hashMap);
    }
}
