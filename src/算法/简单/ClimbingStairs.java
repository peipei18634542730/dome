package 算法.简单;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ClimbingStairs
 * @Deacription 爬楼梯
 * @Author peipei
 * @Date 2020/2/18 19:53
 * @Version 1.0
 **/

public class ClimbingStairs {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
    Map<Integer,Integer> map = new HashMap<>();
    //map作为一个缓存
    public int climbStairs2(int n) {
        if (n == 1) {map.put(1,1); return 1;}
        if (n == 2) {map.put(2,2); return 2;}
        if (map.get(n) != null) return map.get(n);
        int result = climbStairs2(n-1) + climbStairs2(n-2);
        map.put(n,result);
        return map.get(n);
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairs2(6));
        System.out.println(climbingStairs.climbStairs(6));
    }
}
