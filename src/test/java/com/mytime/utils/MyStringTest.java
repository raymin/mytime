package com.mytime.utils;


import org.junit.Assert;
import org.junit.Test;

public class MyStringTest {

    @Test
    public void isMatch() {

        String regex = "^(?!.*(不合谐)).*$";//用到了前瞻，不含指定字符串
        Assert.assertEquals(false, MyString.isMatch("结尾不合谐", regex));
        Assert.assertEquals(false, MyString.isMatch("中间不合谐呢", regex));
        Assert.assertEquals(false, MyString.isMatch("不合谐开始", regex));
        Assert.assertEquals(true, MyString.isMatch("很合谐哦", regex));

        String regex2 = "^.*(?<!(不合谐))$";//用到了后顾，不能以指定字符串结尾
        Assert.assertEquals(false, MyString.isMatch("结尾不合谐", regex2));
        Assert.assertEquals(true, MyString.isMatch("中间不合谐呢", regex2));
        Assert.assertEquals(true, MyString.isMatch("不合谐开始", regex2));
        Assert.assertEquals(true, MyString.isMatch("很合谐哦", regex2));
    }
}
