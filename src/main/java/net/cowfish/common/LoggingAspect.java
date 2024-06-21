package net.cowfish.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
 
    @Before("execution(* net.cowfish.controller..*.*(..))") // 替换为你的包路径
    public void logBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logger.info("Entering in Method: " + signature.getMethod().getName());
        Object[] args = joinPoint.getArgs();
        String params = "";
        //对入参进行遍历
        for (Object o : args)
        {
            if (o != null)
            {
                try
                {
                    /**
                     * 如果参数类型是请求和响应的http，则不需要拼接；因为这两个参数，使用JSON.toJSONString()转换会抛异常
                     * “It is illegal to call this method if the current request is not in asynchronous mode”
                     */
                    if (o instanceof HttpServletRequest
                            || o instanceof HttpServletResponse)
                    {
                        continue;//跳过拼接
                    }
                    //需要拼接
                    Object jsonObj = JSON.toJSON(o);
                    params += jsonObj.toString() + " ";
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        if (args != null) {
            logger.info("Arguments: " + params);
        }
    }
 
    @AfterReturning(pointcut = "execution(* net.cowfish.controller..*.*(..))", returning = "result") // 替换为你的包路径
    public void logAfter(Object result) {
        logger.info("Returning from method: " + JSON.toJSON(result));
    }
}