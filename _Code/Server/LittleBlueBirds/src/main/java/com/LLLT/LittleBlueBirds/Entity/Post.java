package com.LLLT.LittleBlueBirds.Entity;

import com.LLLT.LittleBlueBirds.Enum.EnumPrivacy;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Data
@Document("Post")
public class Post implements Serializable {

    @Id
    private String PID        ;
    private String HolderId   ;
    private String PostTitle  ;
    private String PostContent;

    private String TimePublish   ;
    private String TimeLastModify;

    private List<String> ListPostImg    ;
    private List<String> ListPostLike   ;
    private List<String> ListPostCollect;

    private List<Comment> ListComment;

    private Integer NumLike   ;
    private Integer NumUnlike ;
    private Integer NumCollect;
    private Integer NumComment;

    private Integer PrivacyPost;

    public Post () {}

    public Post (
            String PID        ,
            String HolderId   ,
            String PostTitle  ,
            String PostContent
    ) {
        this.PID         = PID        ;
        this.HolderId    = HolderId   ;
        this.PostTitle   = PostTitle  ;
        this.PostContent = PostContent;
    }

    public Post init (

    ) {

        this.TimePublish = this.TimeLastModify = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
        this.ListPostImg = this.ListPostLike = this.ListPostCollect  = Collections.emptyList();
        this.ListComment = Collections.emptyList();
        this.NumLike = this.NumUnlike = this.NumCollect = this.NumComment = 0;
        this.PrivacyPost = EnumPrivacy.PUBLIC.getCode();

        return this;
    }
}
