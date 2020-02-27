package com.pei.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liupeidong
 * \* Date: 2020/2/25
 * \* Time: 11:25
 * \* Description:杨辉三角2
 * \
 */

public class ReturnTriangleII {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex == 0) {
            result.add(1);
            return result;
        }
        if (rowIndex == 1) {
            result.add(1);
            result.add(1);
            return result;
        }

        result.add(1);
        result.add(1);

        for (int i = 1; i < rowIndex; i++) {
            result.add(1);
            for (int j = 0; j < i; j++) {
                result.add(result.get(0) + result.get(1));
                result.remove(0);
            }
            result.add(1);
            result.remove(0);
        }

        return result;
    }

    public static void main(String[] args) {
        ReturnTriangleII e = new ReturnTriangleII();
        System.out.println(e.getRow(2));
    }

}
