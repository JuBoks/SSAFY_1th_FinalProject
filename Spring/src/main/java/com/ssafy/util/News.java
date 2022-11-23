package com.ssafy.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ssafy.news.dto.NewsDto;

public class News {
	public static void main(String[] args) throws IOException {
		
			int page = 5;
			List<NewsDto> list = new ArrayList<NewsDto>();
			
			for(int j=1; j <= 1; j++) {
				NewsDto newsDto= new NewsDto();
				String url = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&date=20221123&page=" + j;
				Document doc = Jsoup.connect(url).get();
				Elements elements = doc.getElementsByAttributeValue("class", "list_body newsflash_body");
				
//				System.out.println(elements);
//				
				Element element = elements.get(0);
//				System.out.println(element);
				Elements photoElements = doc.getElementsByAttributeValue("class", "photo");
//				
				for(int i=0; i<photoElements.size(); i++) {
					Element articleElement = photoElements.get(i);
					Elements aElements = articleElement.select("a");
					Element aElement = aElements.get(0);
//					
					String articleUrl = aElement.attr("href");		// 기사링크
//					System.out.println(articleUrl);
					newsDto.setUrl(articleUrl);
					
					Element imgElement = aElement.select("img").get(0);
					String imgUrl = imgElement.attr("src");			// 사진링크
					System.out.println(imgUrl);
					newsDto.setImg(imgUrl);
					
					
					String title = imgElement.attr("alt");			// 기사제목
					System.out.println(title);
					newsDto.setSubject(title);
					
					String content = doc.getElementsByAttributeValue("class", "lede").get(i).text();
					newsDto.setContents(contents);
//					System.out.println(title);
					System.out.println(content);
//					System.out.println();
				}
				// url
				// img
				// 제목
				// 기사내용 
				System.out.println(j + "page 크롤링 종료");
				
//				List<newsDto>~~
				
//				map.put("info", list);
			
				}

	}
}