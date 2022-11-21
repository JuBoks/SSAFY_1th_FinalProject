package com.ssafy.aptdeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
}
