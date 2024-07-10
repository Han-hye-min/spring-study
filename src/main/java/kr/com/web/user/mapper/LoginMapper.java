package kr.com.web.user.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.com.web.user.vo.UserVO;

@Mapper
public interface LoginMapper {

	UserVO getLoginUser(Map<String, Object> param) throws Exception;
}
