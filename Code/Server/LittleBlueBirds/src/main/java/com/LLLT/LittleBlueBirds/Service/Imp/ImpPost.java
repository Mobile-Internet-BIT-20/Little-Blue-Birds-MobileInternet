package com.LLLT.LittleBlueBirds.Service.Imp;

import com.LLLT.LittleBlueBirds.DAO.DAOAccountBasic;
import com.LLLT.LittleBlueBirds.DAO.DAOPost;
import com.LLLT.LittleBlueBirds.Entity.Post;
import com.LLLT.LittleBlueBirds.Enum.EnumResult;
import com.LLLT.LittleBlueBirds.Service.ServicePost;
import com.LLLT.LittleBlueBirds.Util.UtilFile;
import com.LLLT.LittleBlueBirds.Util.UtilResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Service("ServicePost")
public class ImpPost implements ServicePost {

    private final MongoTemplate mongoTemplate;
    public ImpPost (
            MongoTemplate mongoTemplate
    ) {
        this.mongoTemplate = mongoTemplate;
    }

    @Value("${web.upload-path}") private String UploadPath;

    @Override
    public UtilResult<Post> PostPublish (
            String HolderId   ,
            String PostTitle  ,
            String PostContent,
            MultipartFile[] ListPostImg
    ) {

        UtilResult<Post> result = new UtilResult<>();

        DAOPost         DAOPost         = new DAOPost(mongoTemplate);
        DAOAccountBasic DAOAccountBasic = new DAOAccountBasic(mongoTemplate);

        String PID = "P" + new ObjectId();

        Post post = new Post(PID, HolderId, PostTitle, PostContent).init();
        List<String> ImgList = Collections.emptyList();

        if (ListPostImg.length != 0) {

            ImgList = new UtilFile.UploadPicture().Multiple(ListPostImg, UploadPath, HolderId + "/" + PID).getData();
        }

        post.setListPostImg(ImgList);

        DAOAccountBasic.update(HolderId, "NumPost", String.valueOf(DAOAccountBasic.findOneById(HolderId).getNumPost() + 1));
        DAOPost.save(post);

        return result;
    }

    @Override
    public UtilResult<Post> PostModify (
            String PID        ,
            String UID        ,
            String PostTitle  ,
            String PostContent,
            MultipartFile[] multipartFiles
    ) {

        UtilResult<Post> result = new UtilResult<>();

        DAOPost DAOPost = new DAOPost(mongoTemplate);

        Post post = DAOPost.findOneById(PID, UID);

        new UtilFile.DropFile().AllSub(UploadPath + UID + "/" + PID);

        List<String> ImgList = Collections.emptyList();

        if (multipartFiles.length != 0) {

            ImgList = new UtilFile.UploadPicture().Multiple(multipartFiles, UploadPath, UID + "/" + PID).getData();
        }

        post.setPostTitle(PostTitle);
        post.setPostContent(PostContent);
        post.setListPostImg(ImgList);

        post.setTimeLastModify(new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Timestamp(System.currentTimeMillis())));

        DAOPost.update(post);

        return result.init(EnumResult.SUCCESS, DAOPost.findOneById(PID, UID));
    }
}
