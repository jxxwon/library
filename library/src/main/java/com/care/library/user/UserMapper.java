package com.care.library.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	UserDTO getMyInfo(String id);
	
	int changeMyInfoProc(UserDTO myInfo);
	
	String currentPwCheck(String id);
	
	int updatePw(String newPW, String id);
	
	int updateAuth(String id);
	
	int updateId(String id, String delId);
}