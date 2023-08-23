package com.care.library.search;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableScheduling
@Service
public class SearchService {

	@Autowired
	SearchMapper mapper;

	// 주기적으로 받아오는 것은 나중에하쟈...
	// 초, 분, 시간, 일, 월, 요
//	@Scheduled(cron = "0/1 * * * * *", zone = "Asia/Seoul")
//	public void test() {
//		System.out.println("스케줄");
//	}
	// 요청 URL 만들
	public String reqUrlParam(String whichDataAPI, String restParam, int pageNo, int pageSize) {
		String url = "http://data4library.kr/api/" + whichDataAPI;

		String urlParam = "?authKey=8d6b32bd9b40ff27779c0cd9cd76329dd858b557eff8d78747d43e3845117641";
		urlParam += restParam;
		urlParam += "&pageNo=" + pageNo;
		urlParam += "&pageSize=" + pageSize;
		url = url + urlParam;

		return url;
	}

	public String reqUrlParam(String whichDataAPI, String restParam) {
		String url = "http://data4library.kr/api/" + whichDataAPI;

		String urlParam = "?authKey=8d6b32bd9b40ff27779c0cd9cd76329dd858b557eff8d78747d43e3845117641";
		urlParam += restParam;
		url = url + urlParam;

		return url;
	}

	// @Scheduled(cron = "0 0 0 1 * 0", zone = "Asia/Seoul") // 매월 1일 요일 00:00:00에
	// 실행
	// connAPI("loanItemSrch", whichTable, url);
	public String connAPI(String url, String tableName) {
		System.out.println("API요청");
//		System.out.println("whichDataUrl : " + whichDataUrl);
		String xmlResponse = "";
//		String key = "";
//
//		
//		// 인기도서(보통은 이달의 인기도서로 하는듯하다.)
//		// 인기도서 loanItemSrch
//		String url = "http://data4library.kr/api/" + whichDataUrl;
//
//		String urlParam = "?authKey=8d6b32bd9b40ff27779c0cd9cd76329dd858b557eff8d78747d43e3845117641";
//		urlParam += "&region=11"; /// 다중 선택 가능 / 서울: 11
//		urlParam += "&dtl_region=11120"; // 은평
//		urlParam += "&pageNo=1&pageSize=5";
//		urlParam += param;
//		url = url + urlParam;
		System.out.println(url);
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
			} else {
				System.out.println("GET request failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// xml파일을 db에 저장하기.
		if (tableName.equals("popularBook")) {
			System.out.println("popularBook API 호출");
			xmlToJson(xmlResponse);
			return "API 호출 성공";
		}
		if (tableName.equals("recentBook")) {
			System.out.println("recentBook API 호출");
			recentXmlToJson(xmlResponse);
			return "API 호출 성공";
		}
		return "API 호출 실패";
	}

	public void recentXmlToJson(String xmlResponse) {
		List<BookDTO> books = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlResponse)));

			doc.getDocumentElement().normalize();

			NodeList docList = doc.getElementsByTagName("book");

			books = new ArrayList<>(); // Book 객체들을 저장할 리스트 생성

			for (int i = 0; i < docList.getLength(); i++) {
				Node docNode = docList.item(i);
				if (docNode.getNodeType() == Node.ELEMENT_NODE) {
					Element docElement = (Element) docNode;
					String bookName = docElement.getElementsByTagName("bookname").item(0).getTextContent();
					String authors = docElement.getElementsByTagName("authors").item(0).getTextContent();
					String publisher = docElement.getElementsByTagName("publisher").item(0).getTextContent();
					String bookImageURL = docElement.getElementsByTagName("bookImageURL").item(0).getTextContent();
					String publicationYear = docElement.getElementsByTagName("publication_year").item(0)
							.getTextContent();
					// System.out.println(bookName);
					System.out.println("recentXmlToJson : " + bookName);
					BookDTO book = new BookDTO();
					book.setPublication_year(publicationYear);
					book.setBookName(bookName);
					book.setAuthors(authors);
					book.setPublisher(publisher);
					book.setBookImageURL(bookImageURL);
					// 생성
					books.add(book); // 리스트에 추가
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recentInsert(books);
	}

	// xml파일을 db에 저장하기.
	public void xmlToJson(String xmlResponse) {

		List<BookDTO> books = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlResponse)));

			doc.getDocumentElement().normalize();

			Node loanBooksNode = doc.getElementsByTagName("loanBooks").item(0);
			NodeList docList = loanBooksNode.getChildNodes();

			// NodeList docList = doc.getElementsByTagName("book");
			System.out.println("xmlToJson1");
			books = new ArrayList<>(); // Book 객체들을 저장할 리스트 생성

			for (int i = 0; i < docList.getLength(); i++) {
				Node docNode = docList.item(i);
				if (docNode.getNodeType() == Node.ELEMENT_NODE) {
					Element docElement = (Element) docNode;
					String no = docElement.getElementsByTagName("no").item(0).getTextContent();
					String ranking = docElement.getElementsByTagName("ranking").item(0).getTextContent();
					String publicationYear = docElement.getElementsByTagName("publication_year").item(0)
							.getTextContent();
					String bookName = docElement.getElementsByTagName("bookname").item(0).getTextContent();
					String authors = docElement.getElementsByTagName("authors").item(0).getTextContent();
					String publisher = docElement.getElementsByTagName("publisher").item(0).getTextContent();
					String bookImageURL = docElement.getElementsByTagName("bookImageURL").item(0).getTextContent();
					System.out.println("xmlToJson : " + bookName);

					BookDTO book = new BookDTO();
					book.setNo(no);
					book.setRanking(ranking);
					book.setPublication_year(publicationYear);
					book.setBookName(bookName);
					book.setAuthors(authors);
					book.setPublisher(publisher);
					book.setBookImageURL(bookImageURL);
					// 생성
					books.add(book); // 리스트에 추가
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonInsert(books);
	}

	public String jsonInsert(List<BookDTO> books) {

		ObjectMapper jsonMapper = new ObjectMapper();
//					//ObjectMapper는 JSON과 Java 객체 간의 변환을 처리하는 역할.
		mapper.jsonDelete();

		// for(BookDTO book : lists) {
		for (BookDTO book : books) {
			// System.out.println(book.getBookName());
			int result = mapper.jsonInsert(book);
			System.out.println("popular 디비에 들어깠니? : " + result);
			if (result == 0)
				return "데이터 입력 중 오류가 발생했습니다. 다시 시도 하세요.";
		}
		return "모든 데이터가 입력되었습니다.";
	}

	public String recentInsert(List<BookDTO> books) {

		ObjectMapper jsonMapper = new ObjectMapper();
//					//ObjectMapper는 JSON과 Java 객체 간의 변환을 처리하는 역할.
		mapper.recentDelete();

		// for(BookDTO book : lists) {
		for (BookDTO book : books) {
			System.out.println(book.getBookName());
			int result = mapper.recentInsert(book);
			System.out.println("recent 디비에 들어깠니? : " + result);
			if (result == 0)
				return "데이터 입력 중 오류가 발생했습니다. 다시 시도 하세요.";
		}
		return "모든 데이터가 입력되었습니다.";
	}

	public String getBookImages(Model model, String whichTable) {
		System.out.println("whichTable ?" + whichTable);
		String modelName = whichTable;
		ArrayList<String> bookImages = mapper.getBookImages(whichTable);
		if (bookImages.isEmpty() || bookImages == null) { // null로 구분하면 안됨. 빈 거(isEmpty())랑 null은 다른것임.
			System.out.println("실패야??");
			return "이미지 가져오기 실패";
		}
		for (String name : bookImages) {
			System.out.println("책 이름 : " + name);
		}
		model.addAttribute(modelName, bookImages);
		return "이미지 가져오기 완료";
	}

	public void showBookImages(String whichTable, Model model, String url) {
		String dbResult = getBookImages(model, whichTable);
		// String tableName = whichTable;

		if (dbResult.equals("이미지 가져오기 실패")) {
			// String whichDataAPI, String restParam, String pageNo, String pageSize
			System.out.println("여기야?");
			String apiResult = connAPI(url, whichTable);
			if (apiResult.equals("API 호출 성공")) {
				System.out.println("여기 온거니???????");
				getBookImages(model, whichTable);
			}
			// System.out.println("popApiResult" + apiResult);
		}
	}

}
