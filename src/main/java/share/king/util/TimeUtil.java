package share.king.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyyMMdd = "yyyyMMdd";
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

}
