/*  代码和人 有一个能跑就行
 *                     ,----------------,              ,---------,
 *                ,-----------------------,          ,"        ,"|
 *              ,"                      ,"|        ,"        ,"  |
 *             +-----------------------+  |      ,"        ,"    |
 *             |  .-----------------.  |  |     +---------+      |
 *             |  |                 |  |  |     | -==----'|      |
 *             |  |  I LOVE DOS!    |  |  |     |         |      |
 *             |  |  Bad command or |  |  |/----|`---=    |      |
 *             |  |  C:\>rm -rf /*  |  |  |   ,/|==== ooo |      ;
 *             |  |                 |  |  |  // |(((( [33]|    ,"
 *             |  `-----------------'  |," .;'| |((((     |  ,"
 *             +-----------------------+  ;;  | |         |,"
 *                /_)______________(_/  //'   | +---------+
 *           ___________________________/___  `,
 *          /  oooooooooooooooo  .o.  oooo /,   \,"-----------
 *         / ==ooooooooooooooo==.o.  ooo= //   ,`\--{)B     ,"
 *        /_==__==========__==_ooo__ooo=_/'   /___________,"
 *
 */
/*
 *  com.LLLT.MobileInternet.Controller.Controller.java
 *  http://IPAddress/api/ 底下的接口代码
 *  Author : SeeChen Lee, ViHang Tan
 *  Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */
package com.LLLT.MobileInternet.Controller;

import com.LLLT.MobileInternet.Entity.Post;
import com.LLLT.MobileInternet.Entity.Response;
import com.LLLT.MobileInternet.Service.PostService;
import com.LLLT.MobileInternet.Service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final UserService userService;
    private final PostService postService;
    public Controller(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @CrossOrigin
    @PostMapping("/userRegister")
    public Response userRegister(HttpServletRequest httpServletRequest) {

        /*
         *  userRegister
         *  用户注册函数
         *  请求参数:
         *      userEmail : 用户注册邮箱
         *      userPass  : 用户注册密码, 通过前端进行 MD5 加密后
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 18-Jan-2023 00:51
         * ---------------- 修改内容 -----------------------------------------
         *  18-Jan-2023 00:51
         *      1. 重写函数, 修改返回值的数据类型
         *      2. 打印请求源的 IP 地址以及请求结果
         */

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        Response registerResponse = userService.userRegister(userEmail, userPass);

        switch(registerResponse.getVerifyCode()) {

            case -1:
                System.out.println(httpServletRequest.getRemoteAddr() + ": Request Register Failed. " + registerResponse.getResponseMessage());
                break;

            case 1:
                System.out.println(httpServletRequest.getRemoteAddr() + ": Request Register Success. UID : " + registerResponse.getResponseMessage());
                break;

            default:
                break;
        }

        return registerResponse;
    }

    @CrossOrigin
    @PostMapping("/userLogin")
    public Response userLogin(HttpServletRequest httpServletRequest) {

        /*
         *  userLogin
         *  用户登录函数
         *  请求参数:
         *      userEmail : 用户注册邮箱
         *      userPass  : 用户注册密码, 通过前端进行 MD5 加密后
         *
         *  Author       : SeeChen Lee, ViHang Tan
         *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
         *  Last Modified: SeeChen Lee @ 18-Jan-2023 17：23
         * ---------------- 修改内容 -----------------------------------------
         *  18-Jan-2023 17：23
         *      1. 重写函数, 修改返回值的数据类型
         *      2. 打印请求源的 IP 地址以及请求结果
         */

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass");

        Response loginResponse = userService.userLogin(userEmail, userPass);

        switch(loginResponse.getVerifyCode()) {

            case 2:
                System.out.println(httpServletRequest.getRemoteAddr() + ": Request Login Success. UID : " + loginResponse.getResponseMessage());
                break;

            default:
                System.out.println(httpServletRequest.getRemoteAddr() + ": Request Login Failed. " + loginResponse.getResponseMessage());
                break;
        }

        return loginResponse;
    }

    @CrossOrigin
    @RequestMapping("/allPost")
    public List<Post> homePage(HttpServletRequest httpServletRequest) {

        Integer num = Integer.valueOf(httpServletRequest.getParameter("requestNum"));

        return postService.allPost(num);
    }
}