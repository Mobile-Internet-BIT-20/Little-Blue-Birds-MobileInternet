// Post Service Interface
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */
package com.LLLT.MobileInternet.Service;

import com.LLLT.MobileInternet.Entity.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    public String publishPost(String holderId, String postTitle, String postContent);

    public Post postContent(String postId);
}
