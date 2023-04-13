package com.LLLT.LittleBlueBirds.DAO;

import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DAOAccountSecurity extends BaseDAO<AccountSecurity> {

    private final String CollectionName = "AccountSecurity";

    public DAOAccountSecurity(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    // Create
    public void save (
            AccountSecurity Data
    ) {
        super.Save(Data, CollectionName);
    }

    // Retrieve
    private AccountSecurity findOneByKey (
            String Key  ,
            String Value
    ) {
        return super.FindOneByKey(Key, Value, CollectionName);
    }

    public AccountSecurity findOneById (
            String UID
    ) {
        return this.findOneByKey("UID", UID);
    }

    public AccountSecurity findOneByEmail (
            String Email
    ) {
        return this.findOneByKey("AccountEmail", Email);
    }

    // Update
    // Delete
    public void remove (
            String UID
    ) {
        super.RemoveByKey("UID", UID, CollectionName);
    }
}
