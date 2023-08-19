package com.care.library.search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Controller
public class SearchController {

	@RequestMapping("/datasearch")
	public String datasearch() {
		return "search/searchMain";
	}

	@PostMapping("datasearchProc")
	public String datasearchProc() {

		String key = "";
		String xmlResponse="";
		try {
			// parsing할 url 지정(API 키 포함해서)
			String url = "http://data4library.kr/api/loanItemSrch";
			
			String urlParam ="?authKey=8d6b32bd9b40ff27779c0cd9cd76329dd858b557eff8d78747d43e3845117641";
			urlParam += "&startDt=2022-01-01&endDt=2022-03-31";
			urlParam += "&gender=1&age=20";
			urlParam += "&region=11;31";
			urlParam += "&addCode=0";
			urlParam += "&kdc=6";
			urlParam += "&pageNo=1&pageSize=10";
			
			url = url + urlParam;
			RestTemplate restTemplate = new RestTemplate();
	        
			 // XML을 String으로 가져오기
			 xmlResponse = restTemplate.getForObject(url, String.class);
			 //ResponseEntity<String> xmlFile = ResponseEntity.ok(xmlResponse);
			 
			 //System.out.println(xmlFile);
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(new InputSource(new StringReader(xmlResponse)));
				
				doc.getDocumentElement().normalize();
				
				NodeList docList = doc.getElementsByTagName("doc");
				 for (int i = 0; i < docList.getLength(); i++) {
		                Node docNode = docList.item(i);
		                if (docNode.getNodeType() == Node.ELEMENT_NODE) {
		                    Element docElement = (Element) docNode;
		                    String bookname = docElement.getElementsByTagName("bookname").item(0).getTextContent();
		                    String authors = docElement.getElementsByTagName("authors").item(0).getTextContent();
		                    String publisher = docElement.getElementsByTagName("publisher").item(0).getTextContent();
		                    // ... similarly extract other information you need
		                    
		                    System.out.println("Book Name: " + bookname);
		                    System.out.println("Authors: " + authors);
		                    System.out.println("Publisher: " + publisher);
		                    System.out.println("--------------------------------");
		                }
		            }
			} 
				catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		return "search/searchMain";
	}
	
	public static String getTagValue(String tag, Element eElement) {

    	//결과를 저장할 result 변수 선언
    	String result = "";

	NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

	result = nlList.item(0).getTextContent();

	return result;
}


}
