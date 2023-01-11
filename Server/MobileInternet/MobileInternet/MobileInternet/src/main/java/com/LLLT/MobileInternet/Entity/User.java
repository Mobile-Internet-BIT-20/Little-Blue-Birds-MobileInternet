// User
/*
 *   @Author : SeeChen Lee, ViHang Tan
 *   @Contact: leeseechen@petalmail.com,
 */

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

package com.LLLT.MobileInternet.Entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    List<String> userSexList = List.of( "None"  ,   // 表示未设置
                                        "Male"  ,   // 表示男性
                                        "Female",   // 表示女性
                                        "Secrecy"); // 表示不显示

    private String                      userId;         // 用户 ID ( 唯一值 )
    private String                      userName;       // 用户名
    private String                      userPass;       // 用户密码 MD5 加密形式
    private String                      dayOfBirth;     // 用户生日
    private Integer                     userSexIndex;   // 用户性别 暂时为 0, 1, 2, 3
    private String                      email;          // 用户邮箱
    private List<String>                userPost;       // 用户发布的帖子
    private List<UserPublicInformation> userFollower;   // 用户的粉丝列表
    private List<UserPublicInformation> userFollowing;  // 用户的关注列表

    private List<String>                likedPost;

    // getter 函数
    public List<String>                getUserSexList()   { return userSexList;   }
    public String                      getUserId()        { return userId;        }
    public String                      getUserName()      { return userName;      }
    public String                      getUserPass()      { return userPass;      }
    public String                      getDayOfBirth()    { return dayOfBirth;    }
    public Integer                     getUserSexIndex()  { return userSexIndex;  }
    public String                      getEmail()         { return email;         }
    public List<String>                getUserPost()      { return userPost;      }
    public List<UserPublicInformation> getUserFollower()  { return userFollower;  }
    public List<UserPublicInformation> getUserFollowing() { return userFollowing; }

    public List<String> getLikedPost() {
        return likedPost;
    }

    // setter 函数
    public void setUserSexList(List<String> userSexList)                    { this.userSexList  = userSexList;  }
    public void setUserId(String userId)                                    { this.userId       = userId;       }
    public void setUserName(String userName)                                { this.userName     = userName;     }
    public void setUserPass(String userPass)                                { this.userPass     = userPass;     }
    public void setDayOfBirth(String dayOfBirth)                            { this.dayOfBirth   = dayOfBirth;   }
    public void setUserSexIndex(Integer userSexIndex)                       { this.userSexIndex = userSexIndex; }
    public void setEmail(String email)                                      { this.email        = email;        }
    public void setUserPost(List<String> userPost)                          { this.userPost     = userPost;     }
    public void setUserFollower(List<UserPublicInformation> userFollower)   { this.userFollower = userFollower; }
    public void setUserFollowing(List<UserPublicInformation> userFollowing) { this.userFollowing = userFollowing; }

    public void setLikedPost(List<String> likedPost) {
        this.likedPost = likedPost;
    }

    // 构造函数
    public User() {}
    public User(String email, String userPass) {

        this.email    = email;
        this.userPass = userPass;
    }

}
