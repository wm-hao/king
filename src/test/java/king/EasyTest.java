package king;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import share.king.util.Utils;

import java.text.DecimalFormat;

public class EasyTest {

    private static transient Log log = LogFactory.getLog(EasyTest.class);

    @org.junit.Test
    public void testMd5() {
        System.out.println(Utils.getMD5("1c63129ae9db9c60c3e8aa94d3e00495"));
    }

    @Test
    public void createVerifyCode() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Utils.createVerificationCode(5));
        }
    }

    @Test
    public void testDecimal() {
        DecimalFormat decimalFormat = new DecimalFormat("##%");
        int a = 2;
        int b = 30;
        double r = (2 * 1.0) / (30 * 1.0);
        log.error(r);
        log.error(decimalFormat.format(r));
    }
}
