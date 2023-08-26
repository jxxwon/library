package com.care.library.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SearchMapper {

	int popularInsert(BookDTO book);
	
	int recentInsert(BookDTO book);

	int insertTotal(BookDTO book);
	
	void deleteData(String whichTable);
	
	ArrayList<String> getBookImages(String whichTable);
	
	int checkTotalDB();
	
	int checkSearchCount(String search);
	
	ArrayList<BookDTO> getTable(String whichTable);
	
//	통합검색 결과
	ArrayList<BookDTO> totalSearch(String search, int begin, int end);
	
	ArrayList<BookDTO> getTotal(int begin, int end);
	
	int insertLoan(BookLoanDTO loanData);

}