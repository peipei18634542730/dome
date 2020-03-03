package com.pei.demo.test;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/2/28
 * \* Time: 11:24
 * \* Description: leetCode - 打家劫舍
 * \
 */

public class Rob {
    /**
     * 解题思路:
     *          其实就是计算相隔最大的和
     *          1.如果数组长度为1,直接返回该数组的值
     *          2.如果数组长度为2,返回数组中的最大值
     *
     */
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return nums[0] > nums[1] ? nums[0] : nums[1];
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i = 2; i <= len; i++) {
            //dp[i] 记录数组中的最大值或者是最大的和
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[len];
    }

    public static void main(String[] args) {
        int [] a = {1,2,3,20,1,6,6,4,5,16,27};
        Rob r = new Rob();
        System.out.println(r.rob(a));
    }
}
