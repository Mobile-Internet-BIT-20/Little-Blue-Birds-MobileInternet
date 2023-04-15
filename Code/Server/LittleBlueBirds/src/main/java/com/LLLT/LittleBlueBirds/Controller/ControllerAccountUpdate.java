package com.LLLT.LittleBlueBirds.Controller;

import com.LLLT.LittleBlueBirds.Entity.AccountBasic;
import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import com.LLLT.LittleBlueBirds.Enum.EnumResult;
import com.LLLT.LittleBlueBirds.Service.ServiceAccount;
import com.LLLT.LittleBlueBirds.Service.ServiceAccountUpdate;
import com.LLLT.LittleBlueBirds.Util.UtilConvert;
import com.LLLT.LittleBlueBirds.Util.UtilCookie;
import com.LLLT.LittleBlueBirds.Util.UtilResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
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

    @PostMapping("/email")
    public UtilResult<AccountSecurity> AccountUpdateEmail (
            HttpServletRequest  request ,
            HttpServletResponse response,
            @CookieValue (
                    value        = "UID" ,
                    defaultValue = "NONE"
            ) String UID
    ) {

        String AccountEmail    = request.getParameter("email"   );
        String AccountPassword = request.getParameter("password");
        String EmailNew        = request.getParameter("newEmail");

        UtilResult<AccountSecurity> result = serviceAccount.AccountLogin(AccountEmail, AccountPassword);

        if (result.getCode() != EnumResult.SUCCESS.getCode()) {

            return result.init(EnumResult.FAILED);
        }

        result = serviceAccountUpdate.UpdateAccountEmail(UID, EmailNew);

        if (result.getCode() == EnumResult.SUCCESS.getCode()) {

            int expired = UtilConvert.ConvertTime.year2sec(1);

            response.addCookie(UtilCookie.setCookie.cookieAccount.cookieSecurity(
                    "UID"               ,
                    result.getData().getUID(),
                    expired
            ));
            response.addCookie(UtilCookie.setCookie.cookieAccount.cookieSecurity(
                    "AccountEmail"               ,
                    result.getData().getAccountEmail(),
                    expired
            ));
            response.addCookie(UtilCookie.setCookie.cookieAccount.cookieSecurity(
                    "AccountPassword"               ,
                    result.getData().getAccountPassword(),
                    expired
            ));
        }

        return result;
    }

    @PostMapping("/password")
    public UtilResult<AccountSecurity> AccountUpdatePassword (
            HttpServletRequest  request ,
            HttpServletResponse response,
            @CookieValue (
                    value        = "UID" ,
                    defaultValue = "NONE"
            ) String UID
    ) {

        String AccountEmail    = request.getParameter("email"      );
        String AccountPassword = request.getParameter("password"   );
        String PasswordNew     = request.getParameter("newPassword");

        UtilResult<AccountSecurity> result = serviceAccount.AccountLogin(AccountEmail, AccountPassword);

        if (result.getCode() != EnumResult.SUCCESS.getCode()) {

            return result;
        }

        result = serviceAccountUpdate.UpdateAccountPassword(UID, PasswordNew);

        if (result.getCode() == EnumResult.SUCCESS.getCode()) {

            int expired = UtilConvert.ConvertTime.year2sec(1);

            response.addCookie(UtilCookie.setCookie.cookieAccount.cookieSecurity(
                    "UID"               ,
                    result.getData().getUID(),
                    expired
            ));
            response.addCookie(UtilCookie.setCookie.cookieAccount.cookieSecurity(
                    "AccountEmail"               ,
                    result.getData().getAccountEmail(),
                    expired
            ));
            response.addCookie(UtilCookie.setCookie.cookieAccount.cookieSecurity(
                    "AccountPassword"               ,
                    result.getData().getAccountPassword(),
                    expired
            ));
        }

        return result;
    }

}
