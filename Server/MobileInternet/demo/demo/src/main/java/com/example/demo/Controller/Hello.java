package com.example.demo.Controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.entity.vuce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Hello {

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/")
    public String Hello() {

        return "Hello World";
    }

    @RequestMapping("/register")
    public String Register(HttpServletRequest httpServletRequest) {

        String userName = httpServletRequest.getParameter("name");
        String userID   = httpServletRequest.getParameter("id");

        int userId = Integer.valueOf(userID).intValue();

        User newUser = new User(userId, userName);

        System.out.println(mongoTemplate.insert(newUser));

        return newUser.toString();
    }

    @RequestMapping("/findUser")
    public List<User> findAll() {

        List<User> all = mongoTemplate.findAll(User.class);

        return all;
    }

    @RequestMapping("/post")
    public String Post(HttpServletRequest httpServletRequest) {

        String tempPostID  = httpServletRequest.getParameter("id");
        String postContent = httpServletRequest.getParameter("content");

        int postId = Integer.valueOf(tempPostID).intValue();

        Post post = new Post(postId, postContent);

        System.out.println(mongoTemplate.insert(post));

        return post.toString();
    }

    @RequestMapping("/findPost")
    public List<Post> findPost() {

        List<Post> all = mongoTemplate.findAll(Post.class);

        System.out.println(all);
        return all;
    }

    @CrossOrigin // 跨域问题
    @PostMapping("/vuce")
    public String vuce(HttpServletRequest httpServletRequest) {

        String vu = httpServletRequest.getParameter("vu");
        String ce = httpServletRequest.getParameter("ce");

        vuce vuce = new vuce(vu, ce);

        mongoTemplate.insert(vuce);

        return vuce.toString();
    }
}
