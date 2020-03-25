package share.king.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import share.king.exception.TestException;

@Controller
@CrossOrigin
public class HomeController {

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
}
