package com.care.library.search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class SearchController {
	
	@RequestMapping("/datasearch")
	public String datasearch() {
		return "search/searchMain";
	}
	
   @PostMapping("datasearchProc")
   public String datasearchProc(){
		
		  System.out.println("여기요"); 
		  HttpURLConnection conn = null; 
		  StringBuilder sb = new StringBuilder(); 
		  sb.append("http://data4library.kr/api/loanItemSrch?authKey=8d6b32bd9b40ff27779c0cd9cd76329dd858b557eff8d78747d43e3845117641&startDt=2022-01-01&endDt=2022-03-31&gender=1&age=20&region=11;31&addCode=0&kdc=6&pageNo=1&pageSize=10"); 
//		  sb.append("?libCode=111042");
//		  sb.append("&startDt="+"2017-06-01");
//		  sb.append("&endDt="+"2017-06-30");
//		  sb.append("&authKey=");
//		  sb.append("8d6b32bd9b40ff27779c0cd9cd76329dd858b557eff8d78747d43e3845117641"); 
//		  sb.append("&startDt=" + "2022-01-01"); 
//		  sb.append("&endDt="+"2022-03-31");
		  System.out.println("sb :" + sb);
		  try { 
			  URL url = new URL(sb.toString()); 
			  conn = (HttpURLConnection)url.openConnection();
			  conn.setRequestProperty("Content-Type","application/xml");
			  conn.setRequestMethod("GET"); 
			  conn.connect();
			  SAXBuilder builder = new SAXBuilder();
			  Document document;
				document = builder.build(conn.getInputStream());
				Element root = document.getRootElement();
				
				Element docs = root.getChild("docs");
				System.out.println("docs = " + docs);
				Element doc = docs.getChild("doc");
				System.out.println("doc = " + doc);
			 //List<Element> item = items.getChildren("item");
	
	
//			  for (Element element : item) {
//			      ApartXmlParser apartXmlParser = transferXmlToParser(element);
//			      System.out.println("apartXmlParser = " + apartXmlParser);
//			  }
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		  return "search/searchMain";
   }

	
}
