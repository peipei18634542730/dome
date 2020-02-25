package com.pei.demo.test;

import java.util.Arrays;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/2/20
 * \* Time: 14:23
 * \* Description:两个有序数组排序
 * \
 */

public class MergeArray {
    public int[] mergeArray(int []a,int []b,int m,int n) {
        while (m >0 && n >0) {
            if (a [--m] <= b[--n]) a[m] = b[n];
        }
        //然后排序,输出结果 a = {1,2,3,1,4}
        Arrays.sort(a);
        for (int i : a) {
            System.out.print(i+",");
        }
        return a;
    }


    public static void main(String[] args) {
        int []a = {1,2,3,0};
        int []b = {5};
        MergeArray m = new MergeArray();
        m.mergeArray(a,b,a.length,b.length);


    }
}
