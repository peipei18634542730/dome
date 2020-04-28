package 线程;

import sun.util.locale.provider.TimeZoneNameUtility;

import java.util.concurrent.*;

/**
 * @ClassName MyThreadPoolDeom
 * @Deacription 第四种获取/使用Java多线程的方式 - 线程池
 * @Author peipei
 * @Date 2020/4/28 15:20
 * @Version 1.0
 **/

public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();一池1线程
//        ExecutorService threadPool = Executors.newCachedThreadPool();//一池n线程
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1l,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
                new ThreadPoolExecutor.DiscardOldestPolicy() //抛弃队列中等待最久的任务
//                new ThreadPoolExecutor.DiscardPolicy() //抛弃未执行的任务
                );
        //模拟线程池处理十个业务
        try {
            for (int i = 0; i < 10; i++) {
                int num  = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务"+"已办理-"+(num+1));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
