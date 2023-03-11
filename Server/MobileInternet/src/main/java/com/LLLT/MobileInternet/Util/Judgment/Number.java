package com.LLLT.MobileInternet.Util.Judgment;

import java.util.regex.Pattern;

public class Number {

    public Number() {}
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
