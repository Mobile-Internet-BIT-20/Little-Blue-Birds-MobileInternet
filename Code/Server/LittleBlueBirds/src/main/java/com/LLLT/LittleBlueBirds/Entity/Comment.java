package com.LLLT.LittleBlueBirds.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("Comment")
@Data
public class Comment implements Serializable {

    private String CommentId;
    private String UserId   ;
    private String Content  ;

    public Comment () {}
    public Comment (String CommentId, String UserId, String Content) {
        this.CommentId = CommentId;
        this.UserId    = UserId   ;
        this.Content   = Content  ;
    }

    // Getter and Setter


    public String getCommentId() {
        return CommentId;
    }

    public void setCommentId(String commentId) {
        CommentId = commentId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
