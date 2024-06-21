package net.cowfish.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月8日下午6:22:50
 */
@Getter
@Setter
public class UserDto {
	private long id;
	private String name;
	private String password;
	private Date createDt;
	private Date updateDt;

}