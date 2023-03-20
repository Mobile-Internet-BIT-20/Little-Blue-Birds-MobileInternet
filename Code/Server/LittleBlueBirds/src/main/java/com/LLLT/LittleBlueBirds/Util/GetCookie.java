package com.LLLT.LittleBlueBirds.Util;

import javax.servlet.http.Cookie;
import java.util.HashMap;

public class GetCookie {

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
