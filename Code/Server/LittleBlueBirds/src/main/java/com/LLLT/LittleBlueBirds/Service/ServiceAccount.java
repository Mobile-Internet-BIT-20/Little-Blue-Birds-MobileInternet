package com.LLLT.LittleBlueBirds.Service;

import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import com.LLLT.LittleBlueBirds.Util.Result;
import org.springframework.stereotype.Service;

@Service
public interface ServiceAccount {

    Result<AccountSecurity> AccountRegister (
            String AccountEmail   ,
            String AccountPassword
    );

    Result<AccountSecurity> AccountLogin (
            String AccountEmail   ,
            String AccountPassword
    );

    Result<String> AccountDelete (

            String UID            ,
            String AccountEmail   ,
            String AccountPassword,
            String UserName
    );
}
