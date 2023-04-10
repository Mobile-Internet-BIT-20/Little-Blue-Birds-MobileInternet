package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DAOAccountSecurity extends BaseDAO<AccountSecurity> {

    private final String CollectionName = "AccountSecurity";

    public DAOAccountSecurity(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    private AccountSecurity findOneByKey (
            String Key  ,
            String Value
    ) {

        return findOneByKey(Key, Value, CollectionName);
    }

    public AccountSecurity findOneByEmail(
            String Email
    ) {
        return findOneByKey("AccountEmail", Email);
    }

    public AccountSecurity findOneById (
            String UID
    ) {
        return findOneByKey("UID", UID);
    }

    public void Save (
            AccountSecurity data
    ) {
        super.Save(data, CollectionName);
    }

    public void Remove (
            String UID
    ) {
        super.RemoveByKey("UID", UID, CollectionName);
    }
}
