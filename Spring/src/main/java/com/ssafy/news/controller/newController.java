package com.ssafy.news.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.news.dto.NewsDto;

@RestController
@RequestMapping("/news")
@CrossOrigin("*")
public class newController {
	public static void main(String[] args) {
		
	}
	@GetMapping
	public ResponseEntity<?> listArticle(@RequestParam Map<String, Object> map) {
		System.out.println("여기도 들어옴");
		try {
			int page = 5;
			List<NewsDto> list = new ArrayList<NewsDto>();
			
			for(int j=1; j < page; j++) {
				String url = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&date=20221123&page=" + j;
				Document doc = Jsoup.connect(url).get();
				Elements elements = doc.getElementsByAttributeValue("class", "list_body newsflash_body");
				
//				System.out.println(elements);
//				
//				Element element = elements.get(0);
//				Elements photoElements = doc.getElementsByAttributeValue("class", "photo");
//				
//				for(int i=0; i<photoElements.size(); i++) {
//					Element articleElement = photoElements.get(i);
//					Elements aElements = articleElement.select("a");
//					Element aElement = aElements.get(0);
//					
//					String articleUrl = aElement.attr("href");		// 기사링크
//					
//					Element imgElement = aElement.select("img").get(0);
//					String imgUrl = imgElement.attr("src");			// 사진링크
//					String title = imgElement.attr("alt");			// 기사제목
//					
//					Document subDoc = Jsoup.connect(articleUrl).get();
//					Element contentElement = subDoc.getElementById("articleBodyContents");
//					String content = contentElement.text();			// 기사내용
//				
//					System.out.println(title);
//					System.out.println(content);
//					System.out.println();
//				}
				// url
				// img
				// 제목
				// 기사내용 
				System.out.println(j + "page 크롤링 종료");
				
//				List<newsDto>~~
				
//				map.put("info", list);
			}

			return new ResponseEntity<List<NewsDto>>(list, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
