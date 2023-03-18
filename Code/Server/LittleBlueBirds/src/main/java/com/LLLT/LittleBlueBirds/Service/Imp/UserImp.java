package com.LLLT.LittleBlueBirds.Service.Imp;

import com.LLLT.LittleBlueBirds.DAO.UserBasicDAO;
import com.LLLT.LittleBlueBirds.DAO.UserSecurityDAO;
import com.LLLT.LittleBlueBirds.Entity.UserBasic;
import com.LLLT.LittleBlueBirds.Entity.UserSecurity;
import com.LLLT.LittleBlueBirds.Service.UserService;
import com.LLLT.LittleBlueBirds.Util.Result;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserImp implements UserService {

    private final MongoTemplate mongoTemplate;
    public UserImp (MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Result<UserSecurity> UserRegister(String UserEmail, String UserPassword) {

        Result<UserSecurity> result = new Result<>();

        UserSecurityDAO userSecurityDAO = new UserSecurityDAO(mongoTemplate);
        UserBasicDAO    userBasicDAO    = new UserBasicDAO(mongoTemplate);

        if (userSecurityDAO.findOneByEmail(UserEmail) != null) {

            return result.init(Result.CodeEnum.EMAIL_INVALID);
        }

        String UserId = "U" + new ObjectId();

        UserSecurity userSecurity = new UserSecurity(UserId, UserEmail, UserPassword);
        UserBasic    userBasic    = new UserBasic(UserId);

        userBasic.init();

        userSecurityDAO.Save(userSecurity);
        userBasicDAO.Save(userBasic);

        return result.init(Result.CodeEnum.SUCCESS, userSecurity);
    }

    @Override
    public Result<UserSecurity> UserLogin(String UserEmail, String UserPassword) {

        Result<UserSecurity> result = new Result<>();

        UserSecurityDAO userSecurityDAO = new UserSecurityDAO(mongoTemplate);
        UserSecurity    userSecurity    = userSecurityDAO.findOneByEmail(UserEmail);

        if (userSecurity == null) {

            return result.init(Result.CodeEnum.EMAIL_INVALID);
        }

        if (!userSecurity.getUserPassword().equals(UserPassword)) {

            return result.init(Result.CodeEnum.WRONG_PASSWORD);
        }

        return result.init(Result.CodeEnum.SUCCESS, userSecurity);
    }

    @Override
    public Result<String> UserDelete(String UserId, String UserEmail, String UserPassword, String UserName) {

        Result<String> result = new Result<>();

        UserSecurityDAO userSecurityDAO = new UserSecurityDAO(mongoTemplate);
        UserBasicDAO    userBasicDAO    = new UserBasicDAO(mongoTemplate);

        if (userSecurityDAO.findOneById(UserId) == null) {

            return result.init(Result.CodeEnum.USER_NOT_FOUND);
        }

        UserBasic    userBasic    = userBasicDAO.findOneById(UserId);
        UserSecurity userSecurity = userSecurityDAO.findOneById(UserId);

        if (!UserEmail.equals(userSecurity.getUserEmail())) {

            return result.init(Result.CodeEnum.EMAIL_INVALID);
        }

        if (!UserPassword.equals(userSecurity.getUserPassword())) {

            return result.init(Result.CodeEnum.WRONG_PASSWORD);
        }

        if (!UserName.equals(userBasic.getName())) {

            return result.init(Result.CodeEnum.FAILED);
        }

        userSecurityDAO.Remove(UserId);
        userBasicDAO.Remove(UserId);

        return result.init(Result.CodeEnum.SUCCESS);
    }
}
