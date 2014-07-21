package com.mytime.utils;


public class Constants {

    /*正则表达式：用户名以字母开头的，可以包含数字和符号("_","-",".") */
    public static final String REGEX_USER_NAME_FORMAT = "^[a-zA-Z]([\\w-.]){2,20}$";
    /*正则表达式：邮箱格式xxx@yyy.com*/
    public static final String REGEX_USER_EMAIL_FORMAT = "^\\w+([-.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /*正则表达式：手机格式为11位数字且以1开头*/
    public static final String REGEX_USER_MOBILE_FORMAT = "^[1]\\d{10}$";
    /*正则表达式：密码包含数字\字母和符号的6位以上字符串，且不能为纯数字或纯字母*/
    public static final String REGEX_USER_PWD_FORMAT = "(([\\w!@#$%^&*-_.]){6,20}$)";

}
