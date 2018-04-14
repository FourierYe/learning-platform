package com.hhit.learn.util;

import java.util.Random;

/**
 * The type Token util.
 *
 * @program: learn
 * @description: 令牌工具类
 * @author: GeekYe
 * @create: 2018 -04-14 21:28
 */
public class TokenUtil {

    /**
     * Get token string.
     *
     * @return the string
     */
    public String getToken(){

        String Time = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";

        return MD5Util.generateMd5(Time);
    }
}
