package com.care.library.cultural;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.care.library.member.MemberDTO;

@Mapper
public interface CulturalMapper {
	void insertCultural(CulturalDTO culturalDTO);

	ArrayList<CulturalDTO> culturalForm(@Param("begin") int begin, @Param("end") int end);

	int count();

	void culFormWriteProc(CulturalDTO cultural);

	CulturalDTO culFormWrite(int culId);	//목록에서 culId값 일치하는 데이터 가저오기

}
