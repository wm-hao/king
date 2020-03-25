package share.king.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "请求方式不允许")
public class TestException extends RuntimeException{

}
