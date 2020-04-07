package share.king.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import share.king.dto.Response;
import share.king.exception.BaseException;
import share.king.util.GateWayUtil;

@ControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(value = {BaseException.class, RuntimeException.class})
    @ResponseBody
    public Response customException(Exception e) {
        return GateWayUtil.returnFailResponse(e.getMessage());
    }

}