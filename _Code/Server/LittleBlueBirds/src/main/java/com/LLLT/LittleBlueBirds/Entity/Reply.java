package com.LLLT.LittleBlueBirds.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Data
@Document("Reply")
public class Reply implements Serializable {

    @Id
    private String ReplyId     ;
    private String HolderId    ;
    private String ReplyContent;

    private String TimePublish;

    private Integer NumLike  ;
    private Integer NumUnlike;

    public Reply () {}

    public Reply (
            String ReplyId     ,
            String HolderId    ,
            String ReplyContent
    ) {

        this.ReplyId      = ReplyId     ;
        this.HolderId     = HolderId    ;
        this.ReplyContent = ReplyContent;
    }

    public Reply init () {

        this.TimePublish = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
        this.NumLike = this.NumUnlike = 0;

        return this;
    }
}
