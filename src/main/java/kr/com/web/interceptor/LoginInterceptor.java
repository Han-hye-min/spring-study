package kr.com.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	
		boolean isTrue = true;
		
		//로그인 여부 -> 세션 정보 -> 로그인 사용자 정보 유무
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userInfo") == null) {
			//로그인이 아닌 상태
			isTrue = false;
			response.sendRedirect("/login.do");
		}
		
		return isTrue;
	}
	
}
