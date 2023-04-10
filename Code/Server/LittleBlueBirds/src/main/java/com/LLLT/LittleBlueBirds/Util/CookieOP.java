package com.LLLT.LittleBlueBirds.Util;

import javax.servlet.http.Cookie;
import java.util.HashMap;

public class CookieOP {

    public static class setCookie {

        public static class CookieAccount {

            public static Cookie CookieSecurity (
                    String Key   ,
                    String Value ,
                    int    maxAge
            ) {

                Cookie cookie = new Cookie(Key, Value);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                cookie.setMaxAge(maxAge);

                return cookie;
            }
        }
    }


    public static class UserCookie {

        public static HashMap<String, String> SecurityCookie (Cookie[] cookies) {

            HashMap<String, String> hashMap = new HashMap<String, String>() {{

                put("UserId"      , "");
                put("UserEmail"   , "");
                put("UserPassword", "");
            }};

            for (Cookie c : cookies) {

                hashMap.replace(c.getName(), c.getValue());
            }

            return hashMap;
        }
    }
}
