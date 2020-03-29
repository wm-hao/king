package king;

import org.junit.Test;
import share.king.util.Utils;

public class EasyTest {
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
}
