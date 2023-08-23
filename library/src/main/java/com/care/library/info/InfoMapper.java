package com.care.library.info;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InfoMapper {

	int findMaxNum();

	void writeNotice(NoticeDTO notice);

}
