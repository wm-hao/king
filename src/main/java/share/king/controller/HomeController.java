package share.king.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import share.king.exception.TestException;

@Controller
@CrossOrigin
public class HomeController {
    private static transient Log log = LogFactory.getLog(HomeController.class);

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Server is normal";
    }


    @GetMapping("/error405")
    @ResponseBody
    public String http405() {
        throw new TestException();
    }


    @GetMapping("/error999")
    public String error999() {
        log.error("测试全局controller异常处理");
        throw new RuntimeException("测试异常");
    }
}
