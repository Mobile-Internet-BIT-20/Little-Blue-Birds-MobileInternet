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
var defaultHost = "http://127.0.0.1:8080"

window.onload = function () {

    language = new _Language().getLanguage();

    let languageUrl = "/json/language/register.json";

    loadPageLanguage(languageUrl);
}

function loadPageLanguage(languageUrl) {

    document.cookie = "usrLanguage=" + language + ";"

    $.getJSON(languageUrl, function(data) {

        let languageObj = data[language][0];

        let pageTitle = languageObj.title;

        $("title:eq(0)").text(pageTitle);
        $("#register_title").text(languageObj.register_title)
    })
}