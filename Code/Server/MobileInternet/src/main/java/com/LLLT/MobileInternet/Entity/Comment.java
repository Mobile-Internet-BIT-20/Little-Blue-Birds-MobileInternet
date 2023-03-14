package com.LLLT.MobileInternet.Entity;

import java.io.Serializable;

public class Comment implements Serializable {

    private String commentUser;
    private String commentContent;

    public String getCommentUser() {
        return commentUser;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Comment() {}

    public Comment(String commentUser, String commentContent) {

        this.commentUser    = commentUser   ;
        this.commentContent = commentContent;
    }
}