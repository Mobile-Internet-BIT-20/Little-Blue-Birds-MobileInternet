package com.LLLT.MobileInternet.Service.Imp;

import com.LLLT.MobileInternet.Dao.UserDao;
import com.LLLT.MobileInternet.Dao.UserIndexDao;
import com.LLLT.MobileInternet.Entity.User;
import com.LLLT.MobileInternet.Entity.UserIndex;
import com.LLLT.MobileInternet.Service.UserService;
import com.LLLT.MobileInternet.Util.Result.Result;
import com.LLLT.MobileInternet.Util.Result.ResultCode;
import com.LLLT.MobileInternet.Util.Sex.SexList;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service("UserService")
public class UserImp implements UserService {

    private final MongoTemplate mongoTemplate;
    public UserImp(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Result<UserIndex> userRegister(String userEmail, String userPassword) {

        Result<UserIndex> result = new Result<>();

        UserIndexDao userIndexDao = new UserIndexDao(mongoTemplate);
        UserDao      userDao      = new UserDao(mongoTemplate);

        if (userIndexDao.findOneByEmail(userEmail) == null) {

            String userId = "U" + new ObjectId();

            UserIndex userIndex = new UserIndex(userId, userEmail, userPassword);
            User      user      = new User(userId).init();

            userIndexDao.Save(userIndex);
            userDao.Save(user);

            result.init(ResultCode.REGISTER_SUCCESS, userIndex);
        } else {

            result.init(ResultCode.EMAIL_EXISTS);
        }

        return result;
    }

    @Override
    public Result<UserIndex> userLogin(String userEmail, String userPassword) {

        Result<UserIndex> result = new Result<>();

        UserIndexDao userIndexDao = new UserIndexDao(mongoTemplate);

        UserIndex userIndex = userIndexDao.findOneByEmail(userEmail);

        if (userIndex == null) {

            result.init(ResultCode.EMAIL_INVALID);
        } else {

            if (userPassword.equals(userIndex.getUserPassword())) {

                result.init(ResultCode.LOGIN_SUCCESS, userIndex);
            } else {

                result.init(ResultCode.WRONG_PASSWORD);
            }
        }

        return result;
    }

    @Override
    public Result<String> userDelete(String userId, String userEmail, String userPassword, String userName) {

        Result<String> result = new Result<>();

        UserIndexDao userIndexDao = new UserIndexDao(mongoTemplate);
        UserDao      userDao      = new UserDao(mongoTemplate);

        UserIndex userIndex = userIndexDao.findOneById(userId);

        if (userIndex == null) {

            result.init(ResultCode.USER_NOT_FOUND);
        } else {

            User user = userDao.findOneById(userId);

            if (userEmail.equals(userIndex.getUserEmail()) && userPassword.equals(userIndex.getUserPassword()) && userName.equals(user.getUserName())) {

                userDao.RemoveById(userId);
                userIndexDao.RemoveById(userId);

                result.init(ResultCode.DELETE_SUCCESS, ResultCode.DELETE_SUCCESS.getMsg());
            } else {

                result.init(ResultCode.ACCOUNT_DELETE_FAILED, ResultCode.ACCOUNT_DELETE_FAILED.getMsg());
            }
        }

        return result;
    }
}