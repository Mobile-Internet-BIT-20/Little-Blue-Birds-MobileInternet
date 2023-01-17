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
import org.springframework.http.HttpHeaders;
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
    @RequestMapping("/userRegister")
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
         *      2. 打印请求源的 IP 地址
         */

        System.out.println(httpServletRequest.getRemoteAddr() + ": Request Register.");

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        return userService.userRegister(userEmail, userPass);
    }

    @CrossOrigin
    @RequestMapping("/allPost")
    public List<Post> homePage(HttpServletRequest httpServletRequest) {

        Integer num = Integer.valueOf(httpServletRequest.getParameter("requestNum"));

        return postService.allPost(num);
    }

    // 用于获取用户登录信息
    // Last Modified by SeeChen Lee @ 11-Jan-2023 06:37
    @CrossOrigin
    @PostMapping("/login")
    public String userLogin(HttpServletRequest httpServletRequest) {

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        return userService.userLogin(userEmail, userPass);
    }
}