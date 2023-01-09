// Controller
/*
 *   @Author : SeeChen Lee, ViHang Tan
 *   @Contact: leeseechen@petalmail.com,
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

import com.LLLT.MobileInternet.Entity.User;
import com.LLLT.MobileInternet.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private UserService userService;

    // MainPage 函数 网页版默认主页
    // Modified by SeeChen Lee @ 09-Jan-2023 11:36
    @RequestMapping("/")
    public String MainPage() {

        return "Hello World";
    }

    // 注册函数
    // Modified by SeeChen Lee @ 09-Jan-2023 19:04
    @CrossOrigin    // 允许跨域
    @PostMapping("/register")
    public String userRegister(HttpServletRequest httpServletRequest) {

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        if (userService.emailExists(userEmail)) {

            // 若用户存在 则返回信息
            return "User Exists";
        } else {

            return userService.createUser(userEmail, userPass);
        }
    }
}