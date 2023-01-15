/*
 *  logout.js
 *  用户退出登录
 *  Author       : SeeChen Lee
 *  Version      : 1.0.0
 *  Last Modified: 15-Jan-2023 14:56
 */

var defaultHost = "http://127.0.0.1:8080"

function userLogout() {

    deleteCookie("userId");
    deleteCookie("userEmail");
    deleteCookie("userPass");

    window.location.href = defaultHost + "/login";
}