package share.king.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyyMMddHH_mm_ss = "yyyyMMddHH:mm:ss";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyyMM = "yyyyMM";

    public static Timestamp getTimestampByFormat(String date, String format) {
        Timestamp time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            time = new Timestamp(simpleDateFormat.parse(date).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    public static boolean checkDateFormat(String dateStr, String format) throws Exception {
        boolean result = false;
        if (StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(format)) {
            if (dateStr.length() == format.length()) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    sdf.setLenient(false);
                    Date date = sdf.parse(dateStr);
                    result = true;
                } catch (Exception e) {

                }
            }
        }
        return result;
    }

    public static String getStringByFormat(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static boolean isSameDate(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(date1);
            d2 = format.parse(date2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setFirstDayOfWeek(Calendar.MONDAY);
        cal2.setFirstDayOfWeek(Calendar.MONDAY);
        cal1.setTime(d1);
        cal2.setTime(d2);
        return cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR);
    }

}
