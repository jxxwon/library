package com.care.library.search;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
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
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class SearchService {
	
	public void connAPI(String url) {
		String xmlResponse = "";
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
		
	}
	
	public void xmlToJson(String xmlResponse) {
		
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;

			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(xmlResponse)));

			doc.getDocumentElement().normalize();

			NodeList docList = doc.getElementsByTagName("doc");

			List<BookDTO> books = new ArrayList<>(); // Book 객체들을 저장할 리스트 생성

			for (int i = 0; i < docList.getLength(); i++) {
				Node docNode = docList.item(i);
				if (docNode.getNodeType() == Node.ELEMENT_NODE) {
					Element docElement = (Element) docNode;
					String bookname = docElement.getElementsByTagName("bookname").item(0).getTextContent();
					String authors = docElement.getElementsByTagName("authors").item(0).getTextContent();
					String publisher = docElement.getElementsByTagName("publisher").item(0).getTextContent();
					String bookImageURL = docElement.getElementsByTagName("bookImageURL").item(0).getTextContent();
					System.out.println(bookname);

					BookDTO book = new BookDTO();
					book.setBookname(bookname);
					book.setAuthors(authors);
					book.setPublisher(publisher);
					book.setBookImageURL(bookImageURL);
					// Book book = new Book(bookname, authors, publisher, bookImageURL); // Book 객체
					// 생성
					books.add(book); // 리스트에 추가
				}
			}

			// Book 객체 리스트를 JSON 문자열로 변환하며 예쁜 형식으로 저장
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			String jsonString = objectMapper.writeValueAsString(books);

			File jsonFile = new File("book3.json");
			try (FileWriter writer = new FileWriter(jsonFile)) {
				writer.write(jsonString);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}




