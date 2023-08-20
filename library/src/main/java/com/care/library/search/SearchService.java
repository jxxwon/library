package com.care.library.search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@EnableScheduling
@Service
public class SearchService {
	
	@Autowired SearchMapper mapper;
	private Model popularBook;
	
	//초, 분, 시간, 일, 월, 요
//	@Scheduled(cron = "0/1 * * * * *", zone = "Asia/Seoul")
//	public void test() {
//		System.out.println("스케줄");
//	}
	
	public void setModel(Model popularBook) {
		this.popularBook = popularBook;
	}
	
	@Scheduled(cron = "0 0 0 1 * 0", zone = "Asia/Seoul") // 매월 1일 요일 00:00:00에 실행
	public String connAPI() {
		System.out.println("API요청");
		String xmlResponse = "";
		String key = "";
		
		//인기도서(보통은 이달의 인기도서로 하는듯하다.)
		//인기도서 loanItemSrch
		String url = "http://data4library.kr/api/loanItemSrch";

		String urlParam = "?authKey=8d6b32bd9b40ff27779c0cd9cd76329dd858b557eff8d78747d43e3845117641";
		urlParam += "&startDt=2023-07-10";
		urlParam += "&endDt=2023-07-17";
		//urlParam += "&gender=1&age=20";
		urlParam += "&region=11;31"; /// 다중 선택 가능 / 서울: 11
		urlParam += "&dtl_region=11120"; // 은평
		//urlParam += "&addCode=0";
		//urlParam += "&kdc=6";
		urlParam += "&pageNo=1&pageSize=5";

		url = url + urlParam;
		try {

			// 인기 대출 도서 조회하기 (loanItemSrch)
			
			URL requestUrl = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
			conn.setRequestMethod("GET"); // GET 요청 설정
			conn.setRequestProperty("Accept", "application/xml"); // Accept 헤더 설정 (응답 형식 지정)

			int responseCode = conn.getResponseCode(); // 요청 보내고 응답 코드 받기
			if (responseCode == HttpURLConnection.HTTP_OK) { // 성공적인 응답
				BufferedReader in = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				// System.out.println("response : "+response);
				in.close();

				// xml을 문자열로 바꿈
				xmlResponse = response.toString();
				// System.out.println("Response Body:");
				// System.out.println(xmlResponse);
			} else {
				System.out.println("GET request failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//xml파일을 json형태의 파일로 바꿔서 저장하기.
		xmlToJson(xmlResponse);
		return "API 호출 성공";
		
	}
	
	public void xmlToJson(String xmlResponse) {
		List<BookDTO> books = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlResponse)));

			doc.getDocumentElement().normalize();

			NodeList docList = doc.getElementsByTagName("doc");

			books = new ArrayList<>(); // Book 객체들을 저장할 리스트 생성

			for (int i = 0; i < docList.getLength(); i++) {
				Node docNode = docList.item(i);
				if (docNode.getNodeType() == Node.ELEMENT_NODE) {
					Element docElement = (Element) docNode;
					String no = docElement.getElementsByTagName("no").item(0).getTextContent();
					String ranking = docElement.getElementsByTagName("ranking").item(0).getTextContent();
					String publicationYear = docElement.getElementsByTagName("publication_year").item(0).getTextContent();
					String bookName = docElement.getElementsByTagName("bookname").item(0).getTextContent();
					String authors = docElement.getElementsByTagName("authors").item(0).getTextContent();
					String publisher = docElement.getElementsByTagName("publisher").item(0).getTextContent();
					String bookImageURL = docElement.getElementsByTagName("bookImageURL").item(0).getTextContent();
					//System.out.println(bookName);

					BookDTO book = new BookDTO();
					book.setNo(no);
					book.setRanking(ranking);
					book.setPublication_year(publicationYear);
					book.setBookName(bookName);
					book.setAuthors(authors);
					book.setPublisher(publisher);
					book.setBookImageURL(bookImageURL);
					// Book book = new Book(bookname, authors, publisher, bookImageURL); // Book 객체
					// 생성
					books.add(book); // 리스트에 추가
				}
			}

			// Book 객체 리스트를 JSON 문자열로 변환하며 예쁜 형식으로 저장
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//			String jsonString = objectMapper.writeValueAsString(books);
//
//			String filePath = "src/main/resources/static/json/book1.json";
//			//String filePath = "json/book1.json"; //상대경로일까?..
//			File jsonFile = new File(filePath);
//			
//			//File jsonFile = new File("book1.json");
//			try (FileWriter writer = new FileWriter(jsonFile)) {
//				writer.write(jsonString);
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonInsert(books);
	}
	
	public String jsonInsert(List<BookDTO> books) {
		/*
		 * JSON 파일의 데이터를 읽어와서 book1.json 파일로 저장.
		 */
		
//		ClassPathResource cpr = new ClassPathResource("static/json/book1.json");
		List<BookDTO> lists = null;
		
//			try {
//				File file = cpr.getFile();
//				
//				
//				if(file.exists()) {
					ObjectMapper jsonMapper = new ObjectMapper();
//					//ObjectMapper는 JSON과 Java 객체 간의 변환을 처리하는 역할.
//					
//					lists = jsonMapper.readValue(file, new TypeReference<List<BookDTO>>() {});
					//ObjectMapper의 readValue() 메서드를 사용하여 JSON 파일을 지정된 유형의 Java 객체로 매핑.
					//TypeReference<>()는 변환할 Java 객체의 유형을 지정
					//변환된 객체는 lists 변수에 할당.
					// 테이블의 모든 데이터 삭제, 초기화 
					mapper.jsonDelete();
					
						//for(BookDTO book : lists) {
						for(BookDTO book : books) {
							int result = mapper.jsonInsert(book);
							if(result == 0)
								return "데이터 입력 중 오류가 발생했습니다. 다시 시도 하세요.";
						}
					//getBookImages(popularBook);
//					
//				}
//				else {
//					return "JSON 파일을 찾을 수 없습니다.";
//				}
//				
//			}catch (FileNotFoundException e) {
//		        // 파일이 존재하지 않을 경우에 대한 예외 처리
//		        //e.printStackTrace();
//		        return "파일이 존재하지 않습니다.";
//		    } 
//			catch (IOException e) {
//				e.printStackTrace();
//				
//			}
			return "모든 데이터가 입력되었습니다.";
	}
	
	public String getBookImages(Model popularBook) {
		System.out.println("뭐지?");
		ArrayList<String> bookImages = mapper.getBookImages();
		if(bookImages.isEmpty() || bookImages == null) { //null로 구분하면 안됨. 빈 거(isEmpty())랑 null은 다른것임.
			//System.out.println("비었쟈나");
			//jsonInsert();
			return "이미지 가져오기 실패";
			//connAPI();
		}
//		
//		  for(String url : bookImages) {
//		 System.out.println("있는거야?");
//		  System.out.println(url); }
		
		popularBook.addAttribute("bookImages", bookImages);
		return "이미지 가져오기 완료";
	} 
	
	
}




