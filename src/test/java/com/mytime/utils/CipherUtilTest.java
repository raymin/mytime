package com.mytime.utils;

import org.junit.Assert;
import org.junit.Test;

public class CipherUtilTest {

    @Test
    public void test(){
        String pwd = CipherUtil.generatePassword("123456");
        Assert.assertEquals("E10ADC3949BA59ABBE56E057F20F883E",pwd);
    }
}
