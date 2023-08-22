package com.care.library.reservation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.library.common.NotifyDTO;
import com.care.library.common.NotifyService;

import jakarta.servlet.http.HttpSession;

@Service
public class ReserveService {

	@Autowired
	ReserveMapper userMapper;
	@Autowired
	HttpSession session;
	@Autowired NotifyService notiService;

	public String userCheck(String id) {
		int usingUser = userMapper.usingUser(id);
		if (usingUser >= 1)
			return "이미 다른 좌석을 예약하셨습니다.";
		return "예약 가능한 회원입니다.";
	}

	public String reservation(ReserveDTO resevedData) {
		int result = userMapper.reservation(resevedData);
		System.out.println(resevedData.getRoom());
		
		if (result == 1) {
			String msg = resevedData.getSeatId()+"번 좌석이 예약되었습니다.";
			NotifyDTO notification = new NotifyDTO();
			//String msg = resevedData.getRoom()
			notification.setId(resevedData.getUserId());
			notification.setCategory("열람실");
			notification.setTitle(msg);
			if(resevedData.getRoom().equals("R1"))
				notification.setUrl("/reservation/readingRoom1");
			if(resevedData.getRoom().equals("R2"))
				notification.setUrl("/reservation/readingRoom2");
			notiService.register(notification);
			return "예약이 완료되었습니다.";
		}
		return "예약이 정상적으로 이루어지지 않았습니다.";
	}

	public ArrayList<ReserveDTO> getReservedSeat(String whichRoom) {
		ArrayList<ReserveDTO> reservedSeat = userMapper.getReservedSeat(whichRoom);
//		for (ReserveDTO seat : reservedSeat) {
//			System.out.println("seat : " + seat.getSeatId());
//			System.out.println("seat : " + seat.getUserId());
//		}
//		System.out.println();
		return reservedSeat;
	}


}
