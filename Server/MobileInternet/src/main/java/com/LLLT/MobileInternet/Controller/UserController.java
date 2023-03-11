package com.LLLT.MobileInternet.Controller;

import com.LLLT.MobileInternet.Entity.UserIndex;
import com.LLLT.MobileInternet.Service.UserService;
import com.LLLT.MobileInternet.Util.Convert.Time;
import com.LLLT.MobileInternet.Util.Result.Result;
import com.LLLT.MobileInternet.Util.Result.ResultCode;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result<UserIndex> UserRegister(HttpServletRequest httpServletRequest) {

        String userEmail    = httpServletRequest.getParameter("userEmail");
        String userPassword = httpServletRequest.getParameter("userPassword");

        return userService.userRegister(userEmail, userPassword);
    }

    @PostMapping("/login")
    public Result<UserIndex> UserLogin(HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) {

        String userEmail    = httpServletRequest.getParameter("userEmail"   );
        String userPassword = httpServletRequest.getParameter("userPassword");

        Result<UserIndex> result = userService.userLogin(userEmail, userPassword);

        if (result.getResultCode() == ResultCode.LOGIN_SUCCESS.getCode()) {

            int Expired = Time.year2sec(1);
            String path = "/";

            Cookie cookie1 = new Cookie("userId", result.getData().getUserId());
            cookie1.setHttpOnly(true);
            cookie1.setMaxAge(Expired);
            cookie1.setPath(path);

            Cookie cookie2 = new Cookie("userEmail", result.getData().getUserEmail());
            cookie2.setHttpOnly(true);
            cookie2.setMaxAge(Expired);
            cookie2.setPath(path);

            Cookie cookie3 = new Cookie("userPassword", result.getData().getUserPassword());
            cookie3.setHttpOnly(true);
            cookie3.setMaxAge(Expired);
            cookie3.setPath(path);

            httpServletResponse.addCookie(cookie1);
            httpServletResponse.addCookie(cookie2);
            httpServletResponse.addCookie(cookie3);
        }

        return result;
    }

    @PostMapping("/delete")
    public Result<String> UserDelete(HttpServletRequest  httpServletRequest ,
                                     HttpServletResponse httpServletResponse,
                                     @CookieValue(value = "userId", defaultValue = "None") String userId
    ) {
        String userName     = httpServletRequest.getParameter("userName"    );
        String userEmail    = httpServletRequest.getParameter("userEmail"   );
        String userPassword = httpServletRequest.getParameter("userPassword");

        Result<String> result = userService.userDelete(userId, userEmail, userPassword, userName);

        if (result.getResultCode() == ResultCode.DELETE_SUCCESS.getCode()) {

            int Expired = 0;
            String path = "/";

            Cookie cookie1 = new Cookie("userId", null);
            cookie1.setHttpOnly(true);
            cookie1.setMaxAge(Expired);
            cookie1.setPath(path);

            Cookie cookie2 = new Cookie("userEmail", null);
            cookie2.setHttpOnly(true);
            cookie2.setMaxAge(Expired);
            cookie2.setPath(path);

            Cookie cookie3 = new Cookie("userPassword", null);
            cookie3.setHttpOnly(true);
            cookie3.setMaxAge(Expired);
            cookie3.setPath(path);

            httpServletResponse.addCookie(cookie1);
            httpServletResponse.addCookie(cookie2);
            httpServletResponse.addCookie(cookie3);
        }

        return result;
    }
}
