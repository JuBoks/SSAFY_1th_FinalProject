package com.ssafy.favoritearea.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.favoritearea.model.dto.FavoriteAreaDto;
import com.ssafy.favoritearea.model.service.FavoriteAreaService;

@RestController
@RequestMapping("/favoritearea")
@CrossOrigin("*")
public class FavoriteAreaRestController {
	
	@Autowired
	private FavoriteAreaService favoriteAreaService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getFavoriteArea(@PathVariable String userId) {
		try {
			List<FavoriteAreaDto> result = favoriteAreaService.select(userId);
			return new ResponseEntity<List<FavoriteAreaDto>>(result, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/{userId}")
	public ResponseEntity<?> registerFavoriteArea(@PathVariable String userId, 
			@RequestBody FavoriteAreaDto favoriteAreaDto) {
		try {
			boolean isRegisted = favoriteAreaService.insert(favoriteAreaDto);
			
			if(isRegisted) {
				List<FavoriteAreaDto> result = favoriteAreaService.select(userId);
				return new ResponseEntity<List<FavoriteAreaDto>>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{userId}/{dongCode}")
	public ResponseEntity<?> deleteFavoriteArea(@PathVariable String userId, 
			@PathVariable String dongCode) {
		try {
			FavoriteAreaDto param = new FavoriteAreaDto(userId, dongCode);
			boolean isDeleted = favoriteAreaService.delete(param);
			
			if(isDeleted) {
				List<FavoriteAreaDto> result = favoriteAreaService.select(userId);
				return new ResponseEntity<List<FavoriteAreaDto>>(result, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
