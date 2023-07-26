package com.LLLT.LittleBlueBirds.Service;

import com.LLLT.LittleBlueBirds.Entity.Post;
import com.LLLT.LittleBlueBirds.Util.UtilCookie;
import com.LLLT.LittleBlueBirds.Util.UtilResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;

@Service
public interface ServicePost {

    UtilResult<Post> PostPublish (
            String HolderId   ,
            String PostTitle  ,
            String PostContent,
            MultipartFile[] ListPostImg
    );

    UtilResult<Post> PostModify (
            String PID        ,
            String UID        ,
            String PostTitle  ,
            String PostContent,
            MultipartFile[] multipartFiles
    );
}
