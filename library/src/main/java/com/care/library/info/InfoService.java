package com.care.library.info;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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

import jakarta.servlet.http.HttpServletResponse;

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
		notice.setImageName("");
		
		mapper.writeNotice(notice);
	}

	public void selectAllNotice(String cp, Model model) {
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

	public void selectNotice(String cp, String select, String search, Model model) {
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 5; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
		
		ArrayList<NoticeDTO> notices = mapper.selectNotice(select, search, begin, end);
		
		String url = "notice?select="+select+"&search="+search+"&currentPage=";
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
	
	public void fileDownload(int no, HttpServletResponse response) {
		NoticeDTO notice = mapper.selectNoticeContent(no);
		String fileName = notice.getFileName();
		String filePath = "C:\\javas\\upload\\" + fileName;
		
		try (FileInputStream fis = new FileInputStream(filePath);
				OutputStream os = response.getOutputStream()) {
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
			
			byte[] buffer = new byte[1024];
			int bytesRead;
			
			while ((bytesRead = fis.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public void selectFaq(String category, Model model) {
		ArrayList<FaqDTO> faqs = mapper.selectFaq(category);
		model.addAttribute("faqs", faqs);
	}

	public void faqContent(int no, Model model) {
		FaqDTO faq = mapper.selectFaqContent(no);
		model.addAttribute("faq", faq);
	}

	public void faqUpdate(int no, String category, String title, String content) {
		FaqDTO faq = mapper.selectFaqContent(no);
		faq.setCategory(category);
		faq.setTitle(title);
		faq.setContent(content);
		mapper.updateFaq(faq);
	}

	public void faqDelete(int no) {
		FaqDTO faq = mapper.selectFaqContent(no);
		mapper.deleteFaq(no);
	}

}
