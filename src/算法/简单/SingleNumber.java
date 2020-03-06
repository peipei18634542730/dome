package com.pei.demo.test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/3/5
 * \* Time: 14:14
 * \* Description:
 * \
 */

public class SingleNumber {
    /*
    * 解题思路:
    *       参照leetCode大神 - Jerry银银 的思路
    * */
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i : nums) {
            Integer num = map.get(i);
            num = num == null ? 1 : ++num; //将数组中只出现一次的做标记(1)
            map.put(i,num);
        }
        for (Integer i : map.keySet()) {
            Integer value = map.get(i);
            if (value == 1) {//拿到标记过的数
                return i;
            }
        }
        return -1;
    }



    public int singleNumber2(int[] nums) {
        //运用异或运算符 ^ / XOR
        /*
        * 性质
            1、交换律
            2、结合律（即(a^b)^c == a^(b^c)）
            3、对于任何数x，都有x^x=0，x^0=x
            4、自反性 A XOR B XOR B = A XOR 0 = A
        * */
        if (nums.length <= 1) return nums[0];
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num = num ^ nums[i];
        }
        return num;
    }

    public static void main(String[] args) {
        SingleNumber s = new SingleNumber();
        int[] a = {1,2,1,2,6};
        //循环顺序;
        // 1^2
        // 1^2 ^1 得到 2 num = 2
        //  2^2 得到 0 num = 0
        // 0^6 得到 6 num = 6

        System.out.println(s.singleNumber(a));
        System.out.println(s.singleNumber2(a));
        int c = 1,b =3;
        c = c^b; //1^3
        b = b^c;//3 ^ 1^3 =1
        c = c^b; //1^3 ^1 =3
        System.out.println(c);
    }
}
