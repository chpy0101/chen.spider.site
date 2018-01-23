package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@RequestMapping("/page")
public class HtmlController {
	@RequestMapping("/test")
	public String index() {
		return "test";
	}

	@RequestMapping("/index")
	public String start() {
		return "index";
	}
}
