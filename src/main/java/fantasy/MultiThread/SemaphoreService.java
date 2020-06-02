package fantasy.MultiThread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量测试
 * 作用是控制线程的并发数量。
 */
public class SemaphoreService {

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    // 同步关键类，构造方法传入的数字是多少，则同一个时刻，只运行多少个进程同时运行制定代码
    private Semaphore semaphore = new Semaphore(5);

    public void doSomething() {
        try {
            /**
             * 在 semaphore.acquire() 和 semaphore.release()之间的代码，同一时刻只允许制定个数的线程进入，
             * 因为semaphore的构造方法是1，则同一时刻只允许一个线程进入，其他线程只能等待。
             * */
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + ":doSomething start-" + getFormatTimeStr());
            Thread.sleep(2000);
//            System.out.println(Thread.currentThread().getName() + ":doSomething end-" + getFormatTimeStr());
            System.out.println("--------end doSomething end-----" + getFormatTimeStr());
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getFormatTimeStr() {
        return sf.format(new Date());
    }

    static class MyThread extends Thread {
        private SemaphoreService service;

        public MyThread(String name, SemaphoreService service) {
            super();
            this.setName(name);
            this.service = service;
        }

        @Override
        public void run() {
            this.service.doSomething();
        }
    }

    public static void main(String args[]) {
        SemaphoreService service = new SemaphoreService();
        for (int i = 0; i < 10; i++) {
            MyThread t = new MyThread("thread" + (i + 1), service);
            // 这里使用 t.run() 也可以运行，但是不是并发执行了
            t.start();
        }
    }
}