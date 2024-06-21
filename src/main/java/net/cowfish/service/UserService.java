package net.cowfish.service;

import net.cowfish.dao.UserMapper;
import net.cowfish.entity.LoginRequest;
import net.cowfish.entity.RegisterRequest;
import net.cowfish.entity.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Date;

/**
 * @classDesc： 功能描述：（）
 * @author：王武
 * @createTime 2018/2/5
 * @verson: v1.0
 * @copyright: 上海江豚教育科技有限公司
 * @qq:834667820
 */
@Service
public class UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserMapper userMapper;
    public void createUser(String name, Integer age) {
        System.out.println("createUser");
        jdbcTemplate.update("insert into users values(null,?,?);", name, age);
        System.out.println("创建用户成功...");
    }
    public boolean register(@RequestBody @Valid RegisterRequest request) {
        UserDto userPt = new UserDto();
        userPt.setName(request.getName());
        userPt.setPassword(request.getPassword());
        userPt.setCreateDt(new Date());
        userPt.setUpdateDt(new Date());
        int flag = userMapper.insertUser(userPt);
        return flag>0;

    }
    /**
     * @Description
     * @param request
     */
    public boolean checkNameAndPwd(@Valid LoginRequest request) {
        UserDto userPt = new UserDto();
        userPt.setName(request.getName());
        userPt.setPassword(request.getPassword());
        int flag = userMapper.selectByNameAndPwd(userPt);
        return flag>0;
    }

    /**
     * @Description
     * @param
     */
    public boolean checkIsExist(String name) {
        UserDto userPt = new UserDto();
        userPt.setName(name);
        int flag = userMapper.checkName(userPt);
        return flag>0;

    }

}
