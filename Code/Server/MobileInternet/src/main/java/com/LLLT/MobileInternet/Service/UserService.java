package com.LLLT.MobileInternet.Service;

import com.LLLT.MobileInternet.Entity.User;
import com.LLLT.MobileInternet.Entity.UserIndex;
import com.LLLT.MobileInternet.Util.Result.Result;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Result<UserIndex> userRegister   (String userEmail, String userPassword);
    Result<UserIndex> userLogin      (String userEmail, String userPassword);
    Result<String>    userDelete     (String userId, String userEmail, String userPassword, String userName);
}