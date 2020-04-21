package 线程;

/**
 * @ClassName SingleGetA
 * @Deacription 通过volatile关键字修饰的单例模式，保证高并发时双检锁不会被指令重排
 * @Author peipei
 * @Date 2020/4/15 11:30
 * @Version 1.0
 **/

public class SingleGetA {
    private static volatile SingleGetA a;
    private  SingleGetA() {//空参构造器
        System.out.println("\t 这是通过volatile关键字修饰的单例模式");
    }
    public static SingleGetA getA() {//如果synchronized修饰方法的话，枷锁的次数会增加
        if (a == null) {
            synchronized (SingleGetA.class) {
                if (a == null) {
                    a = new SingleGetA();
                }
            }
        }
        return a;
    }
}
