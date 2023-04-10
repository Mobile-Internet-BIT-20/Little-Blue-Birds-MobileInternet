package com.LLLT.LittleBlueBirds.Service;

import com.LLLT.LittleBlueBirds.Util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserUpdateService {

    Result<String>    UpdatePicture  (MultipartFile ProfilePicture, String UserId, String UserEmail, String UserPassword);
    Result<UserBasic> UpdateBasicInfo(String UserId, String UserEmail, String UserPassword, String[] UpdateMsg);

    Result<UserSecurity> UpdateEmail   (String UserId, String OldEmail, String NewEmail, String UserPassword);
    Result<UserSecurity> UpdatePassword(String UserId, String UserEmail, String OldPassword, String NewPassword);
}
