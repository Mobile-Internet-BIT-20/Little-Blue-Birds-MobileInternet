/*
 *  userSpace/userSpace.js
 *  个人界面的共享 js 代码
 *  Author       : SeeChen Lee
 *  Version      : 1.0.0
 *  Last Modified: 13-Jan-2023 03:29
 */

var defaultHost = "http://127.0.0.1:8080";
var languageUrl = "/json/language/userSpace/userSpace.json";

function loadPublicPage() {

    let CurrentUrl = window.location.href;
    let userId     = CurrentUrl.split('/')[3];

    let requestUrl = defaultHost + "/api/user/" + userId;

    $.get(requestUrl).done(function(data) {

        $.getJSON(languageUrl, function (dataLang) {

            let languageObj = dataLang[getCookie("userLanguage")][0];
            $("title:eq(0)").text(data.userName + " | " + languageObj.title);

            $("#goto_Home").text(languageObj.goto_Home);
            $("#goto_Publish").text(languageObj.goto_Publish);
            $("#setting_list").html(languageObj.setting_list + "<ul class = \"menu2\">\n" +
                "                    <li class = \"menu_2\" id = \"set_normal\"  >set_normal</li>\n" +
                "                    <li class = \"menu_2\" id = \"set_security\">set_security</li>\n" +
                "                    <li class = \"menu_2\" id = \"user_logout\">user_logout</li>\n" +
                "                </ul>");
            $("#set_normal"  ).text(languageObj.set_normal);
            $("#set_security").text(languageObj.set_security);
            $("#user_logout" ).text(languageObj.user_logout);

            $("#set_normal").click(function() {

                window.location.href = defaultHost + "/settings/normal";
            });
            $("#set_security").click(function() {

                window.location.href = defaultHost + "/settings/security";
            });
            $("#user_logout").click(function() {

                userLogout();
            });

            $("#showUserName").text(data.userName);

            let bgImg = "url('/materials/" + data.userSexList[data.userSexIndex] + ".png')";

            $("#showUserSex").css("background-image", bgImg)

            $("#dayOfBirth").text(data.dayOfBirth);
            $("#userIntro").text(data.userIntro);
        });
    });

    $("#goto_Home").click(function () {

        window.location.href = defaultHost + "/home";
    });
}

function loadUserPost() {

    let CurrentUrl = window.location.href;
    let userId     = CurrentUrl.split('/')[3];

    let requestUrl = defaultHost + "/api/user/" + userId;

    $.get(requestUrl).done(function(userData) {

        for (let i = userData.userPost.length - 1; i >= 0; i--) {

            $.getJSON(defaultHost + "/api/post/" + userData.userPost[i], function (postData) {

                let oriHTML = $("#otherContentArea").html();

                let newHTML = "<p class = \"postHolder\">" + userData.userName + "</p>\n" +
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
        }
    });
}