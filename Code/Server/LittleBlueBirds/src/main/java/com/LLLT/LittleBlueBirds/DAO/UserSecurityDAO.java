package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Entity.UserSecurity;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;

public class UserSecurityDAO extends BaseDAO<UserSecurity> {

    private String CollectionName = "UserSecurity";

    public UserSecurityDAO(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    public UserSecurity findOneById (String UserId) {
        return super.findOneByKey("UserId", UserId,CollectionName);
    }

    public UserSecurity findOneByEmail (String Email) {
        return super.findOneByKey("UserEmail", Email, CollectionName);
    }

    public void Save (UserSecurity data) {
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
}
