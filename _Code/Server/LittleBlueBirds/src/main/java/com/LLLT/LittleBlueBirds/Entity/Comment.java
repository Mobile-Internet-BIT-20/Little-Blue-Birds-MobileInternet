package com.LLLT.LittleBlueBirds.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Data
@Document("Comment")
public class Comment implements Serializable {

    @Id
    private String CommentId     ;
    private String HolderId      ;
    private String CommentContent;

    private String TimePublish;

    private List<Reply> ListReply;

    private Integer NumReply ;
    private Integer NumLike  ;
    private Integer NumUnlike;

    public Comment () {}

    public Comment (
            String CommentId     ,
            String HolderId      ,
            String CommentContent
    ) {

        this.CommentId      = CommentId     ;
        this.HolderId       = HolderId      ;
        this.CommentContent = CommentContent;
    }

    public Comment init () {

        this.TimePublish = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
        this.ListReply = Collections.emptyList();
        this.NumReply = this.NumLike = this.NumUnlike = 0;

        return this;
    }
}
