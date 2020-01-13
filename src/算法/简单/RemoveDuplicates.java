package 算法.简单;

/**
 * @ClassName RemoveDuplicates
 * @Deacription 删除排序数组中重复项
 * @Author peipei
 * @Date 2020/1/13 22:53
 * @Version 1.0
 **/

public class RemoveDuplicates {
    public int removeDuplicates(int [] nums) {
        /**
         * 双指针算法
         * 定义两个指针，a、b,a为慢指针，b为快指针
         * 当a==b时，直接跳过
         */
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return 1;
        int i = 0;
        for (int j=1 ; j <len ; j++) {
            if (nums[j] != nums[i]) {
                //可以理解成，每次有重复的就把靠后的删掉，
                i++;//只记录了不同的数，循环里只记录了6次
                nums[i] = nums[j];//相当于把没有重复的组装成新的数组
            }
        }
        return i + 1;//目的是记录没有重新赋值的0
    }

    public static void main(String[] args) {
        int [] nums = {0,1,1,2,3,4,5,5,7,7,7,7,7,7,7,7,7};
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        System.out.println(removeDuplicates.removeDuplicates(nums));
    }
}
