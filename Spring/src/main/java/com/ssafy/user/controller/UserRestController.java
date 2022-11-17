package com.ssafy.user.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.user.model.dto.UserDto;
import com.ssafy.user.model.service.UserService;



@RestController
@RequestMapping("/user")
public class UserRestController {
	
	private Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	private UserService userService;
	
	
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session) {

		try {
			UserDto selected = userService.login(userDto);

			if (selected != null) {
				session.setAttribute("loginUser", selected);
				return new ResponseEntity<UserDto>(selected, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		try {
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@RequestBody UserDto userDto) {

		try {
			boolean isJoin = userService.join(userDto);

			if (isJoin) {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/view")
	public ResponseEntity<?> view(HttpSession session) {

		try {
			UserDto sessionUser = (UserDto) session.getAttribute("loginUser");
			UserDto userDto = userService.getDetail(sessionUser.getUserId());

			if (userDto != null) {
				return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/modify")
	public ResponseEntity<?> modify(UserDto userDto, HttpSession session) {

		try {
			UserDto sessionUser = (UserDto) session.getAttribute("loginUser");
			userDto.setUserId(sessionUser.getUserId());
			
			boolean isModify = userService.modify(userDto);
			
			if (isModify) {
				UserDto result = userService.getDetail(userDto.getUserId());
				session.setAttribute("loginUser", result);
				return new ResponseEntity<UserDto>(result, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> modify(@PathVariable("userId") String userId,
			HttpSession session) {

		try {
			boolean isDelete = userService.remove(userId);
			
			if (isDelete) {
				session.invalidate();
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
