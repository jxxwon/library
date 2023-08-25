package com.care.library.search;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
	
	@Async
    public CompletableFuture<String> asyncMethod() {
		String url = reqUrlParam( "extends/libSrch",  "&libCode=111042" , 1, 50);
		System.out.println("asyncMethod : "+ url);
		String apiResult = connAPI(url);
		if(apiResult != null) {
			 ArrayList<BookDTO> searchResult = totalXmlParse(apiResult);
		}
        return CompletableFuture.completedFuture("Async result");
    }
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
	
	public String connAPI(String url) {
		System.out.println("API요청");
		String xmlResponse = "";

		System.out.println(url);
		try {

			// 인기 대출 도서 조회하기 (loanItemSrch)

			URL requestUrl = new URL(url);

			HttpURLConnection conn = (HttpURLConnection) requestUrl.openConnection();
			conn.setRequestMethod("GET"); // GET 요청 설정
			conn.setRequestProperty("Accept", "application/xml"); // Accept 헤더 설정 (응답 형식 지정)
			
			int responseCode = conn.getResponseCode(); // 요청 보내고 응답 코드 받기
			System.out.println("responseCode : " +responseCode);
			if (responseCode == HttpURLConnection.HTTP_OK) { // 성공적인 응답
				BufferedReader in = new BufferedReader(
						new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
				String inputLine;
				StringBuilder response = new StringBuilder();
				System.out.println("API response : "+ response);
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				// System.out.println("response : "+response);
				in.close();

				// xml을 문자열로 바꿈
				xmlResponse = response.toString();
				System.out.println("xmlResponse : "+xmlResponse);
			} else {
				System.out.println("GET request failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("xmlResponse : "+xmlResponse);
		//totalXmlParse(xmlResponse);
		return xmlResponse;
	}

	// @Scheduled(cron = "0 0 0 1 * 0", zone = "Asia/Seoul") // 매월 1일 요일 00:00:00에
	public String connAPI(String url, String tableName, String XmlTagName) {
		System.out.println("API요청");
		String xmlResponse = "";

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

		xmltoDTO(xmlResponse, XmlTagName ,tableName);
		return "API 호출 성공";
	}
	
	public  ArrayList<BookDTO> totalXmlParse(String xmlResponse) {
		ArrayList<BookDTO> books = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlResponse)));

			doc.getDocumentElement().normalize();

			NodeList docList = doc.getElementsByTagName("doc");

			books = new ArrayList<BookDTO>(); // Book 객체들을 저장할 리스트 생성

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
					String vol = docElement.getElementsByTagName("vol").item(0)
							.getTextContent();
					System.out.println(bookName);
					BookDTO book = new BookDTO();
					book.setPublicationYear(publicationYear);
					book.setBookName(bookName);
					book.setAuthors(authors);
					book.setPublisher(publisher);
					book.setBookImageURL(bookImageURL);
					book.setVol(vol);
					// 생성
					books.add(book); // 리스트에 추가
				}
			}

		} catch (ParserConfigurationException pce) {
		    pce.printStackTrace(); // Handle ParserConfigurationException
		} catch (SAXException se) {
		    se.printStackTrace(); // Handle SAXException
		} catch (IOException ioe) {
		    ioe.printStackTrace(); // Handle IOException
		} catch (Exception e) {
		    e.printStackTrace(); // Handle other exceptions
		}
		return books;
	}
	
	public void xmltoDTO(String xmlResponse, String tagName ,String tableName) {
	    List<BookDTO> books = null;
	    try {
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder;

	        dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(new InputSource(new StringReader(xmlResponse)));

	        doc.getDocumentElement().normalize();
	        
	        Node loanBooksNode = doc.getElementsByTagName(tagName).item(0);
			NodeList docList = loanBooksNode.getChildNodes();
	        books = new ArrayList<>(); // Book 객체들을 저장할 리스트 생성

	        for (int i = 0; i < docList.getLength(); i++) {
	            Node docNode = docList.item(i);
	            if (docNode.getNodeType() == Node.ELEMENT_NODE) {
	                Element docElement = (Element) docNode;
	                BookDTO book = new BookDTO();
	                NodeList noNodes = docElement.getElementsByTagName("no");
	                if (noNodes != null && noNodes.getLength() > 0) {
	                    String no = noNodes.item(0).getTextContent();
	                    book.setNo(no);
	                }

	                NodeList rankingNodes = docElement.getElementsByTagName("ranking");
	                if (rankingNodes != null && rankingNodes.getLength() > 0) {
	                    String ranking = rankingNodes.item(0).getTextContent();
	                    book.setRanking(ranking);
	                }
	                String publicationYear = docElement.getElementsByTagName("publication_year").item(0).getTextContent();
	                String bookName = docElement.getElementsByTagName("bookname").item(0).getTextContent();
	                String authors = docElement.getElementsByTagName("authors").item(0).getTextContent();
	                String publisher = docElement.getElementsByTagName("publisher").item(0).getTextContent();
	                String bookImageURL = docElement.getElementsByTagName("bookImageURL").item(0).getTextContent();
	                String vol = docElement.getElementsByTagName("vol").item(0).getTextContent();
	                
	                book.setPublicationYear(publicationYear);
	                book.setBookName(bookName);
	                book.setAuthors(authors);
	                book.setPublisher(publisher);
	                book.setBookImageURL(bookImageURL);
	                book.setVol(vol);
	                
	                // 생성
	                books.add(book); // 리스트에 추가
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    insertBooks(books, tableName);
	}
	
	
	public String insertBooks(List<BookDTO> books, String tableName) {
	    String resultMessage = "모든 데이터가 입력되었습니다.";
	    
	    try {
	        mapper.deleteData(tableName);

	        for (BookDTO book : books) {
	            int result = 0;
	            
	            if ("popularBook".equals(tableName)) {
	                result = mapper.popularInsert(book);
	            } else if ("recentBook".equals(tableName)) {
	                result = mapper.recentInsert(book);
	            }
	            
	            if (result == 0) {
	                resultMessage = "데이터 입력 중 오류가 발생했습니다. 다시 시도하세요.";
	                break;  // Stop processing if an error occurs
	            }
	        }
	    } catch (Exception e) {
	        resultMessage = "데이터 입력 중 오류가 발생했습니다. 다시 시도하세요.";
	    }
	    
	    return resultMessage;
	}
	
	public String getBookImages(Model model, String whichTable) {
		String modelName = whichTable;
		ArrayList<String> bookImages = mapper.getBookImages(whichTable);
		if (bookImages.isEmpty() || bookImages == null) { // null로 구분하면 안됨. 빈 거(isEmpty())랑 null은 다른것임.
			return "이미지 가져오기 실패";
		}
		model.addAttribute(modelName, bookImages);
		return "이미지 가져오기 완료";
	}

	public void showMainImages(String whichTable, Model model, String url, String xmlTagName) {
		String dbResult = getBookImages(model, whichTable);

		if (dbResult.equals("이미지 가져오기 실패")) {
			// String whichDataAPI, String restParam, String pageNo, String pageSize
			String apiResult = connAPI(url, whichTable, xmlTagName);
			if (apiResult.equals("API 호출 성공")) {
				getBookImages(model, whichTable);
			}
			// System.out.println("popApiResult" + apiResult);
		}
	}

}
