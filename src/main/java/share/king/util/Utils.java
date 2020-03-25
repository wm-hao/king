package share.king.util;


import java.security.MessageDigest;
import java.util.Base64;

public class Utils {

    public static String getMD5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] digest = md.digest();
            return new String(Base64.getEncoder().encode(digest));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
