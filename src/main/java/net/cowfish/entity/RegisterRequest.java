/**
 * 
 */
package net.cowfish.entity;

import lombok.Getter;
import lombok.Setter;


/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月8日下午5:26:50
 */
@Getter
@Setter
public class RegisterRequest {
  private String name;
  private String password;
  private String repassword;
}
