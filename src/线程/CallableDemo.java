package 线程;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName Callable
 * @Deacription
 * @Author peipei
 * @Date 2020/4/28 14:37
 * @Version 1.0
 **/
class myCallable implements Callable <Integer>{//callable和Runnable的区别：1.可以指定泛型，2有返回值

    @Override
    public Integer call() throws Exception {
        System.out.println("********** come in callable");
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new myCallable());
        Thread t1 = new Thread(futureTask);
        t1.start();
        int result = 100;
        int result2 = (int) futureTask.get();//建议放在最后。因为get方法要求计算callable线程的结果 ，如果没有计算完成就要强求，会导致线程阻塞，直到计算完成
        System.out.println("result="+(result + result2));
    }
}
