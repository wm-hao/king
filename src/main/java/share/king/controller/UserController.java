package share.king.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.king.entity.UserEntity;
import share.king.service.interfaces.IUserSV;
import share.king.util.Common;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserSV userSV;

    @PostMapping(value = "insert")
    public String insert(@RequestBody UserEntity userEntity) {
        if (userSV.insert(userEntity) == Common.StatusCode.SUCCESS.getCode()) {
            return Common.StatusCode.SUCCESS.getDesc();
        }
        return Common.StatusCode.FAIL.getDesc();
    }
}
