/*
 *  login.js
 *  login 界面的主要 js 文件
 *  Author       : SeeChen Lee
 *  Version      : 1.0.0
 *  Last Modified: 13-Jan-2023 03:29
 */
import {consoleMessage} from "../publicFunction/consoleMessage.js";
import {_Language}      from "../publicFunction/language.js";

var language;
var defaultHost = "http://127.0.0.1:8080";
var languageUrl;

window.onload = function () {

    language = new _Language().getLanguage();

    languageUrl = "/json/language/login.json";

    $("#userEmail_input").focus();

    consoleMessage();
    loadPageLanguage(languageUrl);

    keyboardEvent()

    $("#login_button").click(function() { loginClick()});
}

function keyboardEvent() {

    $("html:eq(0)").bind("keydown", function (e) {

        let event   = e || window.event;
        let keyCode = event.keyCode || event.which || event.charCode;

        if (keyCode === 13) {

            loginClick();
        }
    });
}

function loginClick() {

    let userEmail = $("#userEmail_input").val();
    let userPass  = $("#userPass_input").val();

    let verifyEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;

    let noneWrong = true;

    $.getJSON(languageUrl, function(data) {

        let languageObj = data[language][0];

        if (!verifyEmail.test(userEmail)) {

            $("#userWarning").html(languageObj.emailFormatWrong);
            noneWrong = false;
        }

        if (userPass === "") {

            $("#passWarning").html(languageObj.passwordNull);
            noneWrong = false;
        }

        if (noneWrong) {

            $.post(defaultHost + "/api/login", {

                userEmail : userEmail,
                userPass  : $.md5(userPass)
            }).done(function (data) {

                if (data === "UserNotExists") {

                    $("#userWarning").html(languageObj.userNotExists);
                } else if (data === "WrongPassword") {

                    $("#passWarning").text(languageObj.wrongPassword);
                }else {

                    setCookie("userId"   , data           , 365);
                    setCookie("userEmail", userEmail      , 365);
                    setCookie("userPass" , $.md5(userPass), 365);

                    window.location.href = defaultHost + "/home";
                }
            });
        }
    });
}

function loadPageLanguage(languageUrl) {

    setCookie("userLanguage", language, 365);

    $.getJSON(languageUrl, function(data) {

        let languageObj = data[language][0];

        let pageTitle = languageObj.title;

        $("title:eq(0)").text(pageTitle);
        $("#login_title").text(languageObj.login_title)

        $("#userEmail_title").text(languageObj.userEmail_title);
        $("#userPass_title").text(languageObj.userPass_title);
        $("#login_button").text(languageObj.login_button);
        $("#goto_Register").html(languageObj.notYetAccount);

        $("#userEmail_input").attr("placeholder", languageObj.userEmail_placeholder);
        $("#userPass_input").attr("placeholder", languageObj.userPass_placeholder);
    })
}