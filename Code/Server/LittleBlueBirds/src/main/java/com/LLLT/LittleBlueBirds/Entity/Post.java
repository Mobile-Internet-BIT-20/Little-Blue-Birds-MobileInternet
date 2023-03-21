package com.LLLT.LittleBlueBirds.Entity;

import com.LLLT.LittleBlueBirds.Util.PrivacyEnum;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Document("Post")
@Data
public class Post implements Serializable {

    private String PostId     ;
    private String Holder     ;
    private String Title      ;
    private String Content    ;
    private String PublishTime;

    private List<String> Img        ;
    private List<String> LikeUser   ;
    private List<String> CollectUser;
    private List<String> CommentList;

    private Integer LikeNum   ;
    private Integer CollectNum;
    private Integer CommentNum;

    private Integer PostPrivacy;

    public Post () {}

    public Post(String postId, String holder, String title, String content, List<String> Img) {
        this.PostId  = postId ;
        this.Holder  = holder ;
        this.Title   = title  ;
        this.Content = content;
        this.Img     = Img    ;
    }

    public Post init () {

        this.PublishTime = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
        this.LikeUser = this.CollectUser = this.CommentList = Collections.emptyList();
        this.LikeNum = this.CollectNum = this.CommentNum = 0;
        this.PostPrivacy = PrivacyEnum.PUBLIC.getCode();

        return this;
    }
}
