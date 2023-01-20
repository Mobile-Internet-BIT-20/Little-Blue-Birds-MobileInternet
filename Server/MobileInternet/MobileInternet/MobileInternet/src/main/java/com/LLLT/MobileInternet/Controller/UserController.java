/*  知道 Crtl, C, V 为啥不见了吗 这就是这个项目的由来
 * ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
 * │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
 * └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘
 * ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐ ┌───┬───┬───┐ ┌───┬───┬───┬───┐
 * │~ `│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp │ │Ins│Hom│PUp│ │N L│ / │ * │ - │
 * ├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤ ├───┼───┼───┤ ├───┼───┼───┼───┤
 * │ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ │ │Del│End│PDn│ │ 7 │ 8 │ 9 │   │
 * ├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤ └───┴───┴───┘ ├───┼───┼───┤ + │
 * │ Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │               │ 4 │ 5 │ 6 │   │
 * ├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤     ┌───┐     ├───┼───┼───┼───┤
 * │ Shift  │ Z │ X │   │   │ B │ N │ M │< ,│> .│? /│  Shift   │     │ ↑ │     │ 1 │ 2 │ 3 │   │
 * ├─────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤ ┌───┼───┼───┐ ├───┴───┼───┤ E││
 * │     │    │Alt │         Space         │ Alt│    │    │Ctrl│ │ ← │ ↓ │ → │ │   0   │ . │←─┘│
 * └─────┴────┴────┴───────────────────────┴────┴────┴────┴────┘ └───┴───┴───┘ └───────┴───┴───┘
 */

/*
 *  com.LLLT.MobileInternet.Controller.UserController.java
 *  http://IPAddress/api/user 底下的接口代码
 *  Author : SeeChen Lee, ViHang Tan
 *  Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */
package com.LLLT.MobileInternet.Controller;

import com.LLLT.MobileInternet.Entity.Response;
import com.LLLT.MobileInternet.Entity.User;
import com.LLLT.MobileInternet.Service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("/userDelete")
    public Response userDelete(HttpServletRequest httpServletRequest) {

        /*
         *  userDelete
         *  用户删除账号函数
         *  请求参数:
         *      userId    : 用户 ID
         *      userEmail : 用户注册邮箱
         *      userPass  : 用户注册密码, 通过前端进行 MD5 加密后
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 18-Jan-2023 20:48
         * ---------------- 修改内容 -----------------------------------------
         *  18-Jan-2023 20:48
         *      1. 重写函数, 修改返回值的数据类型
         *      2. 打印请求源的 IP 地址以及请求结果
         */

        String userId    = httpServletRequest.getParameter("userId");
        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass");

        Response deleteResponse = userService.userDelete(userId, userEmail, userPass);

        switch (deleteResponse.getVerifyCode()) {

            case 3:
                System.out.println(httpServletRequest.getRemoteAddr() + ": Request Delete " + userId + " Success.");
                break;

            default:
                System.out.println(httpServletRequest.getRemoteAddr() + ": Request Delete Failed. " + deleteResponse.getResponseMessage());
                break;
        }

        return deleteResponse;
    }

    @CrossOrigin
    @RequestMapping("/{userId}/space/all")
    public Response userSpaceAll(@PathVariable("userId") String userId, HttpServletRequest httpServletRequest) {

        /*
         *  userSpaceAll
         *  获取用户的所有信息
         *  请求参数:
         *      userEmail : 用户的邮箱
         *      userPass  : 用户的密码
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 19-Jan-2023 16:50
         * ---------------- 修改内容 -----------------------------------------
         *  19-Jan-2023 16:50
         *      1. 新增验证信息
         */

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        Response userInfo = userService.userLogin(userEmail, userPass);

        if (userInfo.getVerifyCode() == 2) {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request " + userId + " All Information Success.");

            userInfo = userService.getUserAllInfo(userId);
        } else {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request " + userId + " Information Failed. " + userInfo.getResponseMessage());
        }

        return userInfo;
    }

    @CrossOrigin
    @RequestMapping("/{userId}/space/visitor")
    public Response userSpaceVisitor(@PathVariable String userId, HttpServletRequest httpServletRequest) {

        /*
         *  userSpaceVisitor
         *  获取用户访客视角的信息
         *  请求参数:
         *      None
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 19-Jan-2023 16:50
         * ---------------- 修改内容 -----------------------------------------
         *  19-Jan-2023 16:50
         *      1. 创建此函数
         */

        Response response = userService.getVisitorInfo(userId);

        if (response.getVerifyCode() == 5) {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request " + userId + " Visitor Information Success.");
        } else {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request " + userId + " Information Failed. " + response.getResponseMessage());
        }

        return response;
    }

    @CrossOrigin
    @PostMapping("/{userId}/update/basicInfo")
    public Response updateBasicInfo(@PathVariable String userId, HttpServletRequest httpServletRequest) {

        /*
         *  updateBasicInfo
         *  更新用户的基本信息
         *  请求参数:
         *      userId      : 用户的 UID
         *      userEmail   : 用户的邮箱   (从 Cookie 获取)
         *      userPass    : 用户的密码   (从 Cookie 获取)
         *      userName    : 用户的用户名
         *      userPhoto   : 用户的个人头像
         *      userIntro   : 用户的个人简介
         *      dayOfBirth  : 用户的生日
         *      userSexIndex: 用户的性别
         *
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 20-Jan-2023 01:40
         * ---------------- 修改内容 -----------------------------------------
         *  20-Jan-2023 01:40
         *      1. 修改返回类型
         *      2. 打印请求结果
         */

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        String userName   = httpServletRequest.getParameter("userName" );
        String userPhoto  = httpServletRequest.getParameter("userPhoto" );
        String userIntro  = httpServletRequest.getParameter("userIntro" );
        String dayOfBirth = httpServletRequest.getParameter("dayOfBirth");

        Integer userSexIndex = Integer.valueOf(httpServletRequest.getParameter("userSexIndex"));

        User updateUser = new User(userEmail, userPass);

        updateUser.setUserId(userId);

        updateUser.setUserName(userName);
        updateUser.setUserPhoto(userPhoto);
        updateUser.setUserIntro(userIntro);
        updateUser.setDayOfBirth(dayOfBirth);

        updateUser.setUserSexIndex(userSexIndex);

        Response response = userService.updateUserBasicInfo(updateUser);

        if (response.getVerifyCode() == 7) {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request Update " + userId + " Basic Info Success.");
        } else {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request Update " + userId + " Basic Info Failed. " + response.getResponseMessage());
        }

        return response;
    }

    @CrossOrigin
    @PostMapping("/{userId}/update/email")
    public Response updateEmail(@PathVariable String userId, HttpServletRequest httpServletRequest) {

        /*
         *  updateEmail
         *  更新用户的电子邮箱
         *  请求参数:
         *      userId      : 用户的 UID
         *      oldEmail    : 用户旧的电子邮箱  ( 需用户自行输入 )
         *      newEmail    : 用户新的电子邮箱  ( 需用户自行输入 )
         *      userPass    : 用户的密码       ( 需用户自行输入 )
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 21-Jan-2023 01:27
         * ---------------- 修改内容 -----------------------------------------
         *  21-Jan-2023 01:27
         *      1. 修改返回类型
         *      2. 打印请求结果
         */

        String oldEmail = httpServletRequest.getParameter("oldEmail");
        String newEmail = httpServletRequest.getParameter("newEmail");
        String userPass = httpServletRequest.getParameter("userPass");

        Response response = userService.updateEmail(userId, oldEmail, newEmail, userPass);

        if (response.getVerifyCode() == 8) {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request Update " + userId + " Email Success.");
        } else {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request Update " + userId + " Email Failed. " + response.getResponseMessage());
        }

        return response;
    }

    @CrossOrigin
    @PostMapping("/{userId}/update/password")
    public Response updatePassword(@PathVariable String userId, HttpServletRequest httpServletRequest) {

        /*
         *  updatePassword
         *  更新用户的密码
         *  请求参数:
         *      userId      : 用户的 UID
         *      oldPass     : 用户的旧密码    ( 需用户自行输入 )
         *      newPass     : 用户的新密码    ( 需用户自行输入 )
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 21-Jan-2023 01:52
         * ---------------- 修改内容 -----------------------------------------
         *  21-Jan-2023 01:52
         *      1. 修改返回类型
         *      2. 打印请求结果
         */

        String oldPass = httpServletRequest.getParameter("oldPass");
        String newPass = httpServletRequest.getParameter("newPass");

        Response response = userService.updatePassword(userId, oldPass, newPass);

        if (response.getVerifyCode() == 9) {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request Update " + userId + " Password Success.");
        } else {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request Update " + userId + " Password Failed. " + response.getResponseMessage());
        }

        return response;
    }

    @CrossOrigin
    @RequestMapping("/{userId}/space/post")
    public Response getUserPost(@PathVariable String userId, HttpServletRequest httpServletRequest) {

        /*
         *  getUserPost
         *  获取用户的帖子
         *  请求参数:
         *      None
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 20-Jan-2023 01:40
         * ---------------- 修改内容 -----------------------------------------
         *  20-Jan-2023 01:40
         *      1. 修改返回类型
         *      2. 打印请求结果
         */

        Response response = userService.getUserPost(userId);

        if (response.getVerifyCode() == 6) {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request " + userId + " Post Success.");
        } else {

            System.out.println(httpServletRequest.getRemoteAddr() + ": Request " + userId + " Post Failed. " + response.getResponseMessage());
        }

        return response;
    }

    // 用户关注其它用户
    // Last Modified by SeeChen Lee @ 11-Jan-2023 08:55
    @CrossOrigin
    @RequestMapping("/follow")
    public Boolean userFollow(HttpServletRequest httpServletRequest) {

        String followerId = httpServletRequest.getParameter("followerId");
        String targetId = httpServletRequest.getParameter("targetId");

        return userService.userFollow(followerId, targetId);
    }

    // 通过用户 ID 获取用户名
    // Last Modified by SeeChen Lee @ 15-Jan-2023 10:31
    @CrossOrigin
    @GetMapping("/getUserName")
    public String getUserName(HttpServletRequest httpServletRequest){

        String userId = httpServletRequest.getParameter("userId");

        return userService.getUserName(userId);
    }
}
