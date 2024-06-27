package net.cowfish.common;

import net.cowfish.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class TokenInterceptor implements HandlerInterceptor {
    private final RedisService redisService;
    @Autowired
    public TokenInterceptor(RedisService redisService){
        this.redisService = redisService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从HTTP头信息中取得token
        String token = request.getHeader("Authorization");
 
        // 实现你的token验证逻辑，例如检查token是否有效
        boolean isTokenValid = validateToken(token);
        if (!isTokenValid) {
            throw new InvalidTokenException("Token has expired or is invalid.");
        }
//        if (!isTokenValid) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false; // 如果token无效，则不继续执行请求
//        }
 
        return true; // 如果token有效，则继续执行请求
    }
 
    private boolean validateToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        // 这里实现你的token验证逻辑
        // 比如查询数据库，对比token是否有效等
        String username = redisService.getString(token);
        if (username == null) {
            return false;
        }
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 可以在这里添加你想要执行的代码
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 可以在这里添加你想要执行的代码
    }
}