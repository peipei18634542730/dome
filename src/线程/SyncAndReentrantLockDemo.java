package 线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName SyncAndReentranLockDemo
 * @Deacription A打印5次，B打印10次，C打印15次。。。。。。来10轮
 * @Author peipei
 * @Date 2020/4/27 20:01
 * @Version 1.0
 **/

class Share{//资源类
    private int number = 1;// 1=A,2=B,3=C
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();//记得枷锁和释放锁，不然IllegalMonitorStateException
        try {
            //判断
            while (number != 1){
                //说明进来的不是A线程，要等待
                c1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 2;
            c2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (number != 2){
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (number != 3){
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 1;
            c1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}


public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                share.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                share.print10();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                share.print15();
            }
        },"C").start();
    }
}
