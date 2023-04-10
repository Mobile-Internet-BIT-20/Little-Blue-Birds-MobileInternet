package com.LLLT.LittleBlueBirds.Service.Imp;

import com.LLLT.LittleBlueBirds.DAO.DAOAccountBasic;
import com.LLLT.LittleBlueBirds.DAO.DAOAccountSecurity;
import com.LLLT.LittleBlueBirds.Entity.AccountBasic;
import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import com.LLLT.LittleBlueBirds.Service.ServiceAccount;
import com.LLLT.LittleBlueBirds.Util.Result;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service("AccountService")
public class ImpAccount implements ServiceAccount {

    private final MongoTemplate mongoTemplate;
    public ImpAccount(
            MongoTemplate mongoTemplate
    ) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Result<AccountSecurity> AccountRegister (
            String AccountEmail   ,
            String AccountPassword
    ) {

        Result<AccountSecurity> result = new Result<>();

        DAOAccountSecurity DAOaccountSecurity = new DAOAccountSecurity(mongoTemplate);
        DAOAccountBasic    DAOaccountBasic    = new DAOAccountBasic(mongoTemplate);

        if (DAOaccountSecurity.findOneByEmail(AccountEmail) != null) {

            return result.init(Result.CodeEnum.EMAIL_INVALID);
        }

        String UID = "U" + new ObjectId();

        AccountSecurity accountSecurity = new AccountSecurity(UID, AccountEmail, AccountPassword);
        AccountBasic    accountBasic    = new AccountBasic(UID);

        accountBasic.init();

        DAOaccountBasic.Save(accountBasic);
        DAOaccountSecurity.Save(accountSecurity);

        return result.init(Result.CodeEnum.SUCCESS, accountSecurity);
    }

    @Override
    public Result<AccountSecurity> AccountLogin(
            String AccountEmail   ,
            String AccountPassword
    ) {

        Result<AccountSecurity> result = new Result<>();

        DAOAccountSecurity DAOAccountSecurity = new DAOAccountSecurity(mongoTemplate);
        AccountSecurity    accountSecurity    = DAOAccountSecurity.findOneByEmail(AccountEmail);

        if (accountSecurity == null) {

            return result.init(Result.CodeEnum.EMAIL_INVALID);
        }

        if (!accountSecurity.getAccountPassword().equals(AccountPassword)) {

            return result.init(Result.CodeEnum.WRONG_PASSWORD);
        }

        return result.init(Result.CodeEnum.SUCCESS, accountSecurity);
    }

    @Override
    public Result<String> AccountDelete (
            String UID            ,
            String AccountEmail   ,
            String AccountPassword,
            String UserName
    ) {

       Result<String> result = new Result<>();

       DAOAccountSecurity DAOAccountSecurity = new DAOAccountSecurity(mongoTemplate);
       DAOAccountBasic    DAOAccountBasic    = new DAOAccountBasic(mongoTemplate);

       if (DAOAccountSecurity.findOneById(UID) == null) {

           return result.init(Result.CodeEnum.USER_NOT_FOUND);
       }

       AccountSecurity accountSecurity = DAOAccountSecurity.findOneById(UID);
       AccountBasic    accountBasic    = DAOAccountBasic.findOneById(UID);

       if (!accountSecurity.getAccountEmail().equals(AccountEmail)) {

           return result.init(Result.CodeEnum.EMAIL_INVALID);
       }

       if (!accountSecurity.getAccountPassword().equals(AccountPassword)) {

           return result.init(Result.CodeEnum.WRONG_PASSWORD);
       }

       if (!accountBasic.getUserName().equals(UserName)) {

           return result.init(Result.CodeEnum.FAILED);
       }

        DAOAccountBasic.Remove(UID);
       DAOAccountSecurity.Remove(UID);

       return result.init(Result.CodeEnum.SUCCESS);
    }
}
