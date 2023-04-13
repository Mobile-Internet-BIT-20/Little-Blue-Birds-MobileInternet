package com.LLLT.LittleBlueBirds.Service;

import com.LLLT.LittleBlueBirds.Entity.AccountBasic;
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

}
