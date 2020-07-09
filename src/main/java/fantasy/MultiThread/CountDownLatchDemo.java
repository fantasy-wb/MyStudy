package fantasy.MultiThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * countLatch 使用
 *
 * 调用countDown方法会减少计数器的值，
 * 调用await方法会阻塞直到当前计数达到零，之后释放所有等待的线程
 */
public class CountDownLatchDemo {
 
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        ExecutorService executor = Executors.newFixedThreadPool(15);
        for(int i = 0; i < 5; i++){
            executor.execute(() -> {
                latch.countDown();
                System.out.println("First batch has executed! time = " + System.currentTimeMillis());
            });
        }
 
        for(int i = 0; i < 8; i++){
            executor.execute(() -> {
                try {
                    latch.await();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Second batch has executed! time = " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
 
        while(latch.getCount() != 1){
            Thread.sleep(1000);
        }
        latch.countDown();

        System.out.println("Wait for first batch finish!");
 
    }
}