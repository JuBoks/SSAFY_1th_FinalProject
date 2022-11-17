package com.ssafy.alliance.controller;

import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/alliance")
public class AllianceRestController {
	
	// DECODE 키
	private static final String SERVICE_KEY = "ydzP1vshtX3XU9Zu3XK5RPZ2ftkK%2FrhzsCjKiPIFTb%2BdQp5LsUFibuvueXSZ7lfZ%2BYVmMbMhs3ITHjdBhfnYjQ%3D%3D";
	private static final String API_URL = "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade";

	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam Map<String, String> param) throws Exception {
		String sigunguCode = param.get("sigunguCode");
		String dealYear = param.get("dealYear");
		String dealMonth = param.get("dealMonth");
		
		if("".equals(dealYear) || "".equals(dealMonth)) {
			LocalDate now = LocalDate.now();
			dealYear = now.getYear() + "";
			dealMonth = "0" + now.getMonthValue();
		}
		
		StringBuilder urlBuilder = new StringBuilder(API_URL); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + SERVICE_KEY); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + URLEncoder.encode(sigunguCode, "UTF-8")); /*각 지역별 코드*/
        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode(dealYear + dealMonth, "UTF-8")); /*월 단위 신고자료*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*월 단위 신고자료*/
        URL url = new URL(urlBuilder.toString());
        
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		
		Map<String, Object> result = restTemplate.getForObject(url.toURI(), Map.class);
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
