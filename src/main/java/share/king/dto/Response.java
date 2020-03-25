package share.king.dto;

import java.io.Serializable;

public class Response implements Serializable {

    private String code;
    private String result;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Response(String code, String result, String desc) {
        this.code = code;
        this.result = result;
        this.desc = desc;
    }

    public Response() {
    }
}
