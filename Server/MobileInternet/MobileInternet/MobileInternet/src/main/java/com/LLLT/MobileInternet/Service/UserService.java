// User Service Interface
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com,
 */

package com.LLLT.MobileInternet.Service;

import com.LLLT.MobileInternet.Entity.Response;
import com.LLLT.MobileInternet.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

// 定义用户接口
// Last Modified by SeeChen Lee @ 10-Jan-2023 00:10
@Service
public interface UserService {

    public Response userRegister       (String userEmail, String userPass);
    public Response userLogin          (String userEmail, String userPass);
    public Response userDelete         (String userId, String userEmail, String userPass);
    public Response getUserAllInfo     (String userId);
    public Response getVisitorInfo     (String userId);
    public Response getUserPost        (String userId);
    public Response updateUserBasicInfo(User updateMsg);
    public Response updateEmail        (String userId, String oldEmail, String newEmail, String userPass);
    public Response updatePassword     (String userId, String oldPass, String newPass);

    public void    publishPost(String userId, String postId);                       // 用户发帖函数
    //public String  updatePassword(String userId, String oldPass, String newPass);   // 用户更改密码
    public String  getUserName(String userId);                                      // 获取用户名

    public Boolean emailExists(String email);                                       // 用于判断当前邮箱是否已经注册
    public Boolean userFollow(String followerId, String targetId);                  // 用户关注其它用户
    public Boolean likePost(String userId, String postId);                          // 用户点赞帖子
}
