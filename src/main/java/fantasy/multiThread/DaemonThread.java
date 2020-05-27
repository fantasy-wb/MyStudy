package fantasy.multiThread;

public class DaemonThread extends Thread{
    @Override
    public void run() {
        while (true) {
            System.out.println("I am Running!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        Thread.sleep(5000);
    }
}
