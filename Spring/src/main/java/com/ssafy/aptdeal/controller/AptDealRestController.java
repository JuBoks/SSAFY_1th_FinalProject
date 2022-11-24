package com.ssafy.aptdeal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.aptdeal.model.dto.AptDealDto;
import com.ssafy.aptdeal.model.service.AptDealService;

@RestController
@RequestMapping("/aptdeal")
@CrossOrigin("*")
public class AptDealRestController {
	
	@Autowired
	private AptDealService aptDealService;
	
	@GetMapping("/{aptCode}")
	public ResponseEntity<?> getAptDealInfo(@PathVariable("aptCode") Integer aptCode) {
		try {
			List<AptDealDto> list = aptDealService.listAptDeal(aptCode);
			return new ResponseEntity<List<AptDealDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/cancel/{aptCode}")
	public ResponseEntity<?> getAptDealCancelInfo(@PathVariable("aptCode") Integer aptCode) {
		try {
			List<AptDealDto> list = aptDealService.listAptDealCancelByYM(aptCode);
			System.out.println(list);
			return new ResponseEntity<List<AptDealDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Year과 Month 를 Group by 하여 해당 아파트의 거래 내역을 들고옴
	@GetMapping("/group/{aptCode}")
	public ResponseEntity<?> getAptDealByYearMonth(@PathVariable("aptCode") Integer aptCode) {
		try {
			List<AptDealDto> list = aptDealService.listAptDealByYM(aptCode);
			return new ResponseEntity<List<AptDealDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 최근 30일 해당 아파트의 매매가격 평균
	@GetMapping("/avg/{aptCode}")
	public ResponseEntity<?> getAptDealAvgByMonth(@PathVariable("aptCode") Integer aptCode) {
		try {
			String result = aptDealService.getAptDealAvgByMonth(aptCode);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 특정 Year과 Month의 모든 해당 아파트의 거래 내역을 들고옴
	@GetMapping("/month/{aptCode}")
	public ResponseEntity<?> getAptDealByMonth(@PathVariable("aptCode") Integer aptCode, @RequestParam Map<String, Object> param) {
		try {
			System.out.println(param);
			param.put("aptCode", aptCode);
			List<AptDealDto> list = aptDealService.getAptDealByMonth(param);
			return new ResponseEntity<List<AptDealDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
