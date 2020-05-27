package fantasy.MultiThread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类(使用了ThreadLocal获取SimpleDateFormat,其他方法可以直接拷贝common-lang)
 */
public class DateUtil {

    private static Map<String,ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();


    public final static String MDHMSS = "MMddHHmmssSSS";
    public final static String YMDHMS = "yyyyMMddHHmmss";
    public final static String YMDHMS_ = "yyyy-MM-dd HH:mm:ss";
    public final static String YMD = "yyyyMMdd";
    public final static String YMD_ = "yyyy-MM-dd";
    public final static String HMS = "HHmmss";

    /**
     * 根据map中的key得到对应线程的sdf实例
     * @param pattern map中的key
     * @return 该实例
     */
    private static SimpleDateFormat getSdf(final String pattern){
        ThreadLocal<SimpleDateFormat> sdfThread = sdfMap.get(pattern);
        if (sdfThread == null){
            //双重检验,防止sdfMap被多次put进去值,和双重锁单例原因是一样的
            synchronized (DateUtil.class){
                sdfThread = sdfMap.get(pattern);
                if (sdfThread == null){
                    System.out.println(("put new sdf of pattern " + pattern + " to map"));
                    sdfThread = new ThreadLocal<SimpleDateFormat>(){
                        @Override
                        protected SimpleDateFormat initialValue() {
                            System.out.println(("thread: " + Thread.currentThread() + " init pattern: " + pattern));
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern,sdfThread);
                }
            }
        }
        return sdfThread.get();
    }

    /**
     * 按照指定pattern解析日期
     * @param date 要解析的date
     * @param pattern 指定格式
     * @return 解析后date实例
     */
    public static Date parseDate(String date, String pattern){
        if(date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        try {
            return  getSdf(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(("解析的格式不支持:" + pattern));
        }
        return null;
    }
    /**
     * 按照指定pattern格式化日期
     * @param date 要格式化的date
     * @param pattern 指定格式
     * @return 解析后格式
     */
    public static String formatDate(Date date,String pattern){
        if (date == null){
            throw new IllegalArgumentException("The date must not be null");
        }else {
            return getSdf(pattern).format(date);
        }
    }

    public static void main(String[] args) {
        DateUtil.formatDate(new Date(),MDHMSS);
        new Thread(()->{
            DateUtil.formatDate(new Date(),MDHMSS);
        }).start();
        new Thread(()->{
            DateUtil.formatDate(new Date(),MDHMSS);
        }).start();
    }
}