package com.LLLT.LittleBlueBirds.Service.Imp;

import com.LLLT.LittleBlueBirds.DAO.UserBasicDAO;
import com.LLLT.LittleBlueBirds.DAO.UserSecurityDAO;
import com.LLLT.LittleBlueBirds.Service.UserUpdateService;
import com.LLLT.LittleBlueBirds.Util.Result;
import com.LLLT.LittleBlueBirds.Util.FileOP;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Service("UserUpdateService")
public class UserUpdateImp implements UserUpdateService {

    private final MongoTemplate mongoTemplate;
    public UserUpdateImp (MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Value("${web.upload-path}") private String UploadPath;
    @Override
    public Result<String> UpdatePicture(MultipartFile ProfilePicture, String UserId, String UserEmail, String UserPassword) {

        Result<String> result = new Result<>();

        UserSecurityDAO userSecurityDAO = new UserSecurityDAO(mongoTemplate);
        UserBasicDAO    userBasicDao    = new UserBasicDAO(mongoTemplate);

        UserSecurity userSecurity = userSecurityDAO.findOneById(UserId);

        if (userSecurity == null) {

            return result.init(Result.CodeEnum.USER_NOT_FOUND);
        }

        if (!UserEmail.equals(userSecurity.getUserEmail()) || !UserPassword.equals(userSecurity.getUserPassword())) {

            return result.init(Result.CodeEnum.FAILED);
        }


        result = new FileOP.UploadPicture().Single(ProfilePicture, UploadPath, UserId, "profile");

        if (result.getCode() == Result.CodeEnum.UPLOAD_FAILED.getCode()) {

            return result;
        }

        userBasicDao.Update(UserId, "Picture", result.getData());

        return result;
    }

    @Override
    public Result<UserBasic> UpdateBasicInfo(String UserId, String UserEmail, String UserPassword, String[] UpdateMsg) {

        Result<UserBasic> result = new Result<>();

        UserSecurityDAO userSecurityDAO = new UserSecurityDAO(mongoTemplate);
        UserSecurity    userSecurity    = userSecurityDAO.findOneById(UserId);

        if (userSecurity == null) {

            return result.init(Result.CodeEnum.USER_NOT_FOUND);
        }

        if (!UserEmail.equals(userSecurity.getUserEmail()) || !UserPassword.equals(userSecurity.getUserPassword())) {

            return result.init(Result.CodeEnum.FAILED);
        }

        UserBasicDAO userBasicDAO = new UserBasicDAO(mongoTemplate);

        HashMap<String, Object> hashMap = new HashMap<>();
        String[] UpdateKey = {"Name", "Intro", "Sex", "BirthDay"};

        for (int i = 0; i < UpdateMsg.length; i++) {

            if (UpdateMsg[i].equals("")) {
                continue;
            }

            hashMap.put(UpdateKey[i], UpdateMsg[i]);
        }

        userBasicDAO.Update(UserId, hashMap);

        return result.init(Result.CodeEnum.SUCCESS, userBasicDAO.findOneById(UserId));
    }

    @Override
    public Result<UserSecurity> UpdateEmail(String UserId, String OldEmail, String NewEmail, String UserPassword) {

        Result<UserSecurity> result = new Result<>();

        UserSecurityDAO userSecurityDAO = new UserSecurityDAO(mongoTemplate);
        UserSecurity    userSecurity    = userSecurityDAO.findOneById(UserId);

        if (userSecurity == null) {

            return result.init(Result.CodeEnum.USER_NOT_FOUND);
        }

        if (!OldEmail.equals(userSecurity.getUserEmail())) {

            return result.init(Result.CodeEnum.EMAIL_INVALID);
        }

        if (!UserPassword.equals(userSecurity.getUserPassword())) {

            return result.init(Result.CodeEnum.WRONG_PASSWORD);
        }

        userSecurityDAO.Update(UserId, "UserEmail", NewEmail);

        return result.init(Result.CodeEnum.SUCCESS, userSecurityDAO.findOneByEmail(NewEmail));
    }

    @Override
    public Result<UserSecurity> UpdatePassword(String UserId, String UserEmail, String OldPassword, String NewPassword) {

        Result<UserSecurity> result = new Result<>();

        UserSecurityDAO userSecurityDAO = new UserSecurityDAO(mongoTemplate);
        UserSecurity    userSecurity    = userSecurityDAO.findOneById(UserId);

        if (userSecurity == null) {

            return result.init(Result.CodeEnum.USER_NOT_FOUND);
        }

        if (!UserEmail.equals(userSecurity.getUserEmail())) {

            return result.init(Result.CodeEnum.EMAIL_INVALID);
        }

        if (!OldPassword.equals(userSecurity.getUserPassword())) {

            return result.init(Result.CodeEnum.WRONG_PASSWORD);
        }

        userSecurityDAO.Update(UserId, "UserPassword", NewPassword);

        return result.init(Result.CodeEnum.SUCCESS, userSecurityDAO.findOneById(UserId));
    }
}
