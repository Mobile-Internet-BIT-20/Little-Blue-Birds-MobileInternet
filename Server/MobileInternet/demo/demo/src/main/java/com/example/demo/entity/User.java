package com.example.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User {

    private Integer UserId;
    private String  UserName;

    public Integer getUserId() {

        return this.UserId;
    }

    public String getUserName() {

        return this.UserName;
    }

    public void setUserId(Integer userId) {

        this.UserId = userId;
    }

    public void setUserName(String userName) {

        this.UserName = userName;
    }

    public User() {}

    public User(Integer userId, String userName) {
        this.UserId = userId;
        this.UserName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                '}';
    }
}
