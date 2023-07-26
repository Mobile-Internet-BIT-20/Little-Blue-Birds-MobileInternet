package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Entity.AccountBasic;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;

public class DAOAccountBasic extends BaseDAO<AccountBasic> {

    private final String CollectionSuffix = "_AccountBasic";

    public DAOAccountBasic(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    // Create
    public void save (
            AccountBasic Data
    ) {
        super.Save(Data, Data.getUID() + CollectionSuffix);
    }

    // Retrieve
    public AccountBasic findOneById (
            String UID
    ) {
        return super.FindOneByKey("UID", UID, UID + CollectionSuffix);
    }

    // Update
    public void update (
            String UID  ,
            String Key  ,
            String Value
    ) {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(Key, Value);

        super.UpdateByKey("UID", UID, UID + CollectionSuffix, hashMap);
    }

    public void update (
            String UID,
            HashMap<String, Object> hashMap
    ) {

        super.UpdateByKey("UID", UID, UID + CollectionSuffix, hashMap);
    }

    // Delete
    public void remove (
            String UID
    ) {
        super.RemoveByKey("UID", UID, UID + CollectionSuffix);
    }
}
