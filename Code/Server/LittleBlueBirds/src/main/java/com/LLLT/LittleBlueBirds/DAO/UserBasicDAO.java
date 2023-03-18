package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Entity.UserBasic;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;

public class UserBasicDAO extends BaseDAO<UserBasic> {

    private String CollectionName = "UserBasic";

    public UserBasicDAO(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    public UserBasic findOneById (String UserId) {
        return super.findOneByKey("UserId", UserId, CollectionName);
    }

    public void Save (UserBasic data) {
        super.Save(data, CollectionName);
    }

    public void Remove (String UserId) {
        super.RemoveByKey("UserId", UserId, CollectionName);
    }

    public void Update (String UserId, String UpdateKey, String UpdateValue) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(UpdateKey, UpdateValue);
        super.UpdateByKey("UserId", UserId, CollectionName, hashMap);
    }

    public void Update (String UserId, HashMap<String, String> hashMap) {
        super.UpdateByKey("UserId", UserId, CollectionName, hashMap);
    }
}
