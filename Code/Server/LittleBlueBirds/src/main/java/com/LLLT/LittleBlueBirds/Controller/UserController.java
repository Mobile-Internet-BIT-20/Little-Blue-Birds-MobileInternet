package com.LLLT.LittleBlueBirds.Controller;

import com.LLLT.LittleBlueBirds.Entity.UserSecurity;
import com.LLLT.LittleBlueBirds.Service.UserService;
import com.LLLT.LittleBlueBirds.Util.Convert;
import com.LLLT.LittleBlueBirds.Util.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result<UserSecurity> Register (
            HttpServletRequest request
    ) {

        String UserEmail    = request.getParameter("UserEmail"   );
        String UserPassword = request.getParameter("UserPassword");

        return userService.UserRegister(UserEmail, UserPassword);
    }

    @PostMapping("/login")
    public Result<UserSecurity> Login (
            HttpServletRequest  request ,
            HttpServletResponse response
    ) {

        String UserEmail    = request.getParameter("UserEmail"   );
        String UserPassword = request.getParameter("UserPassword");

        Result<UserSecurity> result = userService.UserLogin(UserEmail, UserPassword);

        if (result.getCode() == Result.CodeEnum.SUCCESS.getCode()) {

            int Expired = Convert.Time.year2sec(1);
            String Path = "/";

            Cookie cookie1 = new Cookie("UserId", result.getData().getUserId()) {{

                setHttpOnly(true);
                setPath(Path);
                setMaxAge(Expired);
            }};

            Cookie cookie2 = new Cookie("UserEmail", result.getData().getUserEmail()) {{

                setHttpOnly(true);
                setPath(Path);
                setMaxAge(Expired);
            }};

            Cookie cookie3 = new Cookie("UserPassword", result.getData().getUserPassword()) {{

                setHttpOnly(true);
                setPath(Path);
                setMaxAge(Expired);
            }};

            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
        }

        return result;
    }

    @PostMapping("/delete")
    public Result<String> Delete (
            HttpServletRequest  request ,
            HttpServletResponse response,
            @CookieValue(
                    value        = "UserId",
                    defaultValue = "None"
            ) String UserId
    ) {

        String UserEmail    = request.getParameter("UserEmail"   );
        String UserPassword = request.getParameter("UserPassword");
        String UserName     = request.getParameter("UserName"    );

        Result<String> result = userService.UserDelete(UserId, UserEmail, UserPassword, UserName);

        if (result.getCode() == Result.CodeEnum.SUCCESS.getCode()) {

            Cookie cookie1 = new Cookie("UserId", null) {{

                setMaxAge(0);
                setPath("/");
                setHttpOnly(true);
            }};

            Cookie cookie2 = new Cookie("UserEmail", null) {{

                setMaxAge(0);
                setPath("/");
                setHttpOnly(true);
            }};

            Cookie cookie3 = new Cookie("UserPassword", null) {{

                setMaxAge(0);
                setPath("/");
                setHttpOnly(true);
            }};

            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
        }

        return result;
    }
}
