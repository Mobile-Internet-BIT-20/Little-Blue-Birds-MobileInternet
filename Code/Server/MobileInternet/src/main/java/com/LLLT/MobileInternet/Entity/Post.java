package com.LLLT.MobileInternet.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("Post")
public class Post implements Serializable {

    private String postId;
    private String postTitle;
    private String postContent;
    private String holderId;
    private List<String> postImg;
    private Integer likeNum;
    private Integer commentNum;
    private Integer collectNum;
    private List<String> likeUser;
    private List<String> collectUser;
    private List<Comment> comments;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public List<String> getPostImg() {
        return postImg;
    }

    public void setPostImg(List<String> postImg) {
        this.postImg = postImg;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public List<String> getLikeUser() {
        return likeUser;
    }

    public void setLikeUser(List<String> likeUser) {
        this.likeUser = likeUser;
    }

    public List<String> getCollectUser() {
        return collectUser;
    }

    public void setCollectUser(List<String> collectUser) {
        this.collectUser = collectUser;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post() {}
    public Post(String postId) {

        this.postId = postId;
    }
    public Post init() {

        return this;
    }
}
