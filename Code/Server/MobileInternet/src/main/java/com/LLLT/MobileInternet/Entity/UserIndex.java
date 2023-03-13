package com.LLLT.MobileInternet.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("UserIndex")
public class UserIndex implements Serializable {

    private String userId      ;
    private String userEmail    ;
    private String userPassword;

    public String getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserIndex() {}

    public UserIndex(String userId, String userEmail, String userPassword) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
