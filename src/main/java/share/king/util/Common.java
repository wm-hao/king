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
}
