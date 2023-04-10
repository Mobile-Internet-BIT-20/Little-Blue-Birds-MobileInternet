package com.LLLT.LittleBlueBirds.Controller;

import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import com.LLLT.LittleBlueBirds.Service.ServiceAccount;
import com.LLLT.LittleBlueBirds.Util.Convert;
import com.LLLT.LittleBlueBirds.Util.CookieOP;
import com.LLLT.LittleBlueBirds.Util.Result;
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
    public Result<AccountSecurity> AccountRegister (
            HttpServletRequest request
    ) {

        String AccountEmail    = request.getParameter("email"   );
        String AccountPassword = request.getParameter("password");

        return serviceAccount.AccountRegister(AccountEmail, AccountPassword);
    }

    @PostMapping("/login")
    public Result<AccountSecurity> AccountLogin (
            HttpServletRequest  request ,
            HttpServletResponse response
    ) {

        String AccountEmail    = request.getParameter("email"   );
        String AccountPassword = request.getParameter("password");

        Result<AccountSecurity> result = serviceAccount.AccountLogin(AccountEmail, AccountPassword);

        if (result.getCode() == Result.CodeEnum.SUCCESS.getCode()) {

            int expired = Convert.Time.year2sec(1);

            response.addCookie(CookieOP.setCookie.CookieAccount.CookieSecurity("UID"            , result.getData().getUID()            , expired));
            response.addCookie(CookieOP.setCookie.CookieAccount.CookieSecurity("AccountEmail"   , result.getData().getAccountEmail()   , expired));
            response.addCookie(CookieOP.setCookie.CookieAccount.CookieSecurity("AccountPassword", result.getData().getAccountPassword(), expired));
        }

        return result;
    }

    @PostMapping("/delete")
    public Result<String> AccountDelete (
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

        Result<String> result = serviceAccount.AccountDelete(UID, AccountEmail, AccountPassword, UserName);

        if (result.getCode() == Result.CodeEnum.SUCCESS.getCode()) {

            response.addCookie(CookieOP.setCookie.CookieAccount.CookieSecurity("UID"            , null, 0));
            response.addCookie(CookieOP.setCookie.CookieAccount.CookieSecurity("AccountEmail"   , null, 0));
            response.addCookie(CookieOP.setCookie.CookieAccount.CookieSecurity("AccountPassword", null, 0));
        }

        return result;
    }
}
