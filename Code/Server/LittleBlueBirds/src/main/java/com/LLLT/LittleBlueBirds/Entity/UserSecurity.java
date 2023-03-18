package com.LLLT.LittleBlueBirds.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("UserSecurity")
public class UserSecurity implements Serializable {

    private String UserId      ;
    private String UserEmail   ;
    private String UserPassword;

    public UserSecurity () {}

    public UserSecurity (String UserId, String UserEmail, String UserPassword) {
        this.UserId       = UserId      ;
        this.UserEmail    = UserEmail   ;
        this.UserPassword = UserPassword;
    }

    // Getter and Setter


    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
}
