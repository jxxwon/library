package com.care.library.admin;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.library.member.MemberDTO;

@Mapper
public interface AdminMapper {

	int count();

	ArrayList<MemberDTO> selectMember(String memberSelect, int begin, int end);

	MemberDTO selectUser(String id);

	void memberConfirm(String id, String userGroup, String paper, String authDate);

	void memberReject(String id);


}
