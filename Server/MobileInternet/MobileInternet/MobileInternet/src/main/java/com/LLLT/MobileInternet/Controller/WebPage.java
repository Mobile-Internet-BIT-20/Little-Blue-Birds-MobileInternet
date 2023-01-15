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

import com.LLLT.MobileInternet.Entity.Post;
import com.LLLT.MobileInternet.Service.PostService;
import com.LLLT.MobileInternet.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebPage {

    private final UserService userService;
    private final PostService postService;
    public WebPage(UserService userService, PostService postService) {

        this.userService = userService;
        this.postService = postService;
    }

    // MainPage 函数 网页版默认的主页面
    // Last Modified by SeeChen Lee @ 11-Jan-2023 09:56
    @GetMapping("/")
    public String MainPage(HttpServletRequest httpServletRequest) {

        System.out.println(httpServletRequest.getRemoteAddr());

        return "index";
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

    // 帖子页面
    // Last Modified by ViHang Tan @ 17:12
    @GetMapping("/post/{postId}")
    public String postPage(@CookieValue(value = "userId"   , defaultValue = "null") String userId,
                           @PathVariable("postId") String targetId) {

        if (postService.publisherId(targetId).equals(userId)) {
            return "postSpace/self";
        } else {
            return "postSpace/visitor";
        }
    }

    //更改帖子页面
    @GetMapping("/post/{postId}/edit")
    public String editPostPage(@PathVariable("postId") String postId){
        return  "postSpace/edit";
    }

    //新建帖子页面
    // Last Modified by ViHang Tan @ 17:54
    @GetMapping("/home")
    public String newPostPage(@CookieValue(value = "userId", defaultValue = "null") String userId){

        return "home";
    }

    //分享帖子页面
    // Last Modified by ViHang Tan @ 10:23
    @GetMapping("/publish")
    public String publishPage(@CookieValue(value = "userId"   , defaultValue = "null") String userId){
        if(userId.substring(0,1) == "null"){
            return "login";
        }else{
            return "publish";
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
