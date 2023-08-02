package com.care.library.admin;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.care.library.member.MemberDTO;

@Mapper
public interface AdminMapper {

	ArrayList<MemberDTO> selectMember();

}
