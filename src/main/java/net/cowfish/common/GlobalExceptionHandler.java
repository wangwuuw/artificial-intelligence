
package net.cowfish.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//全局捕获异常类
@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public ResponseWrapper<Object> resultError(Exception e) {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.error("发生错误了",e);
		return ResponseWrapper.fail("系统错误!");
	}
	// 捕获Token异常
	@ExceptionHandler(InvalidTokenException.class)
	@ResponseBody
	public ResponseWrapper<Object> handleInvalidTokenException(InvalidTokenException e) {
		// 返回401未授权状态码和错误信息
		return ResponseWrapper.fail("未登录,请先登录!",401);
	}

}
