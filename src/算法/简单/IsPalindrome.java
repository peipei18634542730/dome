package com.pei.demo.test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/3/4
 * \* Time: 17:07
 * \* Description:
 * \
 */

public class IsPalindrome {
    public boolean isPalindrome(String s) {
        char[] chars=s.toCharArray();
        for(int i=0,j=chars.length-1;i<j;i++,j--){
            char letter;
            //判断是否为字符和数字
            boolean flag1 = (chars[i] >= '0' && chars[i] <= '9') || (chars[i] >= 'a' && chars[i] <= 'z')||(chars[i] >= 'A' && chars[i] <= 'Z');
            boolean flag2 = (chars[j] >= '0' && chars[j] <= '9') || (chars[j] >= 'a' && chars[j] <= 'z')||(chars[j] >= 'A' && chars[j] <= 'Z');
            if (!flag1&&flag2)j++; // 如果flag1特殊字符,则j的值不发生改变
            if (flag1&&!flag2)i--; // 如果flag2特殊字符.则i的值不发生改变
            if (flag1&&flag2){ //如果都没有特殊字符,就把s倒装成一个新的字符串 String.valueOf(cArrs)
                letter=chars[j];
                chars[j]=chars[i];
                chars[i]=letter;
            }
        }
        System.out.println(s);
        System.out.println(String.valueOf(chars));
        return s.equalsIgnoreCase(String.valueOf(chars));//equalsIgnoreCase与equals区别是前者不区分大小写，而后者区分
    }
    public static void main(String[] args) {
    IsPalindrome is = new IsPalindrome();
        System.out.println(is.isPalindrome("A man, a plan, a canal: Panama"));
    }
}


