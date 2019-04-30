package com.ydbbin.basic;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 小数的科学计数法
 * Created by Ydbbin on 2018/4/20
 */
public class kxjsfOfXS {
    public static void main(String[] args) {
        //科学计数法
        Double b = 34343434.34343434d;
        BigDecimal bd = new BigDecimal(b);
        bd.setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(bd.doubleValue());
        //保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        double b1 = 44444444444443.141D;
        String s = df.format(b1);
        System.out.println(s);

    }
}
