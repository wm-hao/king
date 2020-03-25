package share.king.util;

import share.king.dto.Response;

public class GateWayUtil {
    public static Response returnSuccessResponse(String result) {
        return new Response(Common.StatusCode.SUCCESS.getCode() + "", result, Common.StatusCode.SUCCESS.getDesc());
    }

    public static Response returnFailResponse(String result) {
        return new Response(Common.StatusCode.FAIL.getCode() + "", result, Common.StatusCode.FAIL.getDesc());
    }
}
