//  这就是一个 User
//                       .::::.
//                     .::::::::.
//                    :::::::::::
//                 ..:::::::::::'
//              '::::::::::::'
//                .::::::::::
//           '::::::::::::::..
//                ..::::::::::::.
//              ``::::::::::::::::
//               ::::``:::::::::'        .:::.
//              ::::'   ':::::'       .::::::::.
//            .::::'      ::::     .:::::::'::::.
//           .:::'       :::::  .:::::::::' ':::::.
//          .::'        :::::.:::::::::'      ':::::.
//         .::'         ::::::::::::::'         ``::::.
//     ...:::           ::::::::::::'              ``::.
//    ```` ':.          ':::::::::'                  ::::..
//                       '.:::::'                    ':'````..
//

/*
 *  com.LLLT.MobileInternet.Entity.User.java
 *  存储用户的数据结构
 *  Author       : SeeChen Lee, ViHang Tan
 *  Contact      : leeseechen@petalmail.com, tvhang7@gmail.com
 *  Last Modified: SeeChen Lee @ 18-Jan-2023 02:57
 *  ---------------- 修改内容 -----------------------------------------
 *  18-Jan-2023 02:57
 *      1. 新增一些变量
 *      2. 修改一些变量的命名
 */
package com.LLLT.MobileInternet.Entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    List<String> userSexList = List.of( "None"  ,   // 表示未设置
                                        "Male"  ,   // 表示男性
                                        "Female",   // 表示女性
                                        "Secrecy"); // 表示不显示

    private String       userId       ;
    private String       userEmail    ;
    private String       userPass     ;
    private String       userPhoto    ;
    private String       userName     ;
    private String       userIntro    ;
    private String       dayOfBirth   ;
    private Integer      userSexIndex ;
    private List<String> userPost     ;
    private List<String> userFollower ;
    private List<String> userFollowing;
    private List<String> userLike     ;
    private List<String> userFav      ;
    private String       joinDay      ;

    public List<String>   getUserSexList() { return userSexList  ; }
    public String              getUserId() { return userId       ; }
    public String           getUserEmail() { return userEmail    ; }
    public String            getUserPass() { return userPass     ; }
    public String           getUserPhoto() { return userPhoto    ; }
    public String            getUserName() { return userName     ; }
    public String           getUserIntro() { return userIntro    ; }
    public String          getDayOfBirth() { return dayOfBirth   ; }
    public Integer       getUserSexIndex() { return userSexIndex ; }
    public List<String>      getUserPost() { return userPost     ; }
    public List<String>  getUserFollower() { return userFollower ; }
    public List<String> getUserFollowing() { return userFollowing; }
    public List<String>      getUserLike() { return userLike     ; }
    public List<String>       getUserFav() { return userFav      ; }
    public String             getJoinDay() { return joinDay      ; }

    public void   setUserSexList(List<String> userSexList  ) { this.userSexList  = userSexList  ; }
    public void        setUserId(String       userId       ) { this.userId       = userId       ; }
    public void     setUserEmail(String       userEmail    ) { this.userEmail    = userEmail    ; }
    public void      setUserPass(String       userPass     ) {this.userPass      = userPass     ; }
    public void     setUserPhoto(String       userPhoto    ) {this.userPhoto     = userPhoto    ; }
    public void      setUserName(String       userName     ) {this.userName      = userName     ; }
    public void     setUserIntro(String       userIntro    ) {this.userIntro     = userIntro    ; }
    public void    setDayOfBirth(String       dayOfBirth   ) {this.dayOfBirth    = dayOfBirth   ; }
    public void  setUserSexIndex(Integer      userSexIndex ) {this.userSexIndex  = userSexIndex ; }
    public void      setUserPost(List<String> userPost     ) {this.userPost      = userPost     ; }
    public void  setUserFollower(List<String> userFollower ) {this.userFollower  = userFollower ; }
    public void setUserFollowing(List<String> userFollowing) {this.userFollowing = userFollowing; }
    public void      setUserLike(List<String> userLike     ) {this.userLike      = userLike     ; }
    public void       setUserFav(List<String> userFav      ) {this.userFav       = userFav      ; }
    public void       setJoinDay(String       joinDay      ) {this.joinDay       = joinDay      ; }

    public User() {}
    public User(String userEmail, String userPass) {

        this.userEmail = userEmail;
        this.userPass  = userPass;
    }
}