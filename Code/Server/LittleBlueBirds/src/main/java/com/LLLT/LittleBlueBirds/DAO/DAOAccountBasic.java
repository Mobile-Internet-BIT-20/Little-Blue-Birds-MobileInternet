package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Entity.AccountBasic;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DAOAccountBasic extends BaseDAO<AccountBasic> {

    private final String CollectionName = "AccountBasic";

    public DAOAccountBasic(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    public AccountBasic findOneById (
            String UID
    ) {
        return super.findOneByKey("UID", UID, CollectionName);
    }

    public void Save(
            AccountBasic data
    ) {
        super.Save(data, CollectionName);
    }

    public void Remove (
            String UID
    ) {
        super.RemoveByKey("UID", UID, CollectionName);
    }
}
