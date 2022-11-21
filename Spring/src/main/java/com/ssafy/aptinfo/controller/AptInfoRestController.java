package com.ssafy.aptinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.aptinfo.model.dto.AptInfoDto;
import com.ssafy.aptinfo.model.service.AptInfoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/aptinfo")
public class AptInfoRestController {
	
	@Autowired
	private AptInfoService aptInfoService;
	
	@GetMapping("/{dongCode}")
	public ResponseEntity<?> getAptByDong(@PathVariable("dongCode") String dongCode) {
		try {
			List<AptInfoDto> result = aptInfoService.getAptByDong(dongCode);
			System.out.println(result);
			return new ResponseEntity<List<AptInfoDto>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
