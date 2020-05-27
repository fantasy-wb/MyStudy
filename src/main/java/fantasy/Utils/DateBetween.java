package fantasy.Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * 两个日期之间相隔数
 */
public class DateBetween {

    /**
     * 只考虑日期之前的差距 不考虑时间
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new RuntimeException("日期不能为空");
        }
        LocalDate localDate1 = date2LocalDate(date1);
        LocalDate localDate2 = date2LocalDate(date2);
//        同一月才行
//        Period next = Period.between(localDate1, localDate2);
//        System.out.println("相隔多少天：" + next.getDays());
        System.out.println(localDate1.until(localDate2, ChronoUnit.DAYS));
        return (int) (localDate2.toEpochDay() - localDate1.toEpochDay());
    }

    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

    public static void main(String[] args) throws ParseException {
        String dateStr = "2020-1-1 21:21:28";
        String dateStr2 = "2021-1-1 21:21:20";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date2 = format.parse(dateStr2);
        Date date = format.parse(dateStr);
        System.out.println(differentDays(date, date2));
    }
}
