package com.ssafy.apt.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.apt.model.dto.AptDto;
import com.ssafy.apt.model.service.AptService;

@RestController
@RequestMapping("/apt")
public class AptRestController {
	
	private AptService aptService;
	
	@Autowired
	public AptRestController(AptService aptService) {
		this.aptService = aptService;
	}

	@GetMapping("/list")
	public Object list(@RequestParam Map<String, Object> param) {
		
		List<AptDto> list = null;
		try {
			list = aptService.searchAptList(param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
