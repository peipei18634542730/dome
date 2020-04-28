package 线程;

import com.sun.deploy.util.StringUtils;

import java.sql.SQLOutput;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ProdConsumer_BlockQueueDemo
 * @Deacription 线程交互阻塞队列版
 * @Author peipei
 * @Date 2020/4/27 22:49
 * @Version 1.0
 **/

public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) {
        Resource resource = new Resource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                resource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                resource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println(Thread.currentThread().getName()+"大老板main线程叫停，活动结束");
        try {
            resource.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


class Resource{//资源类
    private volatile boolean flag = true;//主标志位，进行生产和消费
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue blockingQueue = null;
    public Resource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public void myProd() throws Exception {
        String data = null;
        boolean resultVal;
        while (flag) {
            data = atomicInteger.incrementAndGet()+"";
            resultVal = blockingQueue.offer(data,2l,TimeUnit.SECONDS);
            if (resultVal) {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            } else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失敗");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 大老板叫停，意味着flag=false,生产动作结束");
    }
    public void myConsumer() throws Exception{
        String result = null;
        while (flag) {
            result= (String) blockingQueue.poll(2l, TimeUnit.SECONDS);
            if (result == null || "".equals(result)) {
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过两秒未取到信息"+result+"消费退出");
                System.out.println();
                System.out.println();
                return;
            } else {
                System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
            }
        }
    }
    public void stop() throws Exception{
        flag = false;
    }
}