package share.king.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class Utils {

    private static final String SALT = "abc123";

    public static String getMD5(String plainText) {
        if (StringUtils.isNoneBlank(plainText)) {
            return DigestUtils.md5Hex(SALT + plainText);
        }
        return null;
    }

}
