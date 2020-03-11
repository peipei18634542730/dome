package com.pei.demo.test;

import javax.xml.transform.Source;
import java.sql.SQLOutput;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/3/10
 * \* Time: 9:53
 * \* Description:阶乘后的0
 * \
 */

public class TrailingZeroes {
    public int trailingZeroes(int num) {
        if (num <= 4) return 0;
        int count = 0;
        while (num > 4) {
            num /= 5;
            count += num;
        }
        return count;
    }

    public static void main(String[] args) {
        TrailingZeroes t = new TrailingZeroes();
        System.out.println(t.trailingZeroes(1808548329));
    }
}
