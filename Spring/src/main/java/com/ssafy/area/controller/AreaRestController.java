package com.ssafy.area.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.area.model.dto.AreaDto;
import com.ssafy.area.model.service.AreaService;

@RestController
@RequestMapping("/area")
public class AreaRestController {

	private AreaService areaService;
	
	@Autowired
	public AreaRestController(AreaService areaService) {
		this.areaService = areaService;
	}
	
	@GetMapping("/sido")
	public ResponseEntity<?> sido() {
		try {
			List<AreaDto> map = areaService.selectSidoNames();
			return new ResponseEntity<List<AreaDto>>(map, HttpStatus.OK);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<?> gugun(@RequestParam("sidoCode") String sidoCode) {
		
		sidoCode = sidoCode.substring(0, 2);
		
		try {
			List<AreaDto> map = areaService.selectGugunNames(sidoCode);
			return new ResponseEntity<List<AreaDto>>(map, HttpStatus.OK);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/dong")
	public ResponseEntity<?> dong(@RequestParam("gugunCode") String gugunCode) {
		
		gugunCode = gugunCode.substring(0, 5);
		
		try {
			List<AreaDto> map = areaService.selectDongNames(gugunCode);
			return new ResponseEntity<List<AreaDto>>(map, HttpStatus.OK);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
