package kr.com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping(value="/hello.do")
	public String hello(Model model) {
		model.addAttribute("msg", "안녕하세요");
		return "hello";
		
	}
}
