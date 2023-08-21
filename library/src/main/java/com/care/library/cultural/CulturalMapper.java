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

	/* MemberDTO culFormWriteProc(String culId); */
	
	void culFormWriteProc(CulturalDTO cultural);
	
	/*
	 * void culFormWrite(String imagePath, int culId, String title, String
	 * lectureStart, String lectureEnd, String registrationStart, String
	 * registrationEnd, String target, String writeDate);
	 */

	void culFormWrite(CulturalDTO cultural);

}
