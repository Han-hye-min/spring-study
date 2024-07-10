package kr.com.web.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.web.user.mapper.LoginMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
//@AllArgsConstructor //모든 멤버변수 값을 생성자를 통해 받는다.
//@NoArgsConstructor //파라메터 없는 기본 생성자
@RequiredArgsConstructor //필수요소만 생성자를 통해서 받는다.
public class DIService {
	//D.I 하는 방법을 알아보기
	
	//1 직접주입 방식
	@Autowired
	private final LoginMapper loginMapper;
	
//	//2 생성자 방식
//	@Autowired //생략가능
//	public void setLoginMapper(LoginMapper loginMapper) {
//		this.loginMapper = loginMapper;
//	}
//	
//	//3 Setter 방식
//	@Autowired //생략가능
//	public void DIServeice(LoginMapper loginMapper) {
//		this.loginMapper = loginMapper;
//	}
}
