// User Service Interface
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com,
 */

package com.LLLT.MobileInternet.Service;

import com.LLLT.MobileInternet.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

// 定义用户接口
// Last Modified by SeeChen Lee @ 10-Jan-2023 00:10
@Service
public interface UserService {

    public void    publishPost(String userId, String postId);                       // 用户发帖函数

    public String  createUser(String userEmail, String userPass);                   // 用于创建新用户
    public String  userLogin(String userEmail, String userPass);                    // 用户登录函数
    public String  updateEmail(String userId, String newEmail, String usePass);     // 用户更改邮箱
    public String  updatePassword(String userId, String oldPass, String newPass);   // 用户更改密码
    public String  getUserName(String userId);                                      // 获取用户名

    public String getUserName(String userId);                                        //获取用户的名字

    public User    getUserInfo(String userId);                                      // 用于获取用户资料

    public Boolean emailExists(String email);                                       // 用于判断当前邮箱是否已经注册
    public Boolean userFollow(String followerId, String targetId);                  // 用户关注其它用户
    public Boolean updateUser(User updateUser);                                     // 用于更新用户的基本资料 ( 非保密性 )
    public Boolean userDelete(String userEmail, String userPass, String userId);    // 用于删除用户账号
    public Boolean likePost(String userId, String postId);                          // 用户点赞帖子
}
