package 算法.简单;

/**
 * @ClassName FindPosition
 * @Deacription 搜索插入位置
 * @Author peipei
 * @Date 2020/2/10 20:48
 * @Version 1.0
 **/

public class FindPosition {
    /**
     * 二分查找法
     */
    public int searchInsert(int[] nums,int target) {
        int left = 0; int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        FindPosition findPosition = new FindPosition();
        int [] num = {0,1,2,3,4};
        int target = 0;
        System.out.println(findPosition.searchInsert(num, target));
    }
}
