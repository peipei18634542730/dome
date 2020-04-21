package 线程;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName VolatileTest
 * @Deacription 验证volatile关键字的可见性
 * @Author peipei
 * @Date 2020/4/15 9:34
 * @Version 1.0
 **/

public class VolatileTest {
//    int num = 0; 如果num为被volatile关键字修饰，main线程读取不到AAA线程对num值的改变，main线程会被困在while循环中
    volatile int num = 0;
    public int edit60() {
        return num = 60;
    }

    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            volatileTest.edit60();
            System.out.println(Thread.currentThread().getName()+"\t updated num value"+volatileTest.num);

        },"AAA").start();
        while (volatileTest.num == 0) {
            //让主线程main一直在这里等待，知道num值进行了改变
        }
        System.out.println(Thread.currentThread().getName()+"\t work over - "+volatileTest.num);
    }
}
