package com.care.library.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SearchMapper {

	int jsonInsert(BookDTO book);
	
	int recentInsert(BookDTO book);

	void jsonDelete();
	
	void recentDelete();
	
	ArrayList<String> getBookImages(String whichTable);

}