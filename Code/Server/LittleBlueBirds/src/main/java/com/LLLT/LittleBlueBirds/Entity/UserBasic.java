package com.LLLT.LittleBlueBirds.Entity;

import com.LLLT.LittleBlueBirds.Util.PrivacyEnum;
import com.LLLT.LittleBlueBirds.Util.SexEnum;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Document("UserBasic")
@Data
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
    private Integer FollowerPrivacy ;
    private Integer FollowingPrivacy;
    private Integer LikePrivacy     ;
    private Integer CollectPrivacy  ;

    public UserBasic () {}
    public UserBasic (String UserId) {
        this.UserId = UserId;
    }

    public void init () {

        this.Name = this.Intro = this.Picture = this.BirthDay = "_DEFAULT_";
        this.Sex  = SexEnum.NONE.getCode();
        this.JoinDay = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
        this.PostNum = this.FollowerNum = this.FollowingNum = this.LikeNum = this.CollectNum = 0;

        this.FollowerPrivacy = this.FollowingPrivacy = this.CollectPrivacy = this.LikePrivacy = PrivacyEnum.PRIVATE.getCode();
    }
}
