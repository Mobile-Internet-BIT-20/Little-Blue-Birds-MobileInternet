// Web Page
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com. tvhang7@gmail.com
 */
/*
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 *  O\ = /O
 * ___/`---'\____
 * .   ' \\| |// `.
 * / \\||| : |||// \
 * / _||||| -:- |||||- \
 * | | \\\ - /// | |
 * | \_| ''\---/'' | |
 * \ .-\__ `-` ___/-. /
 * ___`. .' /--.--\ `. . __
 * ."" '< `.___\_<|>_/___.' >'"".
 * | | : `- \`.;`\ _ /`;.`/ - ` : | |
 * \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 *          .............................................
 *           佛曰：bug泛滥，我已瘫痪！
 */

package com.LLLT.MobileInternet.Controller;

import com.LLLT.MobileInternet.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebPage {

    private final UserService userService;
    public WebPage(UserService userService) { this.userService = userService; }

    // MainPage 函数 网页版默认的主页面
    // Last Modified by SeeChen Lee @ 11-Jan-2023 09:56
    @GetMapping("/")
    public String MainPage() {

        return "index";
    }

    // MainPage 函数 所有内容的默认主页
    // Last Modified by SeeChen Lee @ 13-Jan-2023 01:39
    @GetMapping("/home")
    public String HomePage() {

        return "home";
    }

    // Register 页面
    // Last Modified by SeeChen Lee @ 11-Jan-2023 17:47
    @GetMapping("/register")
    public String RegisterPage() {

        return "register";
    }

    // Login 页面
    // Last Modified by SeeChen Lee @ 14-Jan-2023 10:32
    @GetMapping("/login")
    public String LoginPage(@CookieValue(value = "userEmail", defaultValue = "null") String userEmail,
                            @CookieValue(value = "userPass" , defaultValue = "null") String userPass,
                            @CookieValue(value = "userId"   , defaultValue = "null") String userId) {

        if (userService.userLogin(userEmail, userPass).equals(userId)) {

            return "home";
        } else {

            return "login";
        }
    }

    // 用户个人主页面
    // Last Modified by SeeChen Lee @ 10:52
    @GetMapping("/{userId}")
    public String userPage(@CookieValue(value = "userEmail", defaultValue = "null") String userEmail,
                           @CookieValue(value = "userPass" , defaultValue = "null") String userPass,
                           @CookieValue(value = "userId"   , defaultValue = "null") String userId,
                           @PathVariable("userId") String targetId) {

        if (userService.userLogin(userEmail, userPass).equals(userId) && userId.equals(targetId)) {

            return "userSpace/self";
        } else {

            return "userSpace/visitor";
        }
    }
}
