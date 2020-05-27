package fantasy.MultiThread;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class SimpleDome extends Thread {

    //第二种：报各种异常
    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String str;

    public SimpleDome(String str) {
        this.str = str;
    }

    public Date stToDate() {
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        try {
            Date date = simpleDateFormat.parse(str);
            System.out.println(str + "=" + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}


public class SimpleDateFormatDome {

    public static void main(String args[]) {


        //第二种：报各种异常
        final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        new Thread(new Runnable() {
            public void run() {
                String str = "2017-01-10";
                Date date = new SimpleDome(str).stToDate();
                System.out.println(str + "=" + date);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                String str = "2017-01-11";
                Date date = new SimpleDome(str).stToDate();
                System.out.println(str + "=" + date);
            }
        }).start();
    }
}
