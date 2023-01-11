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

import com.LLLT.MobileInternet.Entity.User;
import com.LLLT.MobileInternet.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Controller {

    private final UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
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

    // 用于获取用户登录信息
    // Last Modified by SeeChen Lee @ 11-Jan-2023 06:37
    @CrossOrigin
    @RequestMapping("/login")
    public String userLogin(HttpServletRequest httpServletRequest) {

        String userEmail = httpServletRequest.getParameter("userEmail");
        String userPass  = httpServletRequest.getParameter("userPass" );

        return userService.userLogin(userEmail, userPass);
    }
}