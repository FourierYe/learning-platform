package com.hhit.learn.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: learn
 * @description: 账号的MD5加密工具
 * @author: 叶志鹏
 * @create: 2018-04-04 11:45
 **/
public class MD5Util {
    /**
     * Generate md 5 string.
     *
     * @param str the str
     * @return the string
     */
    public static String generateMd5(String str){
        StringBuffer stringBuffer1 = new StringBuffer();
        stringBuffer1.append(str);
        final String SALT="YEZHIPENG";
        stringBuffer1.append(SALT);
        String result = stringBuffer1.toString();
        char[] hexDigits ={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        StringBuffer stringBuffer=new StringBuffer();
        String retStr=null;
        try {
            MessageDigest md5=MessageDigest.getInstance("MD5");
            byte [] digest =md5.digest(result.getBytes());
            char[] retChar = new char[digest.length * 2];
            for(int i=0,k=0;i<digest.length;i++){
                //256内数字值
                byte _byte = digest[i];
                /*
                 * >>>无符号右移4位 也就是/2^2 除以4，拿到单个字节的低4位，在于 1111 1111 相与，就能得到了。
                 * eg: _byte = 164  二进制：1010 0100
                 * _byte >>>4 & 0xf = 0000 0100 & 1111 1111 = 0100 = 4 -->0x4
                 * _byte 在移动后为 _byte = 0000 1010 & 0xf = 1010 = 10 -->0xA 【高4位】
                 * 所以这个字节转为十六进制为： A4
                 */
                retChar[k++] = hexDigits[_byte >>> 4 & 0xf];
                retChar[k++] = hexDigits[_byte & 0xf];

            }
            retStr = new String(retChar);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return retStr;
    }
}
