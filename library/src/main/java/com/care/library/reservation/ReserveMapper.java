package com.care.library.reservation;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReserveMapper {
	
	int reservation(ReserveDTO resevedData);

}