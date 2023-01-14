/*
 *  userSpace/userSpace.js
 *  个人界面的共享 js 代码
 *  Author       : SeeChen Lee
 *  Version      : 1.0.0
 *  Last Modified: 13-Jan-2023 03:29
 */

var defaultHost = "http://127.0.0.1:8080";

function getUrlUserInfo() {

    let CurrentUrl = window.location.href;
    let userId     = CurrentUrl.split('/')[3];

    let requestUrl = defaultHost + "/api/user/" + userId;

    $.get(requestUrl).done(function(data) { return data; });
}