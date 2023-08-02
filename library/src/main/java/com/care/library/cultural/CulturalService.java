package com.care.library.cultural;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CulturalService {
	/*
	 * @Autowired private CulturalMapper culturalMapper;
	 * 
	 * public void saveCulturalWithImage(String title, String content, MultipartFile
	 * imageFile) throws IOException { CulturalDTO culturalDTO = new CulturalDTO();
	 * culturalDTO.setTitle(title); culturalDTO.setContent(content);
	 * 
	 * // 이미지 파일의 바이너리 데이터 추출 byte[] imageBytes = imageFile.getBytes();
	 * culturalDTO.setImage(imageBytes);
	 * 
	 * // CulturalDTO를 DB에 저장 culturalMapper.insertCultural(culturalDTO); }
	 */
}
