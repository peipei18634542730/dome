package 线程;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName VOlatileTest2
 * @Deacription 验证volatile不保证原子性
 * @Author peipei
 * @Date 2020/4/15 9:54
 * @Version 1.0
 **/

public class VolatileTest2 {
    /*JMM的原子性：
    *            不可分割、完整性、也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整
    *             要么同时成功，要么同时失败
    *       why:
    *           num++ 底层分为3步 VOlatileTest2 ---》 VOlatileTest2.class -->字节码命令
    *   who do :
    *           1.synchronized关键字修饰方法
    *           2.concurrent.atomic
    *                              .atomicInteger(cas)
    */

    volatile int num = 0;
    void edit60(){
         this.num = 60;
    }
    void addPlus() {
        num++;
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    void addPlusAtomic() {
        atomicInteger.getAndIncrement();
    }
    public static void main(String[] args) {
        VolatileTest2 v = new VolatileTest2();
        for (int i = 0; i < 20; i++) {
            new Thread(() ->{
                for (int j = 0; j < 1000; j++) {
                    v.addPlus();
                    v.addPlusAtomic();
                }
            },String.valueOf(i)).start();
        }
        //等待上面的20个线程都全部算完后，再用main线程取得最终的结果值看是多少
        while (Thread.activeCount() > 2) {//jvm默认有两个线程main和jc线程。上面new了20个线程，只要进入循环说明还没有计算完
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int type finally num value = "+ v.num);//预计结果是20000
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type finally num value = "+ v.atomicInteger);//预计结果是20000
        //1.num未进行volatile修饰
        //2.
    }
}
