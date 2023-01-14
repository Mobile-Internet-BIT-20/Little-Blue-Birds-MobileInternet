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
var languageUrl;

window.onload = function() {

    language = new _Language().getLanguage();

    consoleMessage();

    getUrlUserInfo();
}