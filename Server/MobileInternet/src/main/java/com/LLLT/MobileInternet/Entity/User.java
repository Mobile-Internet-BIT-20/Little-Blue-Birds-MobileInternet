package com.LLLT.MobileInternet.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Document("User")
public class User implements Serializable {

    private String userId      ;
    private String userName    ;
    private String userPhoto   ;
    private String userIntro   ;
    private int    userSex     ;
    private String userBirthDay;
    private String userJoinDay ;
    private List<String> userPost     ;
    private List<String> userFollower ;
    private List<String> userFollowing;
    private List<String> userLike     ;
    private List<String> userCollect  ;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public String getUserIntro() {
        return userIntro;
    }

    public int getUserSex() {
        return userSex;
    }

    public String getUserBirthDay() {
        return userBirthDay;
    }

    public String getUserJoinDay() {
        return userJoinDay;
    }

    public List<String> getUserPost() {
        return userPost;
    }

    public List<String> getUserFollower() {
        return userFollower;
    }

    public List<String> getUserFollowing() {
        return userFollowing;
    }

    public List<String> getUserLike() {
        return userLike;
    }

    public List<String> getUserCollect() {
        return userCollect;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public void setUserBirthDay(String userBirthDay) {
        this.userBirthDay = userBirthDay;
    }

    public void setUserJoinDay(String userJoinDay) {
        this.userJoinDay = userJoinDay;
    }

    public void setUserPost(List<String> userPost) {
        this.userPost = userPost;
    }

    public void setUserFollower(List<String> userFollower) {
        this.userFollower = userFollower;
    }

    public void setUserFollowing(List<String> userFollowing) {
        this.userFollowing = userFollowing;
    }

    public void setUserLike(List<String> userLike) {
        this.userLike = userLike;
    }

    public void setUserCollect(List<String> userCollect) {
        this.userCollect = userCollect;
    }

    public User() {}
    public User(String userId) {
        this.userId = userId;
    }
    public User setPrivateContent() {

        this.userCollect = this.userFollower = this.userFollowing = this.userLike = Collections.emptyList();
        return this;
    }
}
