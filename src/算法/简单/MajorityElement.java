package com.pei.demo.test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/3/9
 * \* Time: 9:36
 * \* Description: 多位元素
 * \
 */

public class MajorityElement {
    /*
     * 解题思路:
     *         借鉴官网的投票算法
     * 题目中明确说明,传入的数组是必然含有多位元素的,必然最后遍历后的结果,count 是大于0 的.num记录的值就是最后的结果
     * */
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer num = null;
        for (int i : nums) {
            if (count == 0) {
                num = i;
            }
            count += (num == i) ? 1 : -1;
        }
        return num;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 6, 1, 1, 1, 1, 6};
        MajorityElement m = new MajorityElement();
        System.out.println(m.majorityElement(a));
    }
}
