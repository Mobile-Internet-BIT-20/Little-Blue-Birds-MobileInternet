package com.LLLT.MobileInternet.Service.Imp;

import com.LLLT.MobileInternet.Dao.UserDao;
import com.LLLT.MobileInternet.Dao.UserIndexDao;
import com.LLLT.MobileInternet.Entity.User;
import com.LLLT.MobileInternet.Entity.UserIndex;
import com.LLLT.MobileInternet.Service.UserUpdateService;
import com.LLLT.MobileInternet.Util.Result.Result;
import com.LLLT.MobileInternet.Util.Result.ResultCode;
import com.LLLT.MobileInternet.Util.Upload.UploadPicture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Service("UserUpdateService")
public class UserUpdateImp implements UserUpdateService {

    private final MongoTemplate mongoTemplate;
    public UserUpdateImp(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Result<User> updateBasicInfo(String userId, String userEmail, String userPassword, String[] updateMsg) {

        Result<User> result = new Result<>();

        UserIndexDao userIndexDao = new UserIndexDao(mongoTemplate);
        UserDao userDao      = new UserDao(mongoTemplate);

        UserIndex userIndex = userIndexDao.findOneById(userId);

        if (userIndex == null) {

            result.init(ResultCode.USER_NOT_FOUND);
        } else {

            if (userIndex.getUserEmail().equals(userEmail) && userIndex.getUserPassword().equals(userPassword)) {

                HashMap<String, String> hashMap = new HashMap<>();

                String[] updateKey = {"userName", "userIntro", "userBirthDay", "userSex"};

                for (int i = 0; i < updateMsg.length; i++) {

                    if (updateMsg[i].equals(""))
                        continue;
                    hashMap.put(updateKey[i], updateMsg[i]);
                }

                userDao.Update(userId, hashMap);

                User user = userDao.findOneById(userId).setPrivateContent();

                result.init(ResultCode.UPDATE_SUCCESS, user);
            } else if (userIndex.getUserEmail().equals(userEmail)) {

                result.init(ResultCode.WRONG_PASSWORD);
            } else {

                result.init(ResultCode.EMAIL_INVALID);
            }
        }

        return result;
    }

    @Override
    public Result<UserIndex> updateEmail(String userId, String oldEmail, String newEmail, String userPassword) {

        Result<UserIndex> result = new Result<>();

        UserIndexDao userIndexDao = new UserIndexDao(mongoTemplate);

        UserIndex userIndex = userIndexDao.findOneById(userId);

        if (userIndex == null) {

            result.init(ResultCode.USER_NOT_FOUND);
        } else {

            if (userIndex.getUserEmail().equals(oldEmail) && userIndex.getUserPassword().equals(userPassword)) {

                userIndexDao.Update(userId, "userEmail", newEmail);
                result.init(ResultCode.UPDATE_SUCCESS, userIndexDao.findOneById(userId));
            } else if (userIndex.getUserPassword().equals(userPassword)){

                result.init(ResultCode.EMAIL_INVALID);
            } else {

                result.init(ResultCode.WRONG_PASSWORD);
            }
        }

        return result;
    }

    @Override
    public Result<UserIndex> updatePassword(String userId, String userEmail, String oldPassword, String newPassword) {

        Result<UserIndex> result = new Result<>();

        UserIndexDao userIndexDao = new UserIndexDao(mongoTemplate);
        UserIndex    userIndex    = userIndexDao.findOneById(userId);

        if (userIndex == null) {

            result.init(ResultCode.USER_NOT_FOUND);
        } else {

            if (userIndex.getUserEmail().equals(userEmail) && userIndex.getUserPassword().equals(oldPassword)) {

                userIndexDao.Update(userId, "userPassword", newPassword);
                result.init(ResultCode.UPDATE_SUCCESS, userIndexDao.findOneById(userId));
            } else if (userIndex.getUserPassword().equals(oldPassword)) {

                result.init(ResultCode.EMAIL_INVALID);
            } else {

                result.init(ResultCode.WRONG_PASSWORD);
            }
        }

        return result;
    }

    @Value("${web.upload-path}") private String uploadPath;
    @Override
    public Result<String> updatePhoto(String userId, String userEmail, String userPassword, MultipartFile userPhoto) {

        Result<String> result = new Result<>();

        UserIndexDao userIndexDao = new UserIndexDao(mongoTemplate);
        UserDao      userDao      = new UserDao(mongoTemplate);
        UserIndex    userIndex    = userIndexDao.findOneById(userId);

        if (userIndex == null) {

            result.init(ResultCode.USER_NOT_FOUND);
        } else {

            if (userIndex.getUserEmail().equals(userEmail) && userIndex.getUserPassword().equals(userPassword)) {

                result = new UploadPicture().onePicture(userPhoto, uploadPath, userId, "/profile");

                if (result.getResultCode() == ResultCode.UPLOAD_SUCCESS.getCode()) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("userPhoto", result.getData());
                    userDao.Update(userId, hashMap);
                }
            } else if (userIndex.getUserPassword().equals(userPassword)) {

                result.init(ResultCode.EMAIL_INVALID);
            } else {

                result.init(ResultCode.WRONG_PASSWORD);
            }
        }

        return result;
    }
}
