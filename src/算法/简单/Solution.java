package 算法.简单;

import java.util.Stack;

/**
 * @ClassName Solution
 * @Deacription LeetCode - 有效的括号
 * @Author peipei
 * @Date 2020/1/10 21:40
 * @Version 1.0
 **/

public class Solution {

    /**
     * 解题思路：
     *          1。如果s.length=0,return true
     *          2。如果s.length=1，return false
     *          3.如果s.length =2,并且符合（）/{}/【】，return true
     *          4.如果s的第一个字符是以闭标签开头，直接returnfalse
     *          5.利用栈的特性
     */
//    public static Map<Character,Character> map = new HashMap<Character, Character>()
//        {{put('(',')');put('[',']');put('{','}');}};
    public boolean isVald(String s) {
        int n = s.length();
        if ("".equals(s)) return true;
        if (n == 1) return false;
        if (n == 2) {
            switch (s) {
                case "[]":
                    return true;
                case "{}":
                    return true;
                case "()":
                    return true;
                default:
                    return false;
            }
        }
        if ((s.length() & 1) == 1) return false; //s.length() & 1判断是否是奇数 ，如果是返回1
        Stack<Character> stack = new Stack();
        for (int i = 0; i <s.length() ; i++) { //s = "[{]"；
            switch (s.charAt(i)) { //第一轮 [进入循环
                case '[':
                case '(':
                case '{':
                    stack.push(s.charAt(i)); //push的是[
                    continue;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false; continue; //如果stack的顶是[的话，就删掉他，此时stack还是空的
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false; continue;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false; continue;
            }
        }
        return stack.isEmpty();
    }


    /**
     * 看到大佬的消消乐式的解法
     */
    public boolean isVald2(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
           if (s.contains("{}"))
                s = s.replace("{}","");
            if (s.contains("[]"))
                s = s.replace("[]","");
            if (s.contains("()"))
                s = s.replace("()","");
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {
        String s = "[{()}[)]";
        String s2 = "[{()}]";
        int q = 11;
        Solution   ss = new Solution();
        System.out.println(ss.isVald(s));
        System.out.println(ss.isVald(s2));
    }

}
