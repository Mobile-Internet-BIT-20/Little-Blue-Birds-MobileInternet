// Controller
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */

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
package com.LLLT.MobileInternet.Controller;

import com.LLLT.MobileInternet.Entity.Post;
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
    @RequestMapping("/newPost")
    public List<Post> homePage(HttpServletRequest httpServletRequest) {

        Integer num = Integer.valueOf(httpServletRequest.getParameter("requestNum"));

        return postService.allPost(num);
    }

    // 注册函数
    // Modified by SeeChen Lee @ 09-Jan-2023 19:04
    @CrossOrigin
    @PostMapping("/register")
    public String userRegister(HttpServletRequest httpServletRequest) {

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        if (userService.emailExists(userEmail)) {

            // 若用户存在 则返回信息
            return "UserExists";
        } else {

            return userService.createUser(userEmail, userPass);
        }
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