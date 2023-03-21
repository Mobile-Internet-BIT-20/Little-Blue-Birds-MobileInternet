package com.LLLT.LittleBlueBirds.Service;

import com.LLLT.LittleBlueBirds.Entity.Post;
import com.LLLT.LittleBlueBirds.Util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface PostService {

    Result<Post> PublishPost (String HolderId, String PostTitle, String PostContent, MultipartFile[] PostImg);
    Result<Post> ModifyPost  (String HolderId, String PostId, String PostTitle, String PostContent, MultipartFile[] PostImg);
}
