package com.ssafy.news.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	@GetMapping
	public ResponseEntity<?> listArticle(@RequestParam Map<String, Object> map) {
		System.out.println("여기도 들어옴");
		try {
			List<NewsDto> list = new ArrayList<NewsDto>();

			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

			String today=(date.format(formatter));
		
			
			String url = "https://news.naver.com/main/list.naver?mode=LS2D&sid2=260&sid1=101&mid=shm&date="+today+"&page=";
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByAttributeValue("class", "list_body newsflash_body");

			Element element = elements.get(0);
			Elements photoElements = doc.getElementsByAttributeValue("class", "photo");

			for (int i = 0; i < 3; i++) {

				NewsDto newsDto = new NewsDto();
				Element articleElement = photoElements.get(i);
				Elements aElements = articleElement.select("a");
				Element aElement = aElements.get(0);
				String articleUrl = aElement.attr("href"); // 기사링크
				newsDto.setUrl(articleUrl);

				Element imgElement = aElement.select("img").get(0);
				String imgUrl = imgElement.attr("src"); // 사진링크
//				System.out.println(imgUrl);
				newsDto.setImg(imgUrl);

				String title = imgElement.attr("alt"); // 기사제목
//				System.out.println(title);
				newsDto.setSubject(title);

				String content = doc.getElementsByAttributeValue("class", "lede").get(i).text();
				newsDto.setContents(content);
//				System.out.println(content);

				System.out.println(newsDto);
				list.add(newsDto);

			}
			System.out.println("page 크롤링 종료");
			System.out.println("list갯수"+list.size());
			return new ResponseEntity<List<NewsDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
