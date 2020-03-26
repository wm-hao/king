package share.king.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import share.king.util.GateWayUtil;
import share.king.util.RedisUtil;
import share.king.util.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static transient Log log = LogFactory.getLog(TokenInterceptor.class);

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        log.error("请求:url=" + request.getRequestURL() + ";token=" + token);
        response.setCharacterEncoding("utf-8");
        if (StringUtils.isBlank(token)) {
            response.getWriter().println(JSON.toJSONString(GateWayUtil.returnFailResponse("无token信息")));
            return false;
        }
        String userName = TokenUtil.getUserName(token);
        if (StringUtils.isBlank(userName)) {
            response.getWriter().println(JSON.toJSONString(GateWayUtil.returnFailResponse("token信息有误")));
            return false;
        }
        Object object = redisUtil.get(userName);
        if (object == null) {
            response.getWriter().println(JSON.toJSONString(GateWayUtil.returnFailResponse("token已过期，请重新登录")));
            return false;
        }
        if (!StringUtils.equals(token, object.toString())) {
            response.getWriter().println(JSON.toJSONString(GateWayUtil.returnFailResponse("token不正确")));
            return false;
        }
        return true;
    }
}
