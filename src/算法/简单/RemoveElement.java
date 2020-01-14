package 算法.简单;

/**
 * @ClassName RemoveElement
 * @Deacription LeetCode - 移除元素
 * @Author peipei
 * @Date 2020/1/14 23:00
 * @Version 1.0
 **/

public class RemoveElement {
    /**
     *双指针算法
     *
     */
    public int removeElement(int[] nums,int num) {
        int i=0,j=0;
        while (j<nums.length) {
            if (nums[j]!=num) {
                nums[i++] = nums[j++];//相当于将j数组里不重复的值复制到i数组里
            } else {
                j++;//当两个值相等的话，过滤掉这个值，接着判断下一个值
            }
        }
        return i;
    }

    public static void main(String[] args) {
        RemoveElement removeElement = new RemoveElement();
        int [] a = {1,2,2,3,4,5,4,2};
        int b = 4;
        System.out.println(removeElement.removeElement(a, b));
    }
}
