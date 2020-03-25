package share.king.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static final String YYYYMMDDHH24SS = "yyyyMMddHHmmss";

    public static Date getTimestampByFormat(String date, String format) {
        Date time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            time = simpleDateFormat.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
}
