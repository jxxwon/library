package com.care.library.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

	MemberDTO loginProc(String id);

}