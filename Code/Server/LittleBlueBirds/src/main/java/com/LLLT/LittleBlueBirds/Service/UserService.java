package com.LLLT.LittleBlueBirds.Service;

import com.LLLT.LittleBlueBirds.Entity.UserSecurity;
import com.LLLT.LittleBlueBirds.Util.Result;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Result<UserSecurity> UserRegister (String UserEmail, String UserPassword);
    Result<UserSecurity> UserLogin    (String UserEmail, String UserPassword);
    Result<String>       UserDelete   (String UserId, String UserEmail, String UserPassword, String UserName);
}
