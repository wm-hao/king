package share.king.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import share.king.dto.Response;
import share.king.entity.UserEntity;
import share.king.service.interfaces.IUserSV;
import share.king.util.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private static transient Log log = LogFactory.getLog(UserController.class);

    @Autowired
    IUserSV userSV;

    @Autowired
    RedisUtil redisUtil;

    @PostMapping(value = "insert")
    public String insert(@RequestBody UserEntity userEntity) {
        if (userSV.insert(userEntity) == Common.StatusCode.SUCCESS.getCode()) {
            return Common.StatusCode.SUCCESS.getDesc();
        }
        return Common.StatusCode.FAIL.getDesc();
    }

    @PostMapping("validate")
    public Response validate(@RequestBody UserEntity userEntity) {
        log.error("用户信息验证请求参数:" + userEntity);
        if (userEntity != null && StringUtils.isNotBlank(userEntity.getUserName()) && StringUtils.isNoneBlank(userEntity.getUserName())) {
            String password = userEntity.getPassword();
            String cipherText = Utils.getMD5(password);
            UserEntity user = userSV.findByUserName(userEntity.getUserName());
            if (user != null && StringUtils.equals(cipherText, user.getPassword())) {
                String token = TokenUtil.createJWT(-1, user);
                redisUtil.set(user.getUserName(), token, 60 * 60);
                return GateWayUtil.returnSuccessResponse(token);
            }
        }
        return GateWayUtil.returnFailResponse("用户验证失败");
    }

    @GetMapping("exists")
    public Response exists(@RequestParam String userName) {
        log.error("校验用户名是否存在请求参数:" + userName);
        UserEntity userEntity = userSV.findByUserName(userName);
        if (userEntity == null) {
            return GateWayUtil.returnSuccessResponse("");
        }
        return GateWayUtil.returnFailResponse("用户名已存在");
    }
}
