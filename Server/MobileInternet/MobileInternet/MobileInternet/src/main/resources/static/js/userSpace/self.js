/*
 *  userSpace/userSpace.js
 *  当前用户个人界面 js 代码
 *  Author       : SeeChen Lee
 *  Version      : 1.0.0
 *  Last Modified: 14-Jan-2023 14:21
 */

import {consoleMessage} from "../../publicFunction/consoleMessage.js";
import {_Language} from "../../publicFunction/language.js";

var pageUserId;
var defaultHost = "http://127.0.0.1:8080";
var language;
var languageUrl = "/json/language/userSpace/self.json";

var currentPage = "selfPost";

window.onload = function() {

    language = new _Language().getLanguage();

    consoleMessage();

    loadPublicPage();
    loadSelfPage();

    loadUserPost();
    fixedDirClick();
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

        $("#rightSideButton").text(languageObj.edit_Profile);

        $("#selfPost").text(languageObj.selfPost);
        $("#likedPost").text(languageObj.likedPost);
        $("#following").text(languageObj.following);
        $("#follower").text(languageObj.follower);
    });
}

function fixedDirClick() {

    $("#selfPost").click(function() {

        if (currentPage !== "selfPost") {
            currentPage = "selfPost"
            loadUserPost();
        }
    });

    $("#likedPost").click(function() {

        if (currentPage !== "likedPost") {
            currentPage = "likedPost"
            //loadUserPost();
        }
    });

    $("#following").click(function() {

        if (currentPage !== "following") {
            currentPage = "following"
            //loadUserPost();
        }
    });

    $("#follower").click(function() {

        if (currentPage !== "follower") {
            currentPage = "follower"
            //loadUserPost();
        }
    });
}