package 线程;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName DeadLockDemo
 * @Deacription 模拟死锁：两个或两个以上的进程在执行的过程中，因争抢资源造成的相互等待现象，若无外力干涉，将无法推进
 * @Author peipei
 * @Date 2020/4/28 20:12
 * @Version 1.0
 **/
class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()+"\t 自己持有锁"+lockA+"尝试获得"+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName()+"\t 自己持有锁"+lockB+"尝试获得"+lockA);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"线程A").start();
        new Thread(new HoldLockThread(lockB,lockA),"线程B").start();
    }
}
