package com.ydbbin.basic;

import java.util.HashSet;
import java.util.Set;

/**
 * byte转为int
 * Created by Ydbbin on 2018/4/20
 */
public class ByteToInt {
    public static void main(String[] args) {
        Set jyms = new HashSet();
        System.out.println(jyms.contains(""));
//        System.out.println(((1 & 0xff )<< 0));
//        System.out.println((int)(((1 & 0xff )<< 24)|((1 & 0xff) << 16)|((1 & 0xff) << 8)|((1 & 0xff) << 0)));
    }

    public static int ByteArrayToInt(byte[] bArr) {
        if(bArr.length!=4){
            return -1;
        }
        return (int) ((((bArr[3] & 0xff) << 24)
                | ((bArr[2] & 0xff) << 16)
                | ((bArr[1] & 0xff) << 8)
                | ((bArr[0] & 0xff) << 0)));
    }
}
