package com.pei.demo.test;

import java.awt.font.NumericShaper;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/3/16
 * \* Time: 9:37
 * \* Description: 旋转数组
 * \
 */

public class RotateArray {
    /**
     *解题思路:
     *      借鉴了leetCode官网解题
     */
    /*第一种 暴力解题*/
    public int[] rotate(int[] nums,int k) {
        int temp,previous;
        for (int i = 0; i <k ; i++) {
            previous = nums[nums.length - 1]; //拿到最后一个参数
            for (int j = 0; j < nums.length ; j++) {
                temp = nums[j];//将最后一个参数插到数组的第一的位置
                nums[j] = previous;
                previous = temp;
            }
        }
        return nums;
    }

    public int[] rotate2(int[] nums,int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
//        for (int i = 0; i < nums.length; i++) {
//            nums[i] = a[i];
//        }
        return a;
    }
    /*
      使用反转
      原始数组                   : 1 2 3 4 5 6 7
      反转所有数字后             : 7 6 5 4 3 2 1
      反转前 k 个数字后          : 5 6 7 4 3 2 1
      反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果
      */
    public int[] rotate3(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        return nums;
    }
    public void reverse(int[] nums, int start, int end) { //反转方法
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /*使用环状替换*/
    public int[] rotate4(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
        return nums;
    }
    public static void main(String[] args) {
        RotateArray  r  =  new RotateArray();
        int [] a = {1,2,3,4,5};
        int b = 2;
        int[] ints = r.rotate4(a, b);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
