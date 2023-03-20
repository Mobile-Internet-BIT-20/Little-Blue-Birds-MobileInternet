package com.LLLT.LittleBlueBirds.Controller;

import com.LLLT.LittleBlueBirds.Entity.Post;
import com.LLLT.LittleBlueBirds.Entity.UserSecurity;
import com.LLLT.LittleBlueBirds.Service.PostService;
import com.LLLT.LittleBlueBirds.Service.UserService;
import com.LLLT.LittleBlueBirds.Util.GetCookie;
import com.LLLT.LittleBlueBirds.Util.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@CrossOrigin
@RequestMapping("/api/post")
@RestController
public class PostController {

    private final UserService userService;
    private final PostService postService;
    public PostController (
            UserService userService,
            PostService postService
    ) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/publish")
    public Result<Post> PublishPost (
            HttpServletRequest request,
            @RequestParam(
                    value = "PostImg",
                    required = false
            ) MultipartFile[] multipartFiles
    ) {

        HashMap<String, String> hashMap = GetCookie.UserCookie.SecurityCookie(request.getCookies());

        UserSecurity userSecurity = userService.UserLogin(hashMap.get("UserEmail"), hashMap.get("UserPassword")).getData();
        Result<Post> result = new Result<>();

        if (userSecurity == null) {

            return result.init(Result.CodeEnum.FAILED);
        }

        String PostTitle   = request.getParameter("PostTitle"  );
        String PostContent = request.getParameter("PostContent");

        MultipartFile[] PostImg = {};

        if (multipartFiles != null) {

            PostImg = multipartFiles;
        }

        return postService.PublishPost(hashMap.get("UserId"), PostTitle, PostContent, PostImg);
    }
}
