/**
 * 
 */
package net.cowfish.dao;

import net.cowfish.entity.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Company 北斗大学堂 qq群：903672759 
 * @author 令狐冲老师 qq号 1058737002
 * @Description: 
 * @date: 2019年12月8日下午6:03:11
 */
@Mapper
public interface UserMapper {
	@Insert("insert into user(name,password,createDt,updateDt) values(#{name},#{password},#{createDt},#{updateDt})")
	int insertUser(UserDto userPt);

	/**
	 * @Description
	 * @param userPt
	 */
	@Select("select count(1) from user where name=#{name} and password=#{password}")
	int selectByNameAndPwd(UserDto userPt);

	/**
	 * @Description
	 * @param userPt
	 * @return
	 */
	@Select("select count(1) from user where name=#{name}")
	int checkName(UserDto userPt);
	@Select("select name,role from user where name=#{name}")
	UserDto queryByName(@Param("name") String name);

}
