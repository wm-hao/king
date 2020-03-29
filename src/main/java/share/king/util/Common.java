package share.king.util;

public class Common {
    public enum StatusCode {
        SUCCESS(1, "成功"),
        FAIL(0, "失败");

        private int code;
        private String desc;

        StatusCode(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public int getCode() {
            return code;
        }
    }

    public static final int USER_STATE_U = 0;
    public static final int USER_STATE_E = 1;

    public static final String USER = "USER_NAME";
    public static final String VERIFY_CODE = "VERIFY_CODE";
    public static final String FORGET_PASS_EMAIL_TLP_SUBJECT = "找回密码";
    public static final String FORGET_PASS_EMAIL_TLP_CONTENT = "尊敬的" + USER + ":您好，您通过忘记密码功能,获取的验证码为:" + VERIFY_CODE + "，请勿将此验证码授予他人";
}
