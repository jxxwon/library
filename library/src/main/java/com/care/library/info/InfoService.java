package com.care.library.info;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.tomcat.util.file.ConfigurationSource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.care.library.common.PageService;
import com.care.library.member.MemberDTO;

@Service
public class InfoService {
	
	@Autowired InfoMapper mapper;
	
	@Value("${upload.path}")
	private String uploadPath;

	@Value("${img.location}")
	private String uploadImagePath;

	public void noticeWrite(String id, String title, String content, MultipartFile file, MultipartFile image) {
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
		
		String imageName = image.getOriginalFilename();
		
		if(image.getSize() != 0) {
			
			//업로드 파일 저장 경로
			String imageLocation = uploadImagePath;
			File saveImage = new File(imageLocation, imageName);
			
			//폴더 존재 여부 확인
			if(!saveImage.getParentFile().exists()) {
				saveImage.getParentFile().mkdir();
			}
			
			//서버가 저장한 업로드 파일은 임시저장경로에 있는데 개발자가 원하는 경로로 이동
			try {
				file.transferTo(saveImage);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			imageName = "첨부파일 없음";
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
		notice.setImageName(imageName);
		
		mapper.writeNotice(notice);
	}

	public void selectNotice(String cp, Model model) {
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 5; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
		
		ArrayList<NoticeDTO> notices = mapper.selectAllNotice(begin, end);
		
		String url = "notice?currentPage=";
		int totalCount = mapper.count();
		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);
		
		model.addAttribute("notices", notices);
		model.addAttribute("result", result);
		model.addAttribute("currentPage", currentPage);
	}

	public void noticeContent(int no, Model model) {
		NoticeDTO notice = mapper.selectNoticeContent(no);
		mapper.updateNoticeHit(no);
		model.addAttribute("notice", notice);
		
	}

	public void noticeFileDownload(int no) {
		NoticeDTO notice = mapper.selectNoticeContent(no);
		String fileName = notice.getFileName();
			
		String saveDir = "C:\\Users\\hi\\Downloads";
			
		File f = new File(saveDir);
		
//		Resource resource = new InputStremResource(Files.newInputStream(path));
//		FileInputStream fis = new FileInputStream(f);
//		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//				
//		out.clear();
//		out = pageContext.pushBody();
//
//		OutputStream os = response.getOutputStream();
//			
//		byte [] b = new byte[1024];
//		while(true){
//			int currentSize = fis.read(b, 0, b.length);
//			if(currentSize == -1){
//				break;
//			}
//			os.write(b, 0, currentSize);
//		}
//			
//		fis.close();
	}

	public void noticeUpdate(int no, Model model) {
		NoticeDTO notice = mapper.selectNoticeContent(no);
		model.addAttribute("notice", notice);
	}

	public void noticeDelete(int no) {
		mapper.deleteNotice(no);
	}

	public void faqWrite(String category, String title, String content) {
		FaqDTO faq = new FaqDTO();
		int no;
		
		try {
			no = mapper.findMaxNumFaq();
		} catch (Exception e) {
			no = 0;
		}
		
		faq.setNo(no+1);
		faq.setCategory(category);
		faq.setTitle(title);
		faq.setContent(content);
		mapper.writeFaq(faq);
	}


}
