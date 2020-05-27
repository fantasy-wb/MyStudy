package fantasy.MultiThread;

/**
 *  单纯的使用 volatile 修饰的变量
 *  并不能保证 线程安全
 */
public class Test {
    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Test test = new Test();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++)
                    test.increase();
            }).start();
        }
        // debug 模式启动
        while (Thread.activeCount() > 1) //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
}