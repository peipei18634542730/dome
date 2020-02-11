package 算法.简单;

/**
 * @ClassName BigSum
 * @Deacription 求最大子序之和
 * @Author peipei
 * @Date 2020/2/11 20:21
 * @Version 1.0
 **/

public class BigSum {
    /**
     * 解题思路：
     *          动态规划
     *          1.如果sum>0,说明当前的结果对增益有效果，将其与遍历的数字相加
     *          2.如果sum<=0,说明当前结果对增益无效，sum直接更新为当前遍历的数
     *          3.每次比较sum和ans的大小，将最大值置为ans
     *          4.遍历完毕，返回ans
     */
    public int maxSubArray(int []nums) {
        int ans = nums[0];//表示从第一个数开始加
        int sum = 0;//记录相加值
        for (int num : nums) {
            if (sum>0) {
                sum += num;
            } else {
                sum = num;//第一次，sum变成数组第一个数
            }
            ans = Math.max(sum,ans);
        }
        return ans;
    }
    //如果数组都是负值，返回的是最大的负数。如果有正有负，返回的是最大的子序之和。

    public static void main(String[] args) {
        BigSum bigSum = new BigSum();
        int []a = {-1,2,-7,-2,1,3};
        System.out.println(bigSum.maxSubArray(a));
    }
}
