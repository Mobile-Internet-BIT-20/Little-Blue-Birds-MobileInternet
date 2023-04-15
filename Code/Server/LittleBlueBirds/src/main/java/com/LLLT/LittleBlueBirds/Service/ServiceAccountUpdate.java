package com.LLLT.LittleBlueBirds.Service;

import com.LLLT.LittleBlueBirds.Entity.AccountBasic;
import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import com.LLLT.LittleBlueBirds.Util.UtilResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ServiceAccountUpdate {

    UtilResult<String> UpdateProfilePicture (
            MultipartFile file,
            String        UID
    );

    UtilResult<AccountBasic> UpdateAccountBasic (
            String   UID      ,
            String[] UpdateMsg
    );

    UtilResult<AccountSecurity> UpdateAccountEmail (
            String UID     ,
            String EmailNew
    );

    UtilResult<AccountSecurity> UpdateAccountPassword (
            String UID        ,
            String PasswordNew
    );
}
