package fantasy.multiThread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class ParseDate implements Runnable{
        int i = 0;
        public ParseDate (int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Date t = sdf.parse("2019-12-12 12:23:" + i % 60);
                if (i % 60 != t.getSeconds()){
                    System.out.println(i % 60 + ":" + t.getSeconds());
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            executorService.submit(new ParseDate(i));
        }
    }
}
