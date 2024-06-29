
package net.cowfish.controller;


import net.cowfish.dao.ParamMapper;
import net.cowfish.entity.*;
import net.cowfish.service.RedisService;
import net.cowfish.common.ResponseWrapper;
import net.cowfish.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class UserController {
	@Value("${user.expire-time}")
	private Long expireTime;
	@Autowired
	private UserService userService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private ParamMapper paramMapper;
	/**
	 * @Description: 要求：
	 *               用户名：
	 *               必须是6-10位字母、数字、下划线（这里字母、数字、下划线是指任意组合，没有必须三类均包含） 不能以数字开头
	 *               密码：
	 *               必须是6-20位的字母、数字、下划线（这里字母、数字、下划线是指任意组合，没有必须三类均包含）
	 * @return
	 */
	@PostMapping("user/register")
	public ResponseWrapper register(@RequestBody @Valid RegisterRequest request){
		/**
		 * 校验两次密码是否一致
		 */
		String name = request.getName();
		String password = request.getPassword();
		String repassword = request.getRepassword();
		if (!password.equals(repassword)) {
			return ResponseWrapper.fail("两次密码不一致");
		}
		/**
		 * 校验用户名和密码格式是否一致
		 */
		boolean checkNameFormat = checkNameFormat(name);
		if (!checkNameFormat) {
			return ResponseWrapper.fail("用户名格式错误");
		}
		boolean checkPwdFormat = checkPwdFormat(password);
		if (!checkPwdFormat) {
			return ResponseWrapper.fail("密码格式错误");
		}
		/**
		 * 检验用户名是否存在
		 */
		boolean checkIsExist = userService.checkIsExist(name);
		if (checkIsExist) {
			return ResponseWrapper.fail("用户名已存在");
		}
		boolean register = userService.register(request);
		if (!register) {
			return ResponseWrapper.fail("注册失败");
		}
		String token = getToken();
		redisService.setObject(token, name, expireTime);
		redisService.setObject(name, token, expireTime);
		return ResponseWrapper.ok("注册成功",token);

	}
	@PostMapping("user/login")
	public ResponseWrapper login(@RequestBody @Valid LoginRequest request){
		/**
		 * 校验用户名和密码格式是否一致
		 */
		String name = request.getName();
		String password = request.getPassword();
		boolean checkNameFormat = checkNameFormat(name);
		if (!checkNameFormat) {
			return ResponseWrapper.fail("用户名格式错误");
		}
		boolean checkPwdFormat = checkPwdFormat(password);
		if (!checkPwdFormat) {
			return ResponseWrapper.fail("密码格式错误");
		}
		/**
		 * 为了逻辑上简单 测试方便注册登录都没做md5加盐等加密
		 *
		 */
		boolean checkNameAndPwd = userService.checkNameAndPwd(request);
		if (!checkNameAndPwd) {
			return ResponseWrapper.fail("用户名或密码错误");
		}
		String newToken = getToken();
		String oldToken = redisService.getString(name);
		if (!StringUtils.isEmpty(oldToken)) {
			redisService.delKey(oldToken);
		}
		redisService.setObject(newToken, name, expireTime);
		redisService.setObject(name, newToken, expireTime);
		return ResponseWrapper.ok("登录成功",newToken);

	}
	@PostMapping("user/updatePassword")
	public ResponseWrapper updatePassword(@RequestBody PasswordModel passwordModel,@RequestHeader("Authorization")String token){
		String name = redisService.getString(token);
		passwordModel.setName(name);
		boolean b = userService.updatePassword(passwordModel);
		if (b) {
			return ResponseWrapper.ok("修改密码成功");
		}
		return ResponseWrapper.fail("旧密码不对,修改密码失败");
	}
	@GetMapping("user/queryParameter")
	public ResponseWrapper queryParameter(@RequestHeader("Authorization")String token){
		String name = redisService.getString(token);
		UserDto userDto = userService.queryByName(name);
		ParamModel paramModel = paramMapper.queryParam();
		paramModel.setName(name);
		paramModel.setRole(userDto.getRole());
		return ResponseWrapper.ok(paramModel);
	}
	public String getToken(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	public boolean checkNameFormat(String name){
		String reg = "^[a-zA-Z]{5,9}$";
		if (name.matches(reg)) {
			return true;
		}
		return false;

	}
	public boolean checkPwdFormat(String pwd){
		String reg = "^[\\w]{6,20}$";
		if (pwd.matches(reg)) {
			return true;
		}
		return false;

	}
}
