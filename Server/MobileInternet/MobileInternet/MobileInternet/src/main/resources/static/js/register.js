/*
 *  register.js
 *  register 界面的主要 js 文件
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

    languageUrl = "/json/language/register.json";

    $("#userEmail_input").focus();

    consoleMessage();
    loadPageLanguage(languageUrl);

    keyboardEvent();

    $("#register_button").click(function() { registerClick()});
}

function keyboardEvent() {

    $("html:eq(0)").bind("keydown", function (e) {

        let event   = e || window.event;
        let keyCode = event.keyCode || event.which || event.charCode;

        if (keyCode === 13) {

            registerClick();
        }
    });
}

function registerClick() {

    let userEmail   = $("#userEmail_input").val();
    let userPass    = $("#userPass_input").val();
    let confirmPass = $("#confirmPass_input").val();

    let verifyEmail = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;
    let verifyPass  = /^(?=.*[a-zA-Z])(?=.*[1-9])/;
    let verifyZH    = /[\u4e00-\u9fa5]/ig;

    let noneWrong = true;

    $.getJSON(languageUrl, function(data) {

        let languageObj = data[language][0];

        if (userPass !== confirmPass) {

            $("#passWarning_second").html(languageObj.passMustSame);
            noneWrong = false;
        }

        if (!verifyEmail.test(userEmail)) {

            $("#userWarning").html(languageObj.emailFormatWrong);
            noneWrong = false;
        }

        if (!verifyPass.test(userPass)) {

            $("#passWarning_first").html(languageObj.passMustInclude);
            noneWrong = false;
        }

        if (verifyZH.test(userPass)) {

            $("#passWarning_first").html(languageObj.passWithoutZH);
            noneWrong = false;
        }

        if (userPass.length < 8) {

            $("#passWarning_first").html(languageObj.passLeast8Digit)
            noneWrong = false;
        }

        console.log(noneWrong);

        if (noneWrong) {

            $.post(defaultHost + "/api/register", {

                userEmail : userEmail,
                userPass  : $.md5(userPass)
            }).done(function (data) {

                if (data === "UserExists") {

                    $("#userWarning").html(languageObj.userExists);
                } else {

                    alert(languageObj.registerFinished);
                    window.location.href = defaultHost + "/login";
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
        $("#register_title").text(languageObj.register_title)

        $("#userEmail_title").text(languageObj.userEmail_title);
        $("#userPass_title").text(languageObj.userPass_title);
        $("#confirmPass_title").text(languageObj.confirmPass_title);
        $("#register_button").text(languageObj.register_button);
        $("#goto_Login").html(languageObj.alreadyHaveAccount);

        $("#userEmail_input").attr("placeholder", languageObj.userEmail_placeholder);
        $("#userPass_input").attr("placeholder", languageObj.userPass_placeholder);
        $("#confirmPass_input").attr("placeholder", languageObj.confirmPass_placeholder);
    })
}