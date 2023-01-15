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

        $("#rightSideButton").click(function() {

            window.location.href = defaultHost + "/settings/normal"
        });

        $("#selfPost").text(languageObj.selfPost);
        $("#likedPost").text(languageObj.likedPost);
        $("#following").text(languageObj.following);
        $("#follower").text(languageObj.follower);
    });
}

function loadUserLike() {

    let requestUrl = defaultHost + "/api/user/" + getCookie("userId");

    $.getJSON(requestUrl, function(data) {

        let likePost = data.likePost;

        for (let i = likePost.length - 1; i >= 0; i--) {

            $.getJSON(defaultHost + "/api/post/" + likePost[i], function(postData) {

                $.get(defaultHost + "/api/user/getUserName", {

                    userId : postData.holderId
                }).done(function(data) {

                    let oriHTML = $("#otherContentArea").html();

                    let newHTML = "<p class = \"postHolder\">" + data + "</p>\n" +
                        "            <table class = \"userPost\">\n" +
                        "                <tr>\n" +
                        "                    <td class = \"postTitle\">" + postData.postTitle + "</td>\n" +
                        "                </tr>\n" +
                        "                <tr>\n" +
                        "                    <td class = \"postContent\">" + postData.postContent + "</td>\n" +
                        "                </tr>\n" +
                        "                <tr>\n" +
                        "                    <td class = \"postOP\">\n" +
                        "                        <table class = \"postOPTable\">\n" +
                        "                            <tr>\n" +
                        "                                <td class = \"likeOP\">" + postData.likeNum + "</td>\n" +
                        "                                <td class = \"commentOP\">Comment</td>\n" +
                        "                            </tr>\n" +
                        "                        </table>\n" +
                        "                    </td>\n" +
                        "                </tr>\n" +
                        "            </table>";

                    $("#otherContentArea").html(oriHTML + newHTML);
                });
            });
        }
    });
}

function loadUserFollower() {

    let requestUrl = defaultHost + "/api/user/" + getCookie("userId");

    $.getJSON(requestUrl, function(data) {

        let userFollowing = data.userFollower;

        for (let i = 0; i < userFollowing.length; i++) {

            $.get(defaultHost + "/api/user/getUserName", {

                userId : userFollowing[i].userId
            }).done(function(userName) {

                let oriHTML = $("#otherContentArea").html();
                let newHTML = "<table class = \"userFollow\" border = 0>\n" +
                    "                <tr>\n" +
                    "                    <td class = \"userProfilePicture\"><img src = \"/materials/logo.png\" /></td>\n" +
                    "                    <td class = \"userName\" colspan = \"5\"><a href = \"" + defaultHost + "/" + userFollowing[i].userId + "\">" + userName + "</a></td>\n" +
                    "                </tr>\n" +
                    "            </table>";

                $("#otherContentArea").html(oriHTML + newHTML);
            });
        }
    });
}

function loadUserFollowing() {

    let requestUrl = defaultHost + "/api/user/" + getCookie("userId");

    $.getJSON(requestUrl, function(data) {

        let userFollowing = data.userFollowing;

        for (let i = 0; i < userFollowing.length; i++) {

            $.get(defaultHost + "/api/user/getUserName", {

                userId : userFollowing[i].userId
            }).done(function(userName) {

                let oriHTML = $("#otherContentArea").html();
                let newHTML = "<table class = \"userFollow\" border = 0>\n" +
                    "                <tr>\n" +
                    "                    <td class = \"userProfilePicture\"><img src = \"/materials/logo.png\" /></td>\n" +
                    "                    <td class = \"userName\" colspan = \"5\"><a href = \"" + defaultHost + "/" + userFollowing[i].userId + "\">" + userName + "</td>\n" +
                    "                </tr>\n" +
                    "            </table>";

                $("#otherContentArea").html(oriHTML + newHTML);
            });
        }
    });
}

function fixedDirClick() {

    $("#selfPost").click(function() {

        if (currentPage !== "selfPost") {
            currentPage = "selfPost";
            $("#otherContentArea").html("");
            loadUserPost();
        }
    });

    $("#likedPost").click(function() {

        if (currentPage !== "likedPost") {
            currentPage = "likedPost";
            $("#otherContentArea").html("");
            loadUserLike();
        }
    });

    $("#following").click(function() {

        if (currentPage !== "following") {
            currentPage = "following";
            $("#otherContentArea").html("");
            loadUserFollowing();
        }
    });

    $("#follower").click(function() {

        if (currentPage !== "follower") {
            currentPage = "follower";
            $("#otherContentArea").html("");
            loadUserFollower();
        }
    });
}