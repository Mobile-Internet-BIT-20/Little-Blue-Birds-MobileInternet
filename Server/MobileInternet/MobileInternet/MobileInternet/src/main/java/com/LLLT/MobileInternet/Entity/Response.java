/*
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * 单身狗就这样默默地看着你，一句话也不说。
 */

/*
 *  com.LLLT.MobileInternet.Entity.Response.java
 *  用于响应请求的返回信息的结构
 *  Author : SeeChen Lee, ViHang Tan
 *  Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */
package com.LLLT.MobileInternet.Entity;

import java.util.List;

public class Response {

    private Integer      verifyCode      ;
    private String       responseMessage ;
    private User         userResponse    ;
    private List<String> userPostResponse;

    public Integer            getVerifyCode() { return verifyCode      ; }
    public String        getResponseMessage() { return responseMessage ; }
    public User             getUserResponse() { return userResponse    ; }
    public List<String> getUserPostResponse() { return userPostResponse; }

    public void       setVerifyCode(Integer      verifyCode      ) { this.verifyCode       = verifyCode      ; }
    public void  setResponseMessage(String       responseMessage ) { this.responseMessage  = responseMessage ; }
    public void     setUserResponse(User         userResponse    ) { this.userResponse     = userResponse    ; }
    public void setUserPostResponse(List<String> userPostResponse) { this.userPostResponse = userPostResponse; }

    public Response() {}
}
