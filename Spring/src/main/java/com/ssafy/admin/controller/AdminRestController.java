package com.ssafy.admin.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.user.model.dto.UserDto;
import com.ssafy.user.model.service.UserService;


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminRestController {

	private static final Logger logger = LoggerFactory.getLogger(AdminRestController.class);
	

	@Autowired
	private UserService userService;
	
	
	@Autowired
	public AdminRestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<?> listUsers(@RequestParam Map<String, Object> map) {
		try {
			System.out.println(map);
			List<UserDto> listUser = userService.getUserList(map);
			return new ResponseEntity<List<UserDto>>(listUser, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {
		try {
			UserDto user = userService.getDetail(userId);
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{userId}")
	public ResponseEntity<?> modifyUser(@PathVariable("userId") String userId,
			@RequestBody UserDto userDto) {

		System.out.println("수정들어옴");
		
		try {
			boolean isModify = userService.modify(userDto);

			if (isModify) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) {

		System.out.println("삭제들어옴");
		try {
			boolean isDelete = userService.remove(userId);

			if (isDelete) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> joinUser(@RequestBody UserDto userDto) {
		System.out.println("만들기");
		try {
			boolean isWrite = userService.join(userDto);

			if (isWrite) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}