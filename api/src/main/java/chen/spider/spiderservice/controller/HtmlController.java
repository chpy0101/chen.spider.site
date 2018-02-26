package chen.spider.spiderservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/page")
public class HtmlController {
	@RequestMapping("/test")
	@ResponseBody
	public String index() {
		System.out.println("114");
		return "test";
	}

	@RequestMapping("/index")
	public String start() {
		return "index";
	}
}
