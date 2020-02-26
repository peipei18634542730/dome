package com.pei.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/2/24
 * \* Time: 15:28
 * \* Description: 杨辉三角 - 根据输入的值,返回相应的杨辉三角
 * \
 */

public class ReturnTriangle {
    public List<List<Integer>> generate(int numRows) {
        //存储要返回的杨辉三角
        List<List<Integer>> dg = new ArrayList<>();
        //若0行，则返回空
        if (numRows == 0) {
            return dg;
        }
        //递归出口，这是第一步！找到出口
        if (numRows == 1) {
            dg.add(new ArrayList<>());
            dg.get(0).add(1);
            return dg;
        }
        //如果传入的参数大于2,则先进行-1操作,直到上面return dg,才会进行 - 'dg = generate(numRows - 1);' 下面的代码
        //递归，注意返回值！！！这是第二步
        dg = generate(numRows - 1);
        //一级递归要做啥，我们可以看第二行到第三行需要做啥
        //首先是要申请一个list来存储第三行，然后通过第二行得到第三行
        //第三行的首尾为1是确定了的，然后就是中间的数如何得到
        //通过观察很容易拿到for循环里面的式子
        //最后别忘了返回值！！！
        List<Integer> row = new ArrayList<>();
        row.add(1);
        // 1
        // 1 1
        // 1 2 1
        for (int j = 1; j < numRows - 1; j++) {
            row.add(dg.get(numRows - 2).get(j - 1) + dg.get(numRows - 2).get(j));
        }
        row.add(1);
        dg.add(row);
        return dg;
    }

    public List<List<Integer>> generate2(int numRows) {
        List<List<Integer>> dp = new ArrayList<>();
        if(numRows == 0){
            return dp;
        }
        dp.add(new ArrayList<>());
        dp.get(0).add(1);
        //注意这里的 i 是指行数，但是dp是从0开始的
        //所以preRow是i-2  preRow 记录的是前一行的结果集
        for(int i = 2;i <= numRows;i++){  //第一个循环表示,三角的行数
            List<Integer> row = new ArrayList<>();
            List<Integer> preRow = dp.get(i-2); // i-2表hi下标,取前一行的结果集
            row.add(1);
            for(int j = 1;j < i-1;j++){ //第二个循环表示,这行上每个位置的值
                row.add(preRow.get(j) + preRow.get(j-1));
            }
            row.add(1);
            dp.add(row);
        }
        return dp;
    }

    public static void main(String[] args) {
        ReturnTriangle r = new ReturnTriangle();
        int a=4;
        System.out.println(r.generate2(a));
    }
}
