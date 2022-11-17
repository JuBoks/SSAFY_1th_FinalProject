package com.ssafy.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
	
	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	public String handlerException(Exception ex, Model model) {
		logger.error("Exception 발생 : {}", ex.getMessage());
		
		model.addAttribute("msg", "처리중 예외가 발생하였습니다.");
		return "error/error";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(Exception ex, Model model) {
		logger.error("404 발생 : {}", "404 page not found");
		
		model.addAttribute("msg", "요청하신 파일은 존재하지 않습니다.");
		return "error/error";
	}

}
