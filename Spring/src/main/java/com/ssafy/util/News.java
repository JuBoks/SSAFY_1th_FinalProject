//package com.ssafy.util;
//
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.ssafy.news.dto.NewsDto;
//
//public class News {
//	public static void main(String[] args) throws IOException {
//		// 현재 날짜 구하기 (시스템 시계, 시스템 타임존)
//        LocalDate now = LocalDate.now();
// 
//        int year = now.getYear();
//        String month = now.getMonth().toString();
//        int monthValue = now.getMonthValue();
//        int dayOfMonth = now.getDayOfMonth();
//        int dayOfYear = now.getDayOfYear();
//        String dayOfWeek = now.getDayOfWeek().toString();
//        int dayOfWeekValue = now.getDayOfWeek().getValue();
//        System.out.println(year);
//        
//	}
//}