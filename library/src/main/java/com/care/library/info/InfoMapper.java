package com.care.library.info;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InfoMapper {

	int findMaxNum();

	void writeNotice(NoticeDTO notice);

	ArrayList<NoticeDTO> selectAllNotice(int begin, int end);

	int count();

	NoticeDTO selectNoticeContent(int no);

	void updateNoticeHit(int no);

	void deleteNotice(int no);
	
	int findMaxNumFaq();

	void writeFaq(FaqDTO faq);

	ArrayList<FaqDTO> selectFaq(String category);

}
