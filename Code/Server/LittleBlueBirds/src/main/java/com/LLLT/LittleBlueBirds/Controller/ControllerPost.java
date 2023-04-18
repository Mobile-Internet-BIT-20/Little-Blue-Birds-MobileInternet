package com.LLLT.LittleBlueBirds.Controller;

import com.LLLT.LittleBlueBirds.Entity.AccountSecurity;
import com.LLLT.LittleBlueBirds.Entity.Post;
import com.LLLT.LittleBlueBirds.Enum.EnumResult;
import com.LLLT.LittleBlueBirds.Service.ServiceAccount;
import com.LLLT.LittleBlueBirds.Service.ServicePost;
import com.LLLT.LittleBlueBirds.Util.UtilCookie;
import com.LLLT.LittleBlueBirds.Util.UtilResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RequestMapping("/api/post")
@RestController
public class ControllerPost {

    private final ServiceAccount serviceAccount;
    private final ServicePost    servicePost   ;
    public ControllerPost (
            ServiceAccount serviceAccount,
            ServicePost    servicePost
    ) {
        this.serviceAccount = serviceAccount;
        this.servicePost    = servicePost   ;
    }

    @PostMapping("/publish")
    public UtilResult<Post> PostPublish (
            HttpServletRequest  request ,
            @RequestParam (
                    value    = "PostImg",
                    required = false
            ) MultipartFile[] multipartFiles
    ) {

        UtilResult<Post> result = new UtilResult<>();

        HashMap<String, String> hashMap = UtilCookie.getCookie.cookieAccount.cookieSecurity(request.getCookies());
        UtilResult<AccountSecurity> accountSecurityUtilResult = serviceAccount.AccountLogin(hashMap.get("AccountEmail"), hashMap.get("AccountPassword"));

        if (accountSecurityUtilResult.getCode() != EnumResult.SUCCESS.getCode()) {

            return result.init(EnumResult.FAILED);
        }

        String UID         = hashMap.get("UID")                ;
        String PostTitle   = request.getParameter("title")  ;
        String PostContent = request.getParameter("content");

        MultipartFile[] PostImg = {};

        if (multipartFiles != null) {

            PostImg = multipartFiles;
        }

        return servicePost.PostPublish(UID, PostTitle, PostContent, PostImg);
    }

    @PostMapping("/modify")
    public UtilResult<Post> PostModify (
            HttpServletRequest request,
            @RequestParam (
                    value    = "PID",
                    required = true
            ) String PID,
            @RequestParam (
                    value    = "PostImg",
                    required = false
            ) MultipartFile[] multipartFiles
    ) {

        UtilResult<Post> result = new UtilResult<>();

        HashMap<String, String> hashMap = UtilCookie.getCookie.cookieAccount.cookieSecurity(request.getCookies());
        UtilResult<AccountSecurity> accountSecurityUtilResult = serviceAccount.AccountLogin(hashMap.get("AccountEmail"), hashMap.get("AccountPassword"));

        if (accountSecurityUtilResult.getCode() != EnumResult.SUCCESS.getCode()) {

            return result.init(EnumResult.FAILED);
        }

        String UID         = hashMap.get("UID")                ;
        String PostTitle   = request.getParameter("title")  ;
        String PostContent = request.getParameter("content");

        MultipartFile[] PostImg = {};

        if (multipartFiles != null) {

            PostImg = multipartFiles;
        }

        return servicePost.PostModify(PID, UID, PostTitle, PostContent, PostImg);
    }
}
