package net.cowfish.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @classDesc： 功能描述：（）
 * @author：王武
 * @createTime 2018/2/5
 * @verson: v1.0
 * @copyright: 上海江豚教育科技有限公司
 * @qq:834667820
 */
//标识该接口全部返回json格式。
@RestController
public class HelloWorldController {
    @RequestMapping("/index")
    public String index() {
        int i = 1/0;
        return "success";
    }

    @RequestMapping("/getMap")
    public Map<String, Object> getMap() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errorCode", "200");
        result.put("errorMsg", "成功..");
        return result;
    }


}
