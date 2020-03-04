package com.pei.demo.test;

import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/3/3
 * \* Time: 11:22
 * \* Description:扑克牌中的顺子 限定数组的长度为5
 * \
 */

public class IsStraight {
    /**
     * 解题思路： 解题思路来自LeetCode大神-路漫漫我不畏
     * 一。不排序
     * 不妨先思考一个这样的问题：
     * 有一串连续的数字（无重复），这串数字中最大值为 mm， 最小值为 nn ，问你这串数字中一共有多少个数字？
     * <p>
     * 答案：
     * m - n + 1
     * <p>
     * 同样，如果我们能够知道 5 张扑克牌中的最大值 maxValuemaxValue 和最小值 minValueminValue ，
     * 那我们就知道，要使它为顺子需要 maxValue - minValue + 1 张牌。
     * <p>
     * 在查找 maxValuemaxValue 和 minValueminValue 过程中，跳过大小王 00 。
     * 如果 maxValue - minValue + 1 > 5maxValue−minValue+1>5，说明题目给的 5 张牌不足以构成顺子，返回 falsefalse .
     * 即使里面有大小王，也不够用来填补使它构成顺子。
     * 如果 maxValue - minValue + 1 <= 5maxValue−minValue+1<=5，说明 5 张牌足以构成顺子，返回 truetrue。
     * 里面的大小王填补在合适位置即可。
     * 同时，我们再定义一个标志数组判断是否有重复数字，发现重复数字直接返回 falsefalse 即可。
     */
    public boolean isStraight(int[] nums) {
        int max = 0, min = 0;
        for (int i = 0; i < nums.length -1; i++) {
            if (i == nums.length -1) break; //已经比完了
            if (nums[i] == 0) continue;
            if (min == 0) min = nums[i];
            if (nums[i]>nums[i+1]) {
                if (max < nums[i]) max = nums[i];
                if (min > nums[i+1]) min = nums[i+1];
            } else {
                if (max < nums[i+1]) max = nums[i+1];
                if (min > nums[i]) min = nums[i];
            }
        }
        return max - min + 1 <= 5;
    }

    /**
     * 二。排序法
     * 排序方式
     * 排序之后扑克牌就有序了，我们就可以直接判断相邻两张牌之间需要多少个大王或小王来填补。
     * <p>
     * 如果需要填补大小王的数量，大于已有大小王的数量，则返回 falsefalse .
     * 相反，如果需要填补大小王的数量，小于或等于已有大小王的数量，则返回 truetrue .
     */
    public boolean isStraight2(int[] nums) {
        Arrays.sort(nums);
        int zero = 0;
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) {
                zero++;
                continue;
            }
            if (nums[i] == nums[i + 1]) {
                return false;
            }
            zero -= nums[i + 1] - nums[i] - 1;//如果相邻差值大于1，zero可能会变成负数
        }
        return zero >= 0;
    }

    public static void main(String[] args) {
        int []a={1,2,3,4,5};
        int []b={0,0,1,2,5};
        int []c={0,0,3,2,7};
        IsStraight is = new IsStraight();
        System.out.println(is.isStraight(c));
    }
}

