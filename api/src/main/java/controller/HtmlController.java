package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class HtmlController {
	@RequestMapping("/test")
	public String index() {
		return "test";
	}

	@RequestMapping("/index")
	public String start() {
		System.out.print(1);
		return "index";
	}
}
