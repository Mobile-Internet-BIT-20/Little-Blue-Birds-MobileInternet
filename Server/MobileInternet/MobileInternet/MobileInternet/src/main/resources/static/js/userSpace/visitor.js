/*
 *  userSpace/userSpace.js
 *  非当前用户个人界面 js 代码
 *  Author       : SeeChen Lee
 *  Version      : 1.0.0
 *  Last Modified: 13-Jan-2023 03:29
 */

import {consoleMessage} from "../../publicFunction/consoleMessage.js";
import {_Language} from "../../publicFunction/language.js";

var pageUserId;
var defaultHost = "http://127.0.0.1:8080";
var language;
var languageUrl = "/json/language/userSpace/visitor.json";

window.onload = function() {

    language = new _Language().getLanguage();

    consoleMessage();

    loadPublicPage();
    loadSelfPage();

    loadUserPost();
}

function loadSelfPage() {

    language = getCookie("userLanguage");

    $.getJSON(languageUrl, function (data) {

        let languageObj = data[language][0];

        $("#switch_lang").html(languageObj.switch_lang + "<ul class = \"menu2\">\n" +
            "                        <li class = \"menu_2\" id = \"switch_zh\">中文</li>\n" +
            "                        <li class = \"menu_2\" id = \"switch_en\">English</li>\n" +
            "                    </ul>");

        $("#switch_zh").click(function () {
            setCookie("userLanguage", "zh", 365);
            loadPublicPage();
            loadSelfPage();
        });

        $("#switch_en").click(function () {
            setCookie("userLanguage", "en", 365);
            loadPublicPage();
            loadSelfPage();
        });

        let CurrentUrl = window.location.href;
        let userId     = CurrentUrl.split('/')[3];

        let requestUrl = defaultHost + "/api/user/" + userId;

        $.get(requestUrl).done(function(data) {

            let followTxt = "followUser";
            for (let i = 0; i < data.userFollower.length; i++) {
                if (getCookie("userId") === data.userFollower[i].userId) {

                    followTxt = "unfollowUser";
                    break;
                }
            }

            $("#rightSideButton").text(languageObj[followTxt]);
        });
    });
}