package com.LLLT.LittleBlueBirds.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Document("Post")
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

        return this;
    }

    // Getter and Setter

    public String getPostId() {
        return PostId;
    }

    public void setPostId(String postId) {
        PostId = postId;
    }

    public String getHolder() {
        return Holder;
    }

    public void setHolder(String holder) {
        Holder = holder;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }

    public List<String> getImg() {
        return Img;
    }

    public void setImg(List<String> img) {
        Img = img;
    }

    public List<String> getLikeUser() {
        return LikeUser;
    }

    public void setLikeUser(List<String> likeUser) {
        LikeUser = likeUser;
    }

    public List<String> getCollectUser() {
        return CollectUser;
    }

    public void setCollectUser(List<String> collectUser) {
        CollectUser = collectUser;
    }

    public List<String> getCommentList() {
        return CommentList;
    }

    public void setCommentList(List<String> commentList) {
        CommentList = commentList;
    }

    public Integer getLikeNum() {
        return LikeNum;
    }

    public void setLikeNum(Integer likeNum) {
        LikeNum = likeNum;
    }

    public Integer getCollectNum() {
        return CollectNum;
    }

    public void setCollectNum(Integer collectNum) {
        CollectNum = collectNum;
    }

    public Integer getCommentNum() {
        return CommentNum;
    }

    public void setCommentNum(Integer commentNum) {
        CommentNum = commentNum;
    }
}
