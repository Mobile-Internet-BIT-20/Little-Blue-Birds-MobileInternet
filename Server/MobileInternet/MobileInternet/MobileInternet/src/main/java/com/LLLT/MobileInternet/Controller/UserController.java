// All about Controller for /user page
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com,
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 用户主页
    // Last Modified by SeeChen Lee @ 10-Jan-2023 11:20
    @RequestMapping("/{userId}")
    public User UserSpace(@PathVariable("userId") String userId) {

        return userService.getUserInfo(userId);
    }

    // 获取用户的帖子
    // Last Modified by SeeChen Lee @ 10-Jan-2023 11:59
    @RequestMapping("/{userId}/post")
    public List<String> test(@PathVariable("userId") String code) {

        return userService.getUserInfo(code).getUserPost();
    }

    // 用户删除
    // Last Modified by ViHang Tan @ 10-Jan-2023 14:22
    @RequestMapping("/{userId}/delete")
    public String UserDelete(@PathVariable("userId") String userId){

        //可以添加验证那些在这里
        return userService.userDelete(userId);
    }

    // 更新用户基本信息
    // Last Modified by ViHang Tan @ 10-Jan-2023 19:56
    @CrossOrigin
    @GetMapping("/update")
    public Boolean UserUpdate(HttpServletRequest httpServletRequest){
        String userId = httpServletRequest.getParameter("userId");
        String userName = httpServletRequest.getParameter("userName");
        String dob = httpServletRequest.getParameter("dob");
        Integer sex = Integer.valueOf(httpServletRequest.getParameter("sex"));

        User user = new User();
        user.setUserName(userName);
        user.setUserId(userId);
        user.setUserSexIndex(sex);
        user.setDayOfBirth(dob);

        return userService.updateUserBasicInfo(user);
    }

    // 更新用户密码
    // Last Modified by ViHang Tan @ 10-Jan-2023 19:56
    @PostMapping("/update/password")
    public Boolean updateUserPassword(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("userId");
        String password = httpServletRequest.getParameter("userPass");

        User user = new User();
        user.setUserPass(password);
        user.setUserId(id);

        return userService.updateUserPassword(user);
    }

    // 更新用户邮箱
    // Last Modified by ViHang Tan @ 10-Jan-2023 19:56
    @PostMapping("/update/email")
    public Boolean updateUserEmail(HttpServletRequest httpServletRequest){
        String id = httpServletRequest.getParameter("userId");
        String email = httpServletRequest.getParameter("email");

        User user = new User();
        user.setEmail(email);
        user.setUserId(id);

        return userService.updateUserEmail(user);
    }

    // 关注其他用户
    // Last Modified by ViHang Tan @ 10-Jan-2023 20:40
    @GetMapping("/follow")
    public Boolean userFollow(HttpServletRequest httpServletRequest){
        String myId = httpServletRequest.getParameter("myUserId");
        String followId = httpServletRequest.getParameter("followUserId");

        return userService.updateFollow(myId,followId);
    }
}
