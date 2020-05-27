package fantasy.multiThread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


/**
 *      虽然AtomicIntegerFieldUpdater很好用，但是还是有几个注意事项^
 * 第一，Updater只能修改它可见范围内的变量，因为Updater使用反射得到这个变量。
 * 如果变量不可见，就会出错，比如score声明为private,就是不可行的。
 *
 * 第二，为了确保变量被正确的读取，它必须是volatile类型的
 *
 * 第三，由于CAS操作会通过对象实例中的偏移量直接进行陚值，
 * 因此，它不支持static 字段（Unsafe.objectFieldOffsetO方法不支持静态变量）
 *
 */

public class AtomicIntegerFieldUpdaterDemo {


    public static class Candidate {
        int id;
        volatile int score;
    }

    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater
            = AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");
    //检査Updater是否正确工作
    public static AtomicInteger allScore = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final Candidate stu = new Candidate();
        Thread[] t = new Thread[10000];
        for (int i = 0; i < 10000; i++) {
            t[i] = new Thread() {
                public void run() {
                    if (Math.random() > 0.4) {
                        scoreUpdater.incrementAndGet(stu);
                        allScore.incrementAndGet();

                    }
                }
            };

            t[i].start();
        }
        for (int i = 0; i < 10000; i++) {
            t[i].join();
        }
        System.out.println("scores="+ stu.score);
        System.out.println("allScore=" + allScore);
    }
}