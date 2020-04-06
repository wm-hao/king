package share.king.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class Utils {

    private static final String SALT = "abc123";

    public static String getMD5(String plainText) {
        if (StringUtils.isNoneBlank(plainText)) {
            return DigestUtils.md5Hex(SALT + plainText);
        }
        return null;
    }

    /**
     * 生成指定长度的验证码
     */
    public static final String createVerificationCode(int verificationCodeLength) {
        String[] verificationCodes = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        };
        StringBuffer verificationCode = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < verificationCodeLength; i++) {
            verificationCode.append(verificationCodes[random.nextInt(verificationCodes.length)]);
        }
        return verificationCode.toString();
    }

    public static String convertWeekDay(int weekDay) {
        String weekDatDesc;
        switch (weekDay) {
            case 1:
                weekDatDesc = "星期天";
                break;
            case 2:
                weekDatDesc = "星期一";
                break;
            case 3:
                weekDatDesc = "星期二";
                break;
            case 4:
                weekDatDesc = "星期三";
                break;
            case 5:
                weekDatDesc = "星期四";
                break;
            case 6:
                weekDatDesc = "星期五";
                break;
            case 7:
                weekDatDesc = "星期六";
                break;
            default:
                weekDatDesc = "未知";
        }
        return weekDatDesc;
    }

}
