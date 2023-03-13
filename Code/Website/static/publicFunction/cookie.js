/*
 *  cookie.js
 *  设置 Cookie 的 js 文件
 *  Author       : SeeChen Lee, ViHang Tan
 *  Version      : 1.0.0
 *  Last Modified: 15-Jan-2023 09:35
 */
function getCookie(key){

    const cookieDecoded = decodeURIComponent(document.cookie);
    const cookieArr     = cookieDecoded.split(";");

    let result = "NoResult";
    let name   = key + "=";

    for (let i = 0; i < cookieArr.length; i++) {

        let cookie = cookieArr[i].trim();

        if (cookie.indexOf(name) === 0) {

            result = cookie.substring(name.length, cookie.length);
        }
    }

    return result;
}

function deleteCookie(key){

    setCookie(key, null, null);
}

function setCookie(key, value, day) {

    let date = new Date();

    date.setTime(date.getTime() +  (day * 24 * 60 * 60 * 1000));
    let expires = "expires=" + date.toUTCString() + ";";
    let cookie  = key + "=" + value + ";"

    document.cookie = cookie + expires;
}