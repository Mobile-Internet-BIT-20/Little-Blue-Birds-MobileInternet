package com.LLLT.LittleBlueBirds.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("AccountSecurity")
public class AccountSecurity {

    @Id
    private String UID            ;
    private String AccountEmail   ;
    private String AccountPassword;

    public AccountSecurity () {}
    public AccountSecurity (String UID, String UserEmail, String UserPassword) {

        this.UID             = UID         ;
        this.AccountEmail    = UserEmail   ;
        this.AccountPassword = UserPassword;
    }
}