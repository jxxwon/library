package com.care.library.user;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

	UserDTO getMyInfo(String id);
	
	int changeMyInfoProc(UserDTO myInfo);

	int findMaxNum();

	int myInquiryWrite(InquiryDTO inquiry);

	int count();

	ArrayList<InquiryDTO> selectInqiry(@Param("id")String id, @Param("begin")int begin, @Param("end")int end);

	ArrayList<InquiryDTO> selectInqiryTitle(String id, String search, int begin, int end);
}