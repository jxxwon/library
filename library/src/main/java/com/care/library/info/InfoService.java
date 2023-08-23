package com.care.library.info;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class InfoService {
	
	@Autowired InfoMapper mapper;
	
	@Value("${upload.path}")
	private String uploadPath;

	public void noticeWrite(String id, String title, String content, MultipartFile file) {
		String fileName = file.getOriginalFilename();
		
		if(file.getSize() != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar cal = Calendar.getInstance();
			fileName = sdf.format(cal.getTime()) + fileName;
			
			//업로드 파일 저장 경로
			String fileLocation = uploadPath;
			File save = new File(fileLocation, fileName);
			
			//폴더 존재 여부 확인
			if(!save.getParentFile().exists()) {
				save.getParentFile().mkdir();
			}
			
			//서버가 저장한 업로드 파일은 임시저장경로에 있는데 개발자가 원하는 경로로 이동
			try {
				file.transferTo(save);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			fileName = "첨부파일 없음";
		}
		
		NoticeDTO notice = new NoticeDTO();
		
		int no;
		try {
			no = mapper.findMaxNum();
		} catch (Exception e) {
			no = 0;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String writeDate = sdf.format(new Date());
		
		notice.setNo(no+1);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setFileName(fileName);
		notice.setWriter(id);
		notice.setWriteDate(writeDate);
		notice.setHits(0);
		
		mapper.writeNotice(notice);
	}

}
