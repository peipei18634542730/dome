package 算法.简单;

import jdk.nashorn.internal.ir.annotations.Ignore;

/**
 * @ClassName RomanTo
 * @Deacription
 * @Author peipei
 * @Date 2020/1/8 23:05
 * @Version 1.0
 **/

public class RomanTo {
    /**
     * 解题思路：
     *          将所有的字符对应的数字，将传过来的字符串进行拆分，与相邻的字符进行比较，如果大就加，如果小就减。
     */
    public static int getValue(char x) {
        switch (x) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                throw new IllegalArgumentException("输入参数格式不正确，请确认后重试");
        }
    }
        public int romanToInt(String s) {
               int result = 0, i = 0, n = s.length();
               while (i<n) {
                   int current = getValue(s.charAt(i)); //一次从第一个字符开始取值
                   if (i == n-1 || current >= getValue(s.charAt(i+1))) { //当取到最后一个，或者是字符串→边的比←边的大，直接加
                       result += current;
                   } else { //否则直接减
                       result -= current;
                   }
                   i++; //依次拿第二个，第三个
               }
               return result;
        }

    public static void main(String[] args) {
        RomanTo romanTo = new RomanTo();
        String s = "LVIII";
        System.out.println(romanTo.romanToInt(s));
    }
}
