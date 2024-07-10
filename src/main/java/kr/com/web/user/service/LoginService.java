package kr.com.web.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.com.web.user.mapper.LoginMapper;
import kr.com.web.user.vo.UserVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	//생성자를 통한 의존성 주입
	private final LoginMapper loginMapper;
	
	public UserVO loginUser(String memId, String memPasswd) throws Exception{
		 Map<String, Object> param = new HashMap<>();
		 param.put("memId", memId);
		 param.put("memPasswd", memPasswd);
		
		 UserVO user =loginMapper.getLoginUser(param);
		 //유저 정보가 없다면 로그인 실패!
		 if(user == null) {
			 throw new Exception("로그인 실패");
		 }
		 
		return user;
	}
}
