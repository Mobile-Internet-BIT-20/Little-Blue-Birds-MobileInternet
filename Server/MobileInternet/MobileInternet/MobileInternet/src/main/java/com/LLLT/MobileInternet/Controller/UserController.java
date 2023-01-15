// All about Controller for /user page
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */

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
package com.LLLT.MobileInternet.Controller;

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

    // 用户主页
    // Last Modified by SeeChen Lee @ 10-Jan-2023 11:20
    @CrossOrigin
    @RequestMapping("/{userId}")
    public User UserSpace(@PathVariable("userId") String userId) {

        return userService.getUserInfo(userId);
    }

    // 获取用户的帖子
    // Last Modified by SeeChen Lee @ 10-Jan-2023 11:59
    @CrossOrigin
    @RequestMapping("/{userId}/post")
    public List<String> getUserPost(@PathVariable("userId") String userId) {

        return userService.getUserInfo(userId).getUserPost();
    }

    // 删除账号
    // Last Modified by SeeChen Lee @ 11-Jan-2023 06:51
    @CrossOrigin
    @PostMapping("/{userId}/delete")
    public Boolean UserDelete(HttpServletRequest httpServletRequest, @PathVariable("userId") String userId) {

        // 用于验证账号的安全性 账号邮箱以及密码需要输入正确
        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        // 删除用户所发布的帖子 待实现 @SeeChen Lee

        return userService.userDelete(userEmail, userPass, userId);
    }

    // 更新用户的基本信息
    // Last Modified by ViHang Tan @ 10-Jan-2023 19:56
    @CrossOrigin
    @RequestMapping("/update")
    public Boolean UserUpdate(HttpServletRequest httpServletRequest) {

        String  userId     = httpServletRequest.getParameter("userId");
        String  userName   = httpServletRequest.getParameter("userName");
        String  dayOfBirth = httpServletRequest.getParameter("dob");
        Integer sexIndex   = Integer.valueOf(httpServletRequest.getParameter("sexIndex"));
        String  userIntro  = httpServletRequest.getParameter("userIntro");

        User updateUser = new User();

        updateUser.setUserId(userId);
        updateUser.setUserName(userName);
        updateUser.setDayOfBirth(dayOfBirth);
        updateUser.setUserSexIndex(sexIndex);
        updateUser.setUserIntro(userIntro);

        return userService.updateUser(updateUser);
    }

    // 更新用户邮箱
    // Last Modified by SeeChen Lee @ 11-Jan-2023 07:52
    @CrossOrigin
    @RequestMapping("/security/email")
    public String updateEmail(HttpServletRequest httpServletRequest) {

        String userId   = httpServletRequest.getParameter("userId");
        String newEmail = httpServletRequest.getParameter("newEmail");
        String userPass = httpServletRequest.getParameter("userPass");

        return userService.updateEmail(userId, newEmail, userPass);
    }

    // 更新用户的密码
    // Last Modified by SeeChen Lee @ 11-Jan-2023 07:31
    @CrossOrigin
    @PostMapping("/security/password")
    public String updatePassword(HttpServletRequest httpServletRequest) {

        String userId  = httpServletRequest.getParameter("userId" );
        String oldPass = httpServletRequest.getParameter("oldPass");
        String newPass = httpServletRequest.getParameter("newPass");

        return userService.updatePassword(userId, oldPass, newPass);
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
