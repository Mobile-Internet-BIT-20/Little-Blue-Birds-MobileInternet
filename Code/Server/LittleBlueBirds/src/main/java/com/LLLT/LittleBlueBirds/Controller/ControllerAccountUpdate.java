package com.LLLT.LittleBlueBirds.Controller;

import com.LLLT.LittleBlueBirds.Entity.AccountBasic;
import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import com.LLLT.LittleBlueBirds.Enum.EnumResult;
import com.LLLT.LittleBlueBirds.Service.ServiceAccount;
import com.LLLT.LittleBlueBirds.Service.ServiceAccountUpdate;
import com.LLLT.LittleBlueBirds.Util.UtilCookie;
import com.LLLT.LittleBlueBirds.Util.UtilResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/api/account/update")
public class ControllerAccountUpdate {

    private final ServiceAccountUpdate serviceAccountUpdate;
    private final ServiceAccount       serviceAccount      ;
    public ControllerAccountUpdate (
            ServiceAccountUpdate serviceAccountUpdate,
            ServiceAccount       serviceAccount
    ) {
        this.serviceAccountUpdate = serviceAccountUpdate;
        this.serviceAccount       = serviceAccount      ;
    }

    @PostMapping("/profilePicture")
    public UtilResult<String> AccountUpdateProfilePicture (
            HttpServletRequest  request,
            @RequestParam("ProfilePicture")MultipartFile multipartFile
    ) {

        UtilResult<String> result = new UtilResult<>();

        HashMap<String, String> hashMap = UtilCookie.getCookie.cookieAccount.cookieSecurity(request.getCookies());

        UtilResult<AccountSecurity> accountSecurityUtilResult = serviceAccount.AccountLogin(hashMap.get("AccountEmail"), hashMap.get("AccountPassword"));

        if (accountSecurityUtilResult.getCode() != EnumResult.SUCCESS.getCode()) {

            return result.init(EnumResult.FAILED);
        }

        result = serviceAccountUpdate.UpdateProfilePicture(multipartFile, hashMap.get("UID"));

        return result;
    }

    @PostMapping("/basicInfo")
    public UtilResult<AccountBasic> AccountUpdateBasicInfo (
            HttpServletRequest  request
    ) {

        HashMap<String, String> hashMap = UtilCookie.getCookie.cookieAccount.cookieSecurity(request.getCookies());

        UtilResult<AccountBasic> result = new UtilResult<>();
        if (serviceAccount.AccountLogin(hashMap.get("AccountEmail"), hashMap.get("AccountPassword")).getCode() != EnumResult.SUCCESS.getCode()) {

            return result.init(EnumResult.FAILED);
        }

        String[] msg = {
                "name" ,
                "intro",
                "sex"  ,
                "dob"
        };

        for (int i = 0; i < msg.length; i++) {

            msg[i] = request.getParameter(msg[i]);
        }

        result = serviceAccountUpdate.UpdateAccountBasic(hashMap.get("UID"), msg);

        return result;
    }

}
