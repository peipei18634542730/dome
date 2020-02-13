package 算法.简单;

/**
 * @ClassName Add
 * @Deacription 加1
 * @Author peipei
 * @Date 2020/2/13 19:45
 * @Version 1.0
 **/

public class Add {
    /**
     * 解题思路
     *      1.末位加1不会进位，最简单，直接return结果就好了
     *      2.末位加1进位，但不会影响数组长度。比如399 --> 400
     *      3.末位加1进位，影响长度，如99 --> 100
     */
    public int[] addOne(int[] digits) {
        int len = digits.length;
        for (int i = len - 1;i >= 0;i--) {
            digits[i]++;//数组的最后一个值加1，如果结果为0，接着len-1的位置的数加1，依次循环
            digits[i] %= 10;
            if (digits[i] != 0) return digits;
        }
        digits = new int[len+1];//如果是99这样的值，结果是100，则第一位加1，其他位置为0
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        Add add = new Add();
//        int [] digits = {2,3};
        int [] digits = {8,9,9};
        for (int i : add.addOne(digits)) {
            System.out.println(i);
        }
    }
}
