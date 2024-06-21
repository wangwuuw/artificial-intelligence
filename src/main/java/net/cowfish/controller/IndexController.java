
package net.cowfish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

	@RequestMapping("/indexController")
	public String indexController(Map<String, Object> result) {
		System.out.println("IndexController....index");
		result.put("name", "yushengjun");
		result.put("sex", 1);
		List<String> list = new ArrayList<String>();
		list.add("zhangsan");
		list.add("lisi");
		result.put("userlist", list);
		return "index";
	}

}
