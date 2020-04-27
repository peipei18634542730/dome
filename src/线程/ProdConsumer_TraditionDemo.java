package 线程;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData{//资源类
    private int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    /*加1*/
    public void increment() throws Exception{
        lock.lock();
        try {
            //1.判断
            while (0 != num) {
                //等待不能生产
                condition.await();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /*减1*/
    public void decrement() throws Exception{
        lock.lock();
        try {
            //1.判断
            while (0 == num) {
                //等待不能生产
                condition.await();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName()+"\t"+num);
            //唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
/**
 * @ClassName ProdConsumer_TraditionDemo
 * @Deacription
 *   题目：一个初始值为0的变量，两个线程交替操作，一个加1一个减1，来5轮
 *   1.线程 操作 资源类
 *   2.判断 干活 通知,三步操作可用synchronized枷锁，等待wait和唤醒 notify,
 *   3.防止虚假唤醒
 * @Author peipei
 * @Date 2020/4/24 11:14
 * @Version 1.0
 **/

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"aa").start();

        new Thread(() -> {
            for (int i = 1; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"bb").start();
    }


}
