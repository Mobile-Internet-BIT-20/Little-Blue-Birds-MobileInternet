package com.LLLT.MobileInternet.Entity;

import com.LLLT.MobileInternet.Util.Sex.SexList;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
    private int postCount     ;
    private int followerCount ;
    private int followingCount;
    private int likeCount     ;
    private int collectCount  ;

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

    public int getPostCount() {
        return postCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCollectCount() {
        return collectCount;
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

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public User() {}
    public User(String userId) {
        this.userId = userId;
    }
    public User setPrivateContent() {

        this.userCollect = this.userFollower = this.userFollowing = this.userLike = Collections.emptyList();
        return this;
    }

    public User init() {

        this.userName    = this.userIntro = this.userPhoto = this.userBirthDay = "_DEFAULT_";
        this.userSex     = SexList.NONE.getCode();
        this.userJoinDay = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
        this.userPost    = this.userCollect  = this.userFollower  = this.userFollowing  = this.userLike  = Collections.emptyList();
        this.postCount   = this.collectCount = this.followerCount = this.followingCount = this.likeCount = 0;
        return this;
    }
}