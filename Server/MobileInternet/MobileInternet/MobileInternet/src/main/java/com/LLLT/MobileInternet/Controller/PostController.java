// For /Post Page
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */
/*
 *      ┌─┐       ┌─┐
 *   ┌──┘ ┴───────┘ ┴──┐
 *   │                 │
 *   │       ───       │
 *   │   >        <    │
 *   │                 │
 *   │   ...  ⌒  ...   │
 *   │                 │
 *   └───┐         ┌───┘
 *       │         │
 *       │         │
 *       │         │
 *       │         └──────────────┐
 *       │                        │
 *       │                        ├─┐
 *       │                        ┌─┘
 *       │                        │
 *       └─┐  ┐  ┌───────┬──┐  ┌──┘
 *         │ ─┤ ─┤       │ ─┤ ─┤
 *         └──┴──┘       └──┴──┘
 *                神兽保佑
 *               代码无BUG!
 */

package com.LLLT.MobileInternet.Controller;

import com.LLLT.MobileInternet.Entity.Post;
import com.LLLT.MobileInternet.Service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 用户上传帖子
    // Last Modified by SeeChen Lee @ 10-Jan-2023 19:54
    @CrossOrigin
    @RequestMapping("/publish")
    public String publishPost(HttpServletRequest httpServletRequest) {

        String holderId    = httpServletRequest.getParameter("holderId");
        String postTitle   = httpServletRequest.getParameter("postTitle");
        String postContent = httpServletRequest.getParameter("content");

        return postService.publishPost(holderId, postTitle, postContent);
    }

    // 帖子内容
    // Last Modified by SeeChen Lee @ 10-Jan-2023 19:57
    @CrossOrigin
    @RequestMapping("/{postId}")
    public Post postContent(@PathVariable("postId") String postId) {

        return postService.postContent(postId);
    }
}
