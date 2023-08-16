package com.care.library.common;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.library.member.MemberDTO;

@Mapper
public interface NotifyMapper {

	void insert(NotifyDTO notification);

	int findMaxNum();
	
	ArrayList<NotifyDTO> selectAll(String id);

	MemberDTO findAdmin();
	
	void admin(NotifyDTO notification);

}
