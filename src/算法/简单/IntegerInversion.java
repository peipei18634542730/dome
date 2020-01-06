package 算法.简单;

/**
 * @ClassName  整数反转
 * @Deacription leetCode - 整数反转
 * @Author peipei
 * @Date 2020/1/6 22:52
 * @Version 1.0
 **/

public class IntegerInversion {

    /**
     * 解题思路：
     *         再不借助其他储存结构的情况下，可以用数学的方式进行反转。
     *         第一步：x%10.得到新输出值的第一位数
     *         第二步：x=x/10 ,运算完成后，相当于将原输入值x的最后一位给删除
     *         第三步：result = result * 10 + num
     *      注意考虑溢出问题：
     *      如果x>0的话，1.result > Integer.MAX_VALUE / 10,肯定是溢出的。2.result == Integer.MAX_VALUE / 10，如果num > Integer.MAX_VALUE
     *      %10,则为溢出情况。如果x<0，也是一样的结果
     */
    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int num = x % 10; // 将x的最后一位获取到，这个值就是result的第一位数
            x = x / 10;
            if (x > 0) {
                if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                    //result > Integer.MAX_VALUE / 10 假设 Integer.MAX_VALUE = 320  result > 32的话，result=result*10+num溢出
                    //result == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10 result=result*10+num溢出
                    return 0;
                }
            } else {
                if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && num < Integer.MIN_VALUE % 10)) {
                    //result < Integer.MIN_VALUE / 10 result=result*10+num溢出
                    //result == Integer.MIN_VALUE / 10 && num < Integer.MAX_VALUE % 10 result=result*10+num溢出
                    return 0;
                }
            }
            result = result * 10 + num;
        }
        return result;
    }

    public static void main(String[] args) {
        int i = 145421;
        System.out.println(reverse(i));
    }
}
