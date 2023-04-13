package com.LLLT.LittleBlueBirds.Controller;

import com.LLLT.LittleBlueBirds.Service.UserUpdateService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/api/user/update")
public class UserUpdateController {

    private final UserUpdateService userUpdateService;
    public UserUpdateController (UserUpdateService userUpdateService) {
        this.userUpdateService = userUpdateService;
    }

    @PostMapping("/profilePicture")
    public Result<String> UpdateProfilePicture (
            HttpServletRequest  request ,
            @RequestParam("UserPhoto") MultipartFile multipartFile
    ) {

        HashMap<String, String> hashMap = CookieOP.UserCookie.SecurityCookie(request.getCookies());

        return userUpdateService.UpdatePicture(multipartFile, hashMap.get("UserId"), hashMap.get("UserEmail"), hashMap.get("UserPassword"));
    }

    @PostMapping("/basicInfo")
    public Result<UserBasic> UpdateBasicInfo (
            HttpServletRequest  request
    ) {

        HashMap<String, String> hashMap = CookieOP.UserCookie.SecurityCookie(request.getCookies());

        String Name     = request.getParameter("Name"    );
        String Intro    = request.getParameter("Intro"   );
        String Sex      = request.getParameter("Sex"     );
        String BirthDay = request.getParameter("BirthDay");

        String[] UpdateMsg = {Name, Intro, Sex, BirthDay};

        return userUpdateService.UpdateBasicInfo(hashMap.get("UserId"), hashMap.get("UserEmail"), hashMap.get("UserPassword"), UpdateMsg);
    }

    @PostMapping("/email")
    public Result<UserSecurity> UpdateEmail (
            HttpServletRequest  request ,
            HttpServletResponse response,
            @CookieValue(
                    value = "UserId",
                    defaultValue = "None"
            ) String UserId
    ) {

        String OldEmail     = request.getParameter("OldEmail"    );
        String NewEmail     = request.getParameter("NewEmail"    );
        String UserPassword = request.getParameter("UserPassword");

        Result<UserSecurity> result = userUpdateService.UpdateEmail(UserId, OldEmail, NewEmail, UserPassword);

        if (result.getCode() == Result.CodeEnum.SUCCESS.getCode()) {

            Cookie cookie = new Cookie("UserEmail", result.getData().getUserEmail()) {{

                setHttpOnly(true);
                setPath("/");
                setMaxAge(Convert.Time.year2sec(1));
            }};

            response.addCookie(cookie);
        }

        return result;
    }

    @PostMapping("/password")
    public Result<UserSecurity> UpdatePassword (
            HttpServletRequest  request ,
            HttpServletResponse response,
            @CookieValue(
                    value = "UserId",
                    defaultValue = "None"
            ) String UserId
    ) {

        String UserEmail   = request.getParameter("UserEmail"  );
        String OldPassword = request.getParameter("OldPassword");
        String NewPassword = request.getParameter("NewPassword");

        Result<UserSecurity> result = userUpdateService.UpdatePassword(UserId, UserEmail, OldPassword, NewPassword);

        if (result.getCode() == Result.CodeEnum.SUCCESS.getCode()) {

            Cookie cookie = new Cookie("UserPassword", result.getData().getUserEmail()) {{

                setHttpOnly(true);
                setPath("/");
                setMaxAge(Convert.Time.year2sec(1));
            }};

            response.addCookie(cookie);
        }

        return result;
    }
}
