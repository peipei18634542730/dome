package 算法.简单;

/**
 * @ClassName StrStr
 * @Deacription LeetCode - 实现Strstr
 * @Author peipei
 * @Date 2020/1/15 23:11
 * @Version 1.0
 **/

public class StrStr {
    /**
     * 解题思路：
     *          利用双指针算法
     */
    public int getStrStrLen(String lStr,String sStr) {
        //lStr表示长的字符串，sStr表示短的字符串
        char[] lChars = lStr.toCharArray();
        char[] sShars = sStr.toCharArray();
        int i = 0,j = 0;
        while (i < lStr.length() && j < sStr.length()) {
            if (lChars[i] == sShars [j]) { //长的和短的数组去比较，如果相等的话，就用短的下个字符和长的下一个字符去比较
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;//如果不相等的话，就用短的第一个字符和长的下一个字符去比较
            }
        }
        if (j == sShars.length)  return i - j;//其实j就表示记录相等字符的次数，当j == sShars.length时，说明长的字符串里已经完全包含了短的字符
        return -1;
    }

    public static void main(String[] args) {
        StrStr strStr = new StrStr();
        String l = "abccabv";
        String s = "cc";
        System.out.println(strStr.getStrStrLen(l, s));
    }
}
