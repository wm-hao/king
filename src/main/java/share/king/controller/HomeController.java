package share.king.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Server is normal";
    }


    @GetMapping("/error405")
    public ResponseEntity http405() {
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
