package 算法.简单;

/**
 * @ClassName PublicPrefix
 * @Deacription LeetCode - 最长公共前缀
 * @Author peipei
 * @Date 2020/1/10 0:07
 * @Version 1.0
 **/

public class PublicPrefix {
    /**
     * 解题思路：
     *          1.如果输入的字符串数组长度为0，直接return “”
     *          2.如果有字符串是“”，直接return“”
     *          3.如果数组长度为1，直接return字符串
     *          3.如果第一个字符串的首字母与其他字符串的首字母都不匹配，return “”
     *          4.如果最短的字符串是其他字符串的前缀，直接return 最短的字符串
     *          5.依次用其他最短的末尾和其他字符串相对应的位置进行匹配，循环结束没有相同的，直接return “”
     */
    static String LongStrCommonPrefix(String[]strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String min = strs[0];
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].isEmpty()) {
                return  "";
            }
            if (strs[0].charAt(0) != strs[i].charAt(0)) {
                return "";
            }
            if (min.length() > strs[i].length()) {
                min = strs[i];
            }
        }
        for (int i = 0; i < strs.length; i++) {
            if (min.equals(strs[i])) { //如果数组内所有的字符串都一样的话，跳出循环
                continue;
            }
            for (int j = min.length()-1;j > 0;j--) {
                if (min.charAt(j) != strs[i].charAt(j)) {//最短的字符串依次和其他字符串进行比较，所以是strs[i]
                    min = min.substring(0,j); //注意这里的j是已经减1的
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        String[] strs = {"uow","flosss","flwerssde"};
        String s = LongStrCommonPrefix(strs);
        System.out.println(s);
    }
}
