package com.LLLT.LittleBlueBirds.Service;

import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import com.LLLT.LittleBlueBirds.Util.UtilResult;
import org.springframework.stereotype.Service;

@Service
public interface ServiceAccount {

    UtilResult<AccountSecurity> AccountRegister (
            String AccountEmail,
            String AccountPassword
    );

    UtilResult<AccountSecurity> AccountLogin (
            String AccountEmail   ,
            String AccountPassword
    );

    UtilResult<String> AccountDelete (
            String UID            ,
            String AccountEmail   ,
            String AccountPassword,
            String AccountName
    );
}
