package 算法.简单;

/**
 * @ClassName PalindromeNumber
 * @Deacription LeetCode - 回文数
 * @Author peipei
 * @Date 2020/1/8 7:16
 * @Version 1.0
 **/

public class PalindromeNumber {
    /**
     * 解题思路：
     *         如果小于0的话，直接return false,如果大于0的话，以1221为例，%10取到个位数，%100取到10位数，%1000取到百位数，/1000取到千位数。
     *    比较千/百位的反转和十/个位是否相等
     */
    public static Boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }
        int a = 0;
        while (x > a) {
            //第一轮，a为1，取到了x的个位数，第二轮x进入循环的时候，x已经变成了122， x % 10取到2，1*10+2得到12
            a = a * 10 + x % 10;
            x /= 10;
        }
        /**
         *  x == a / 10,当x的长度为奇数时，以12321为例，第一轮a=0*10+1,x=1232.第二轮a=12,x=123,第三轮，
         *  a=123,x=12.return x == a / 10;
         */
        return x == a || x == a / 10;
    }

    public static void main(String[] args) {
        int x = 123431;
        System.out.println(isPalindrome(x));
    }
}
