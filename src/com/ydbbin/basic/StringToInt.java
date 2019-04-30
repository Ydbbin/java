package com.ydbbin.basic;

/**
 * 字符串转为数字
 * 方法二：思路
 * 1：判断是否空
 * 2：判断正负
 * 3：判断除符号外长度是否符合
 * 4：如果长度为10，判断最高位是否大于最大int的最高位2，由于进行拼接最高位时候，会产生比最大int大的数字如：3000000000
 * 5：从最小位进行遍历判断每个字符是否为数字
 * 6：获取的字符，转化为int之后必须减去48或者'0'，十位开始进行*10，计算
 * 7：最后*符号位 -1/1
 * Created by Ydbbin on 2018/4/20
 */
public class StringToInt {
    public static void main(String[] args) {

        String s = "-2933333333";
        //方法一：java函数
        //为int类型
        //System.out.println(Integer.parseInt(s));
        //为Integer类型
        //System.out.println(Integer.valueOf(s));

        //方法二：
        System.out.println("方法二："+ stringToInt(s));



    }

    protected static  int stringToInt(String ss) {
        //是否为空
        if(ss == null || ss.trim().length() == 0){
            throw new IllegalArgumentException("需要转化的字符串为空：\"" + ss+"\"");
        }
        String s = ss.trim();
        int length = s.length();
        //判断正负
            //判断长度是否符合：除符号位 10位（间接说明超过范围）
            //如果为10位，就判断前两位是否大于21,不让继续执行
        int fh = 1;
        if ('-' == (s.charAt(0))) {
            fh = -1;
            s = s.substring(1);
            length  = length -1;
        }
        if(length > 10){
            throw new IllegalArgumentException("需要转化的字符串不符合int范围：\"" + ss+"\"");
        }
        if( length == 10){
            if(s.charAt(0) > '2' ){
                throw new IllegalArgumentException("需要转化的字符串不符合int范围：\"" + ss+"\"");
            }
        }
        //遍历每位字符
            //判断是否为非数字(char对比大小,char要减去'0'才能获取真正的数字，字符‘0’为48)
        int c ;
        long num = 0;

        for(int i = length -1,j = 1; i >= 0 ; i-- ,j *= 10 ) {
            c = (int)s.charAt(i);
            if(c < '0' || c > '9'){
                throw new IllegalArgumentException("需要转化的字符串不符合int格式：\"" + ss+"\"");
            }
            if(i == length -1){
                num = num + (c-48);
            }else{
                num = num + ((c-48) * j) ;
            }
        }
        num = num * fh;
        if(num > Integer.MAX_VALUE){
            throw new IllegalArgumentException("需要转化的字符串不符合int范围：\"" + ss+"\"");
        }
        if(num < Integer.MIN_VALUE){
            throw new IllegalArgumentException("需要转化的字符串不符合int范围：\"" + ss+"\"");
        }
        return (int)num;
    }
}
