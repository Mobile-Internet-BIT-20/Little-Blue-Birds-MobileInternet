package com.LLLT.LittleBlueBirds.Service.Imp;

import com.LLLT.LittleBlueBirds.DAO.DAOAccountBasic;
import com.LLLT.LittleBlueBirds.DAO.DAOAccountSecurity;
import com.LLLT.LittleBlueBirds.Entity.AccountBasic;
import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import com.LLLT.LittleBlueBirds.Enum.EnumResult;
import com.LLLT.LittleBlueBirds.Service.ServiceAccountUpdate;
import com.LLLT.LittleBlueBirds.Util.UtilFile;
import com.LLLT.LittleBlueBirds.Util.UtilResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@Service("ServiceAccountUpdate")
public class ImpAccountUpdate implements ServiceAccountUpdate {

    private final MongoTemplate mongoTemplate;
    public ImpAccountUpdate (
            MongoTemplate mongoTemplate
    ) {
        this.mongoTemplate = mongoTemplate;
    }

    @Value("${web.upload-path}") private String UploadPath;

    @Override
    public UtilResult<String> UpdateProfilePicture(
            MultipartFile file,
            String        UID
    ) {

        DAOAccountBasic DAOAccountBasic = new DAOAccountBasic(mongoTemplate);

        UtilResult<String> result = new UtilFile.UploadPicture().Single(file, UploadPath, UID, "ProfilePicture");

        if (result.getCode() == EnumResult.FAILED.getCode()) {

            return result;
        }

        DAOAccountBasic.update(UID, "ProfilePicture", result.getData());

        return result;
    }

    @Override
    public UtilResult<AccountBasic> UpdateAccountBasic (
            String   UID      ,
            String[] UpdateMsg
    ) {

        UtilResult<AccountBasic> result = new UtilResult<>();

        DAOAccountBasic DAOAccountBasic = new DAOAccountBasic(mongoTemplate);

        String[] UpdateKey = {
                "UserName" ,
                "UserIntro",
                "UserSex"  ,
                "DayBirth"
        };
        HashMap<String, Object> hashMap = new HashMap<>();

        for (int i = 0; i < 4; i++) {

            hashMap.put(UpdateKey[i], UpdateMsg[i]);
        }

        DAOAccountBasic.update(UID, hashMap);

        return result.init(EnumResult.SUCCESS, DAOAccountBasic.findOneById(UID));
    }

    @Override
    public UtilResult<AccountSecurity> UpdateAccountEmail (
            String UID     ,
            String EmailNew
    ) {

        UtilResult<AccountSecurity> result = new UtilResult<>();

        DAOAccountSecurity DAOAccountSecurity = new DAOAccountSecurity(mongoTemplate);

        DAOAccountSecurity.update(UID, "AccountEmail", EmailNew);

        return result.init(EnumResult.SUCCESS, DAOAccountSecurity.findOneByEmail(EmailNew));
    }

    @Override
    public UtilResult<AccountSecurity> UpdateAccountPassword (
            String UID        ,
            String PasswordNew
    ) {

        UtilResult<AccountSecurity> result = new UtilResult<>();

        DAOAccountSecurity DAOAccountSecurity = new DAOAccountSecurity(mongoTemplate);

        DAOAccountSecurity.update(UID, "AccountPassword", PasswordNew);

        return result.init(EnumResult.SUCCESS, DAOAccountSecurity.findOneById(UID));
    }
}
