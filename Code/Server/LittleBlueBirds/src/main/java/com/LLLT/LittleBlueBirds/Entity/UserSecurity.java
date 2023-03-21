package com.LLLT.LittleBlueBirds.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("UserSecurity")
@Data
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
}
