package com.care.library.reservation;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReserveMapper {
	
	int findMaxNum();

	int count();
	
	String currentPwCheck(String id);
	
	int updatePw(String newPW, String id);
	
	int updateAuth(String id);
	
	int updateId(String id, String delId);

}