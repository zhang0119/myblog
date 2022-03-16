package com.example.myblog.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author: zy
 * date: 2022/3/17 3:21
 * qq:546359148
 *
 * MD5加密工具类
 */
public class MD5Util {

    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < byteDigest.length; offset++) {
                i = byteDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static void main(String[] args) {
        System.out.println(code("111111"));
    }
}

/*
    通过该工具类，可以获取密码，在main函数中输入自己密码对应的明码，然后运行，
    可以在控制台获取对应的密码，这个密码是要存储在数据库中的password字段
    -----------------------------------------------------------------------------
    eg：这里是"111111"字符串，运行main后，获得密码为："96e79218965eb72c92a549dd5a330112"，
    则将该字符串存储进数据库中
 */
