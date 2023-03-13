package com.LLLT.MobileInternet.Controller;

import com.LLLT.MobileInternet.Entity.User;
import com.LLLT.MobileInternet.Entity.UserIndex;
import com.LLLT.MobileInternet.Service.UserUpdateService;
import com.LLLT.MobileInternet.Util.Convert.Time;
import com.LLLT.MobileInternet.Util.Result.Result;
import com.LLLT.MobileInternet.Util.Result.ResultCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/user/update")
public class UserUpdateController {

    private final UserUpdateService userUpdateService;
    public UserUpdateController(UserUpdateService userUpdateService) {

        this.userUpdateService = userUpdateService;
    }

    @PostMapping("/BasicInfo")
    public Result<User> UpdateBasicInfo(HttpServletRequest httpServletRequest,
                                        @CookieValue(value = "userId", defaultValue = "None") String userId
    ) {

        String userEmail    = httpServletRequest.getParameter("userEmail"   );
        String userPassword = httpServletRequest.getParameter("userPassword");

        String userName     = httpServletRequest.getParameter("userName"    );
        String userIntro    = httpServletRequest.getParameter("userIntro"   );
        String userBirthDay = httpServletRequest.getParameter("userBirthDay");
        String userSex      = httpServletRequest.getParameter("userSex"     );

        String[] updateMsg = {userName, userIntro, userBirthDay, userSex};

        return userUpdateService.updateBasicInfo(userId, userEmail, userPassword, updateMsg);
    }

    @PostMapping("/email")
    public Result<UserIndex> UpdateEmail(HttpServletRequest  httpServletRequest ,
                                         HttpServletResponse httpServletResponse,
                                         @CookieValue(value = "userId", defaultValue = "None") String userId
    ) {

        String oldEmail = httpServletRequest.getParameter("oldEmail"    );
        String newEmail = httpServletRequest.getParameter("newEmail"    );
        String password = httpServletRequest.getParameter("userPassword");

        Result<UserIndex> result = userUpdateService.updateEmail(userId, oldEmail, newEmail, password);

        if (result.getResultCode() == ResultCode.UPDATE_SUCCESS.getCode()) {

            Cookie cookie = new Cookie("userEmail", result.getData().getUserEmail()) {{
                setPath("/");
                setMaxAge(Time.year2sec(1));
                setHttpOnly(true);
            }};

            httpServletResponse.addCookie(cookie);
        }

        return result;
    }

    @PostMapping("/password")
    public Result<UserIndex> UpdatePassword(HttpServletRequest  httpServletRequest ,
                                            HttpServletResponse httpServletResponse,
                                            @CookieValue(value = "userId", defaultValue = "None") String userId
    ) {

        String userEmail   = httpServletRequest.getParameter("userEmail"  );
        String oldPassword = httpServletRequest.getParameter("oldPassword");
        String newPassword = httpServletRequest.getParameter("newPassword");

        Result<UserIndex> result = userUpdateService.updatePassword(userId, userEmail, oldPassword, newPassword);

        if (result.getResultCode() == ResultCode.UPDATE_SUCCESS.getCode()) {

            Cookie cookie = new Cookie("userPassword", result.getData().getUserPassword()){{
                setPath("/");
                setMaxAge(Time.year2sec(1));
                setHttpOnly(true);
            }};

            httpServletResponse.addCookie(cookie);
        }

        return result;
    }

    @RequestMapping("/photo")
    public Result<String> UpdatePhoto(@RequestParam("userPhoto") MultipartFile multipartFile,
                                      HttpServletRequest httpServletRequest
    ) {

        Cookie[] cookies = httpServletRequest.getCookies();

        HashMap<String, String> hashMap = new HashMap<String, String>() {{
            put("userId"      , "");
            put("userEmail"   , "");
            put("userPassword", "");
        }};

        for (Cookie c : cookies) hashMap.replace(c.getName(), c.getValue());

        Result<String> result = userUpdateService.updatePhoto(hashMap.get("userId"), hashMap.get("userEmail"), hashMap.get("userPassword"), multipartFile);

        return result;
    }
}
