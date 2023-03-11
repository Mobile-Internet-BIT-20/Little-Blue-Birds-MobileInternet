package com.LLLT.MobileInternet.Service;

import com.LLLT.MobileInternet.Entity.User;
import com.LLLT.MobileInternet.Entity.UserIndex;
import com.LLLT.MobileInternet.Util.Result.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserUpdateService {

    Result<User> updateBasicInfo(String userId, String userEmail, String userPassword, String[] updateMsg);
    Result<UserIndex> updateEmail    (String userId, String oldEmail, String newEmail, String userPassword);
    Result<UserIndex> updatePassword (String userId, String userEmail, String oldPassword, String newPassword);
    Result<String>    updatePhoto    (String userId, String userEmail, String userPassword, MultipartFile userPhoto);
}
