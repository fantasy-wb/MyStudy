package fantasy.MultiThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class SingletonTest {

    private  SingletonTest(){
    }

    private static SingletonTest singletonTest;

    public static SingletonTest getInstance() {
        if (singletonTest == null) {
            synchronized(SingletonTest.class) {
                if (singletonTest == null) {
                    singletonTest = new SingletonTest();
                }
            }
        }
        return singletonTest;
    }

    public static void use(SingletonTest singletonTest) {
        if (null == singletonTest) {
            throw new NullPointerException();
        }
        System.out.println("No Problem!");
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1000);
        for (int i =0; i < 1000; i++) {
            Thread thread = new Thread(new Work(cyclicBarrier));
            thread.start();
        }
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };
    }
    static class Work implements Runnable {

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
                SingletonTest instance = SingletonTest.getInstance();
                use(instance);
                System.out.println(Thread.currentThread().getName() + "启动时间是" + System.currentTimeMillis());
            } catch (InterruptedException | BrokenBarrierException e1) {
                e1.printStackTrace();
            }
        }

    }
}
