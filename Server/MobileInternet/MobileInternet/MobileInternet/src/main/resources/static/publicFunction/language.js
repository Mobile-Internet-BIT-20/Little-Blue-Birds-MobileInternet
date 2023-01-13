/*
 *  language.js
 *  用于获取用户的计算机当前的语言 调整用户界面语言
 *  Author       : SeeChen Lee
 *  Version      : 1.0.0
 *  Last Modified: 12-Jan-2023 16:49
 */

export class _Language {

    #language = navigator.language;

    constructor() {}

    #filterLanguage() {

        let systemLanguage = true;

        let name      = "usrLanguage=";
        let cookieArr = document.cookie.split(';');

        for (let i = 0; i < cookieArr.length; i++) {
            var cookie = cookieArr[i].trim();

            if (cookie.indexOf(name) === 0) {

                this.#language = cookie.substring(name.length, cookie.length);
                systemLanguage = false;
            }
        }

        if (systemLanguage) {

            switch (this.#language.substring(0, 2)) {

                case 'zh':
                    this.#language = 'zh';
                    break

                default:
                    this.#language = 'en';
                    break
            }
        }

        return this.#language;
    }

    getLanguage() {

        return this.#filterLanguage();
    }
}