package 算法.简单;

/**
 * @ClassName AddBInary
 *@Deacription 二进制相加
 * @Author peipei
 * @Date 2020/2/17 20:14
 * @Version 1.0
 **/

public class AddBInary {
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;//记录每次循环相加的和,如果是1,0或者是0,1的话,其值为0.为1,1时其值为1
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {// i >= 0 || j >= 0 将两个字符串的长度补齐,意味着不论两个字符串的长度是否相同,都能循环长的字符串的长度的次数
            int sum = ca;//如果前一次是1,1相加,需要进位
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);//1010
            ca = sum / 2;//0101
        }
        ans.append(ca == 1 ? ca : "");//加到最后一位,如果是一的话,说明是1和1相加,序号进位
        return ans.reverse().toString();
    }
}
