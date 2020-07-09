package fantasy.MultiThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
 
    public static void main(String[] args) throws InterruptedException {
//        CyclicBarrier支持一个回调函数，每当一轮线程结束后，下一轮线程开始前，这个回调函数都会被调用一次，
//        而且这个回调函数是执行在一个回合里最后执行await()的线程上。
//        注意代码中注释了一行，注释的写法意味着新开一个线程执行回调函数，那么回调函数会异步执行。
       //final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> new Thread(() -> System.out.println("Wait for batch finish!")).start());
       final CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println("Wait for batch finish!"));
 
        for(int i = 0; i < 14; i++){
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "开始等待其他线程");
                    cyclicBarrier.await();
                    Thread.sleep(2000);
                    System.out.println("The batch has executed! time =" + System.currentTimeMillis());
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(5000);
        // 最后再加入一个线程进入 cyclicBarrier.await()方法
        new Thread(() -> {
            try {
                System.out.println("·最后一个---------开始等待其他线程");
                cyclicBarrier.await();
                System.out.println("The batch has executed! time =" + System.currentTimeMillis());
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

    }
}