package com.pei.demo.test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/3/9
 * \* Time: 17:16
 * \* Description:Excel表序列号
 * \
 */

public class TitleToNumber {
    /**
     *解题思路:
     *        来自大佬 灵魂画手.
     */
    public int toNum(String s) {
        int result = 0;
        for (int i = 0; i <s.length() ; i++) {
            int num = s.charAt(i) - 'A' + 1; //num相当于记录每次循环进来的字母
            result = result * 26 + num;//result * 26 + num,将每次的值计算出来
        }
        return result;
    }
}
