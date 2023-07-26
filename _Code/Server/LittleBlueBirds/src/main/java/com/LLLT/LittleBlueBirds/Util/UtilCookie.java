package com.LLLT.LittleBlueBirds.Util;

import javax.servlet.http.Cookie;
import java.util.HashMap;

public class UtilCookie {

    public static class setCookie {

        public static class cookieAccount {

            public static Cookie cookieSecurity (
                    String Key  ,
                    String Value,
                    int    Expired
            ) {

                return new Cookie(Key, Value) {{

                    setHttpOnly(true);
                    setPath("/");
                    setMaxAge(Expired);
                }};
            }
        }
    }

    public static class getCookie {

        public static class cookieAccount {

            public static HashMap<String, String> cookieSecurity (
                    Cookie[] cookies
            ) {

                HashMap<String, String> hashMap = new HashMap<String, String>() {{

                    put("UID"            , "");
                    put("AccountEmail"   , "");
                    put("AccountPassword", "");
                }};

                for (Cookie c : cookies) {

                    hashMap.replace(c.getName(), c.getValue());
                }

                return hashMap;
            }
        }
    }
}
