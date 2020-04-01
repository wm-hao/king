package share.king.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import share.king.dto.Response;
import share.king.entity.UserEntity;
import share.king.service.interfaces.IMailSV;
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

    @Autowired
    IMailSV mailSV;

    @Value("${token.expire.hours}")
    private long tokenExpireHours;

    @PostMapping(value = "insert")
    public Response insert(@RequestBody UserEntity userEntity) {
        if (userSV.insert(userEntity) == Common.StatusCode.SUCCESS.getCode()) {
            return GateWayUtil.returnSuccessResponse("用户注册成功");
        }
        return GateWayUtil.returnFailResponse("用户注册失败");
    }

    @PostMapping("validate")
    public Response validate(@RequestBody UserEntity userEntity) {
        log.info("用户信息验证请求参数:" + userEntity);
        String returnData = "用户验证失败";
        if (userEntity != null && StringUtils.isNotBlank(userEntity.getUserName())) {
            if (StringUtils.isNotBlank(userEntity.getPassword()) || StringUtils.isNotBlank(userEntity.getEmail())) {
                UserEntity user = userSV.findByUserName(userEntity.getUserName());
                boolean success = false;
                if (user != null) {
                    if (StringUtils.isNotBlank(userEntity.getPassword())) {
                        String password = userEntity.getPassword();
                        String cipherText = Utils.getMD5(password);
                        if (StringUtils.equals(cipherText, user.getPassword())) {
                            String token = TokenUtil.createJWT(-1, user);
                            returnData = user.getId() + "-" + token;
                            redisUtil.set(user.getUserName(), token, tokenExpireHours * 60 * 60);
                            success = true;
                        }
                    } else {
                        if (StringUtils.equals(userEntity.getEmail(), user.getEmail())) {
                            returnData = "用户名与邮箱匹配成功";
                            success = true;
                        }
                    }
                    if (success) {
                        return GateWayUtil.returnSuccessResponse(returnData);
                    }
                }
            }
        }
        return GateWayUtil.returnFailResponse(returnData);
    }

    @GetMapping("exists")
    public Response exists(@RequestParam String userName) {
        log.info("校验用户名是否存在请求参数:" + userName);
        UserEntity userEntity = userSV.findByUserName(userName);
        if (userEntity == null) {
            return GateWayUtil.returnSuccessResponse("");
        }
        return GateWayUtil.returnFailResponse("用户名已存在");
    }

    @GetMapping("verifyCode")
    public Response getVerifyCode(@RequestParam String userName, @RequestParam String email) {
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(email)) {
            UserEntity user = userSV.findByUserName(userName);
            if (user != null && StringUtils.equals(email, user.getEmail())) {
                String verifyCode = Utils.createVerificationCode(5);
                mailSV.sendSimpleMail(email.trim(), Common.FORGET_PASS_EMAIL_TLP_SUBJECT, Common.FORGET_PASS_EMAIL_TLP_CONTENT.replaceAll(Common.USER, userName).replaceAll(Common.VERIFY_CODE, verifyCode));
                return GateWayUtil.returnSuccessResponse(verifyCode);
            }
        }
        return GateWayUtil.returnFailResponse("获取验证码失败");
    }

    @PostMapping("updatePass")
    public Response updatePass(@RequestBody UserEntity userEntity) {
        if (StringUtils.isNotBlank(userEntity.getUserName()) && StringUtils.isNotBlank(userEntity.getPassword())) {
            UserEntity user = userSV.findByUserName(userEntity.getUserName());
            if (user == null) {
                return GateWayUtil.returnFailResponse("用户不存在，无法更新密码");
            }
            user.setPassword(Utils.getMD5(userEntity.getPassword()));
            if (Common.StatusCode.SUCCESS.getCode() == userSV.updateByPrimaryKeySelective(user)) {
                return GateWayUtil.returnSuccessResponse("更新密码成功");
            }
        }
        return GateWayUtil.returnFailResponse("更新密码失败");
    }
}
