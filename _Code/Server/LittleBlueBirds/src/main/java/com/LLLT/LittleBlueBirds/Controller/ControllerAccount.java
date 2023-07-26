package com.LLLT.LittleBlueBirds.Controller;

import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import com.LLLT.LittleBlueBirds.Enum.EnumResult;
import com.LLLT.LittleBlueBirds.Service.ServiceAccount;
import com.LLLT.LittleBlueBirds.Util.UtilConvert;
import com.LLLT.LittleBlueBirds.Util.UtilCookie;
import com.LLLT.LittleBlueBirds.Util.UtilResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/account")
public class ControllerAccount {

    private final ServiceAccount serviceAccount;
    public ControllerAccount (
            ServiceAccount serviceAccount
    ) {
        this.serviceAccount = serviceAccount;
    }

    @PostMapping("/register")
    public UtilResult<AccountSecurity> AccountRegister (
            HttpServletRequest request
    ) {

        String AccountEmail    = request.getParameter("email"   );
        String AccountPassword = request.getParameter("password");

        return serviceAccount.AccountRegister(AccountEmail, AccountPassword);
    }

    @PostMapping("/login")
    public UtilResult<AccountSecurity> AccountLogin (
            HttpServletRequest  request ,
            HttpServletResponse response
    ) {

        String AccountEmail    = request.getParameter("email"   );
        String AccountPassword = request.getParameter("password");

        UtilResult<AccountSecurity> result = serviceAccount.AccountLogin(AccountEmail, AccountPassword);

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

    @PostMapping("/delete")
    public UtilResult<String> AccountDelete (
            HttpServletRequest  request ,
            HttpServletResponse response,
            @CookieValue(
                    value        = "UID" ,
                    defaultValue = "NONE"
            ) String UID
    ) {

        String AccountEmail    = request.getParameter("email"   );
        String AccountPassword = request.getParameter("password");
        String UserName        = request.getParameter("userName");

        UtilResult<String> result = serviceAccount.AccountDelete(UID, AccountEmail, AccountPassword, UserName);

        if (result.getCode() == EnumResult.SUCCESS.getCode()) {

            response.addCookie(UtilCookie.setCookie.cookieAccount.cookieSecurity(
                    "UID",
                    null,
                    0
            ));
            response.addCookie(UtilCookie.setCookie.cookieAccount.cookieSecurity(
                    "AccountEmail",
                    null,
                    0
            ));
            response.addCookie(UtilCookie.setCookie.cookieAccount.cookieSecurity(
                    "AccountPassword",
                    null,
                    0
            ));
        }

        return result;
    }
}
