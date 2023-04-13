package com.LLLT.LittleBlueBirds.Entity;

import com.LLLT.LittleBlueBirds.Enum.EnumPrivacy;
import com.LLLT.LittleBlueBirds.Enum.EnumSex;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Data
@Document("AccountBasic")
public class AccountBasic implements Serializable {

    @Id
    private String  UID           ;
    private String  UserName      ;
    private String  UserIntro     ;
    private String  ProfilePicture;
    private Integer UserSex       ;

    private String DayBirth;
    private String DayJoin ;

    private Integer NumPost     ;
    private Integer NumFollower ;
    private Integer NumFollowing;
    private Integer NumLike     ;
    private Integer NumCollect  ;

    private Integer PrivacyFollower ;
    private Integer PrivacyFollowing;
    private Integer PrivacyLike     ;
    private Integer PrivacyCollect  ;

    private List<String> ListFollower ;
    private List<String> ListFollowing;
    private List<String> ListLike     ;
    private List<String> ListCollect  ;

    public AccountBasic() {}
    public AccountBasic(String UID) {
        this.UID = UID;
    }

    public void init() {

        this.UserName = this.UserIntro = this.ProfilePicture = "__DEFAULT__";
        this.DayBirth = "__DEFAULT__";
        this.DayJoin = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
        this.UserSex = EnumSex.NONE.getCode();
        this.NumPost = this.NumFollower = this.NumFollowing = this.NumCollect = 0;
        this.PrivacyFollower = this.PrivacyFollowing = this.PrivacyLike = this.PrivacyCollect = EnumPrivacy.LOGIN_USER.getCode();
        this.ListCollect = this.ListFollower = this.ListFollowing = this.ListLike = Collections.emptyList();
    }
}
