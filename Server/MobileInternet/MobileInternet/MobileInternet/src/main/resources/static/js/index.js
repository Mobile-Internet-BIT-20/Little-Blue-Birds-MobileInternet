/*
 *  index.js
 *  index 界面的主要 js 文件
 *  Author       : SeeChen Lee
 *  Version      : 1.0.0
 *  Last Modified: 13-Jan-2023 03:29
 */

import {consoleMessage} from "../publicFunction/consoleMessage.js";
import {_Language}      from "../publicFunction/language.js";

var language;
var defaultHost = "http://39.105.205.166:8080/"

window.onload = function() {

    language = new _Language().getLanguage();

    let languageUrl = "/json/language/index.json";

    consoleMessage();
    loadPageLanguage(languageUrl);

    $("#goto_Login").click(function() {
        window.location.href = defaultHost + "/login";
    });

    $("#goto_Register").click(function() {
        window.location.href = defaultHost + "/register";
    });

    $("#goto_Download").click(function() {
        window.location.href = defaultHost + "/download/android/test.apk";
    });

    $("#goto_home").click(function () {
        window.location.href = defaultHost + "/home"
    });
}

function loadPageLanguage(languageUrl) {

    setCookie("userLanguage", language, 365);

    $.getJSON(languageUrl, function(data) {

        let languageObj = data[language][0];

        let pageTitle = languageObj.title;

        $("title:eq(0)").text(pageTitle);
        $("#headerTitle").text(pageTitle);
        $("#goto_Login").text(languageObj.goto_login);
        $("#goto_Register").text(languageObj.goto_register);
        $("#goto_Download").text(languageObj.downloadApp);
        $("#switch_lang").html(languageObj.switchLang + "<ul class = \"menu2\">\n" +
            "                        <li class = \"menu_2\" id = \"switch_zh\">中文</li>\n" +
            "                        <li class = \"menu_2\" id = \"switch_en\">English</li>\n" +
            "                    </ul>");

        $("#switch_zh").click(function () {
            language = 'zh';
            loadPageLanguage(languageUrl);
        });
        $("#switch_en").click(function () {
            language = 'en';
            loadPageLanguage(languageUrl);
        });

        $("#web_name").text(pageTitle + " ,");
        $("#web_introduction").text(languageObj.web_introduction);

        $("#goto_home").text(languageObj.goto_home);

        $("#project_members").text(languageObj.project_members);
        $("#leye_lau").text(languageObj.leye_lau);
        $("#seechen_lee").text(languageObj.seechen_lee);
        $("#winyi_lee").text(languageObj.winyi_lee);
        $("#vihang_tan").text(languageObj.vihang_tan);
    });
}