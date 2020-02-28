package com.pei.demo.test;

import sun.security.util.Length;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/2/27
 * \* Time: 16:35
 * \* Description: 买卖股票的最佳时机
 * \
 */

public class BestTime {
    //第一种方法参照官网解题,比较暴力,容易理解
    int minprice = Integer.MAX_VALUE;

    public int maxProfit(int prices[]) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit)
                    maxprofit = profit;
            }
        }
        return maxprofit;
    }

    //[7, 1, 5, 3, 6, 4]
    //leetCode官网的解题.动态规划法
    public int maxProfit2(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;//这个数作为记录最大差值
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) //找出最小的值
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit) //每次进入循环的时候,和之前的差值进行比较.从而拿到最大差值
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    /**
     * 解题思路:
     * 找出数组中最小的值,然后找到该值右边最大的值.两个值相减就是结果.
     * 特殊情况:
     * 1.数组长度为1 return 0
     * 2.如果最小的值在数组的末尾,return 0
     */
    public int maxProfit3(int prices[]) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int max = 0, min = 0; // min 记录数组中的最小值 max记录记录差值
        for (int i = 0, j = 1; i < prices.length; i++) {
            if (j >= prices.length - 1) {
                if(prices[prices.length - 1] == min) return 0; else return max;
                //如果到这一步的话,说明已经比完了,prices[prices.length - 1] == min 说明最小的值在数组的末尾
            }
            if (prices[i] < prices[j]) {
                min = prices[i];     // |
            } else {                 // |min始终记录的是数组中的最小值
                min = prices[j];     // |
                j++;
                if (prices[j] - min > max) max = prices[j] - min;//用数组中最小值右边的每一个值和最小值减,得到结果
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {7, 1, 2, 3, 6, 4,8};
        int[] b = {5,3, 2, 1};
        BestTime bt = new BestTime();
        System.out.println(bt.maxProfit2(a));
    }
}
