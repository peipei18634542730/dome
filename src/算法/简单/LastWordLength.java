package 算法.简单;

/**
 * @ClassName LastWordLength
 * @Deacription 最后一个单词的长度
 * @Author peipei
 * @Date 2020/2/12 19:46
 * @Version 1.0
 **/

public class LastWordLength {
    /**
     *我的思路：直接调用string的方法，将字符串以‘ ’为准，分割成数组，取最后一个字符串的长就可以了
     * 刚开始以为s.split(" ")不会将类似"sdf sssko popk         "的字符串截取成功，浪费了很多时间
     */
    public int lastWordLen(String s) {
//        int end = s.length();
        if (s.length() < 1) return 0;
        char[] chars = s.toCharArray();
//        for (int i = end; i >= 0; i--) {
//            if (chars[end - 1] != ' ') break;
//        }
//        s.substring(0,end);
        String[] s1 = s.split(" ");
        return s1[s1.length - 1].length();
    }

    /**
     *这个是大神的思路：
     *      直接从最后一个字符判断，如果是空格，end就-1，碰到不是‘ ’的，跳出第一个循环。
     */
    public int lastWordLen2(String s) {
        int end = s.length() - 1;
        while (end >=0 && s.charAt(end) == ' ') {end --;}
        if (end <0) return 0;
        int start = 0;
        while (end >= 0 && s.charAt(end) != ' ') { end --;start ++;}
        return start;
    }


    public static void main(String[] args) {
        LastWordLength lastWordLength = new LastWordLength();
        String s = "sd popk ";
        System.out.println(lastWordLength.lastWordLen2(s));
//        System.out.println(lastWordLength.lastWordLen(s));
    }
}
