package com.care.library.reservation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Service
public class ReserveService {

	@Autowired
	ReserveMapper userMapper;
	@Autowired
	HttpSession session;

	public String userCheck(String id) {
		int usingUser = userMapper.usingUser(id);
		if (usingUser >= 1)
			return "이미 다른 좌석을 예약하셨습니다.";
		return "예약 가능한 회원입니다.";
	}

	public String reservation(ReserveDTO resevedData) {
		int result = userMapper.reservation(resevedData);
		if (result == 1) {
			return "예약이 완료되었습니다.";
		}
		return "예약이 정상적으로 이루어지지 않았습니다.";
	}

	public ArrayList<String> getReservedSeat(String whichRoom) {
		ArrayList<String> reservedSeat = userMapper.getReservedSeat(whichRoom);
//		for (String seat : reservedSeat) {
//			System.out.println("seat : " + seat);
//		}
//		System.out.println();
		return reservedSeat;
	}


}
