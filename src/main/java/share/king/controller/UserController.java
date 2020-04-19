package share.king.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import share.king.dto.Response;
import share.king.entity.User;
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
    public Response insert(@RequestBody User user) {
        if (userSV.insert(user) == Common.StatusCode.SUCCESS.getCode()) {
            return GateWayUtil.returnSuccessResponse("用户注册成功");
        }
        return GateWayUtil.returnFailResponse("用户注册失败");
    }

    @PostMapping("validate")
    public Response validate(@RequestBody User user) {
        log.info("用户信息验证请求参数:" + user);
        String returnData = "用户验证失败";
        if (user != null && StringUtils.isNotBlank(user.getUserName())) {
            if (StringUtils.isNotBlank(user.getPassword()) || StringUtils.isNotBlank(user.getEmail())) {
                User dbUser = userSV.findByUserName(user.getUserName());
                boolean success = false;
                if (dbUser != null) {
                    if (StringUtils.isNotBlank(user.getPassword())) {
                        String password = user.getPassword();
                        String cipherText = Utils.getMD5(password);
                        String token;
                        if (StringUtils.equals(cipherText, dbUser.getPassword())) {
                            Object existToken = redisUtil.get(dbUser.getUserName());
                            if (existToken != null && StringUtils.isNotBlank(existToken.toString())) {
                                    token = existToken.toString();
                            } else {
                                token = TokenUtil.createJWT(-1, dbUser);
                            }
                            returnData = dbUser.getId() + "-" + token;
                            redisUtil.set(dbUser.getUserName(), token, tokenExpireHours * 60 * 60);
                            success = true;
                        }
                    } else {
                        if (StringUtils.equals(dbUser.getEmail(), user.getEmail())) {
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
        User user = userSV.findByUserName(userName);
        if (user == null) {
            return GateWayUtil.returnSuccessResponse("用户名不存在");
        }
        return GateWayUtil.returnFailResponse("用户名已存在");
    }

    @GetMapping("verifyCode")
    public Response getVerifyCode(@RequestParam String userName, @RequestParam String email) {
        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(email)) {
            User user = userSV.findByUserName(userName);
            if (user != null && StringUtils.equals(email, user.getEmail())) {
                String verifyCode = Utils.createVerificationCode(5);
                mailSV.sendSimpleMail(email.trim(), Common.FORGET_PASS_EMAIL_TLP_SUBJECT, Common.FORGET_PASS_EMAIL_TLP_CONTENT.replaceAll(Common.USER, userName).replaceAll(Common.VERIFY_CODE, verifyCode));
                return GateWayUtil.returnSuccessResponse(verifyCode);
            }
        }
        return GateWayUtil.returnFailResponse("获取验证码失败");
    }

    @PostMapping("updatePass")
    public Response updatePass(@RequestBody User user) {
        if (StringUtils.isNotBlank(user.getUserName()) && StringUtils.isNotBlank(user.getPassword())) {
            User dbUser = userSV.findByUserName(user.getUserName());
            if (dbUser == null) {
                return GateWayUtil.returnFailResponse("用户不存在，无法更新密码");
            }
            dbUser.setPassword(Utils.getMD5(user.getPassword()));
            if (Common.StatusCode.SUCCESS.getCode() == userSV.updateByPrimaryKeySelective(dbUser)) {
                return GateWayUtil.returnSuccessResponse("更新密码成功");
            }
        }
        return GateWayUtil.returnFailResponse("更新密码失败");
    }

    @PostMapping("update")
    public Response update(@RequestBody User user) {
        User dbUser = userSV.selectByPrimaryKey(user.getId());
        if (dbUser == null) {
            return GateWayUtil.returnFailResponse("用户信息查询失败");
        }
        if (!StringUtils.equals(dbUser.getPassword(), user.getPassword().trim())) {
            dbUser.setPassword(Utils.getMD5(user.getPassword()));
        }
        if (Common.StatusCode.SUCCESS.getCode() == userSV.updateByPrimaryKeySelective(dbUser)) {
            return GateWayUtil.returnSuccessResponse("更新用户信息成功");
        }
        return GateWayUtil.returnFailResponse("更新用户信息失败");
    }

    @GetMapping("qry")
    public Response qry(@RequestParam Integer id) {
        User user = userSV.selectByPrimaryKey(id);
        if (user != null) {
            return GateWayUtil.returnSuccessResponse(JSON.toJSONString(user));
        }
        return GateWayUtil.returnFailResponse("未查询到用户信息");
    }
}
