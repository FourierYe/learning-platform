package com.hhit.learn.util;

import java.util.regex.Pattern;

/**
 * The type Regex util.
 *
 * @program: learn
 * @description: 正则匹配工具类
 * @author: GeekYe
 * @create: 2018 -04-15 22:48
 */
public class RegexUtil {

    /**
     * The constant REGEX_EMAIL.
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * Is email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
}
