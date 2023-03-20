package com.LLLT.LittleBlueBirds.Service.Imp;

import com.LLLT.LittleBlueBirds.DAO.PostDAO;
import com.LLLT.LittleBlueBirds.Entity.Post;
import com.LLLT.LittleBlueBirds.Service.PostService;
import com.LLLT.LittleBlueBirds.Util.Result;
import com.LLLT.LittleBlueBirds.Util.UploadFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Service("PostService")
public class PostImp implements PostService {

    private final MongoTemplate mongoTemplate;
    public PostImp (MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Value("${web.upload-path}") private String UploadPath;
    @Override
    public Result<Post> PublishPost(String HolderId, String PostTitle, String PostContent, MultipartFile[] PostImg) {

        Result<Post> result = new Result<>();

        PostDAO postDAO = new PostDAO(mongoTemplate);

        String PostId = "P" + new ObjectId();
        List<String> ImgList = Collections.emptyList();

        if (PostImg.length != 0) {

            ImgList = new UploadFile.UploadPicture().Multiple(PostImg, UploadPath, HolderId + "/" + PostId).getData();
        }

        Post post = new Post(PostId, HolderId, PostTitle, PostContent, ImgList).init();

        postDAO.Save(post);

        return result.init(Result.CodeEnum.SUCCESS, post);
    }
}
