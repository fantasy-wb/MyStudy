package fantasy.MultiThread;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i=0; i<12; i++) {
            Thread thread = new Thread(new Work(cyclicBarrier));
            thread.setName("线程-" + (i+1));
            thread.start();
        }
    }
}

class Work implements Runnable {

    private final CyclicBarrier cyclicBarrier;

    public Work(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            /**
             * CyclicBarrier类的await()方法对当前线程（运行cyclicBarrier.await()代码的线程）进行加锁，然后进入await状态；
             * 当进入CyclicBarrier类的线程数（也就是调用cyclicBarrier.await()方法的线程）等于初始化CyclicBarrier类时配置的线程数时；
             * 然后通过signalAll()方法唤醒所有的线程。
             */
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + "启动时间是" + System.currentTimeMillis());
        } catch (InterruptedException | BrokenBarrierException e1) {
            e1.printStackTrace();
        }
    }

}