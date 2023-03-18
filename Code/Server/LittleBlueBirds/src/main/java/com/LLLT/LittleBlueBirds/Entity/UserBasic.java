package com.LLLT.LittleBlueBirds.Entity;

import com.LLLT.LittleBlueBirds.Util.SexEnum;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Document("UserBasic")
public class UserBasic implements Serializable {

    private String  UserId  ;
    private String  Name    ;
    private String  Intro   ;
    private String  Picture ;
    private Integer Sex     ;
    private String  BirthDay;
    private String  JoinDay ;
    private Integer PostNum     ;
    private Integer FollowerNum ;
    private Integer FollowingNum;
    private Integer LikeNum     ;
    private Integer CollectNum  ;

    public UserBasic () {}
    public UserBasic (String UserId) {
        this.UserId = UserId;
    }

    public void init () {

        this.Name = this.Intro = this.Picture = this.BirthDay = "_DEFAULT_";
        this.Sex  = SexEnum.NONE.getCode();
        this.JoinDay = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
        this.PostNum = this.FollowerNum = this.FollowingNum = this.LikeNum = this.CollectNum = 0;
    }

    // Getter and Setter

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public Integer getSex() {
        return Sex;
    }

    public void setSex(Integer sex) {
        Sex = sex;
    }

    public String getBirthDay() {
        return BirthDay;
    }

    public void setBirthDay(String birthDay) {
        BirthDay = birthDay;
    }

    public String getJoinDay() {
        return JoinDay;
    }

    public void setJoinDay(String joinDay) {
        JoinDay = joinDay;
    }

    public Integer getPostNum() {
        return PostNum;
    }

    public void setPostNum(Integer postNum) {
        PostNum = postNum;
    }

    public Integer getFollowerNum() {
        return FollowerNum;
    }

    public void setFollowerNum(Integer followerNum) {
        FollowerNum = followerNum;
    }

    public Integer getFollowingNum() {
        return FollowingNum;
    }

    public void setFollowingNum(Integer followingNum) {
        FollowingNum = followingNum;
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
}
