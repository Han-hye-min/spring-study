package kr.com.web.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.com.web.user.service.LoginService;
import kr.com.web.user.vo.UserVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	
	@GetMapping("/login.do")
	public ModelAndView loginView() {
		ModelAndView view = new ModelAndView();
		view.setViewName("login/loginView");
		
		return view;
	}
	
	@GetMapping("/login/proc.do")
	@ResponseBody // 응답객체의 body를 리턴 = 데이터를 전송 
	public Map<String, Object> loginPoc(@RequestParam("memId") String memId,
			         @RequestParam("memPasswd") String memPasswd,
			         HttpServletRequest request) {
		 //리턴용 맵
		 Map<String, Object> resultMap = new HashMap<>();
		 resultMap.put("resultCode", 200);
		 
		 try {
		
			 UserVO user =loginService.loginUser(memId, memPasswd);
			 //세션에 로그인 정보를 저장
			 HttpSession session = request.getSession();
			 session.setAttribute("userInfo", user);

		 }catch (Exception e) {
			 resultMap.put("resultCode", 500);
			e.printStackTrace();
		}
		 return resultMap;
	}
	
	@GetMapping("/logout.do")
	public ModelAndView loginOut(HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/login.do");
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userInfo") != null) {
			session.removeAttribute("userInfo");
		}
		return view;
	}
}
