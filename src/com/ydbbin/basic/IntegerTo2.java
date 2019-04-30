package com.ydbbin.basic;

/**
 * 十进制数字转化为2进制
 * 方法二思路：
 *  1：获取除2余数进行拼接
 *  2：获取除2结果循环步骤1
 *  3：最后反转
 * Created by Ydbbin on 2018/4/20
 */
public class IntegerTo2 {
    public static void main(String[] args) {

        int i = 5;
        //方法一：java函数
        System.out.println("方法一：java函数:"+Integer.toBinaryString(i));
        //方法二
        char[] cs =  {'0','1'};
        StringBuffer sb = new StringBuffer();
        int j ;
        while(i > 0){
            j = i % 2;
            sb.append(cs[j]);
            i = i >> 1;
        }
        System.out.println("方法二:"+sb.reverse().toString());




    }

}
