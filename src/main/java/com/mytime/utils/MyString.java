package com.mytime.utils;


import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

public class MyString extends StringUtils {

    public static boolean isMatch(String str, String regex) {
        return Pattern.compile(regex).matcher(str).matches();
    }

}
