package com.ssafy.notice.controller;

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

import com.ssafy.notice.model.dto.NoticeDto;
import com.ssafy.notice.model.service.NoticeService;



@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeRestController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeRestController.class);
	

	@Autowired
	private NoticeService noticeService;

	@GetMapping
	public ResponseEntity<?> listArticle(@RequestParam Map<String, Object> map) {
		try {
			List<NoticeDto> listArticle = noticeService.listArticle(map);
			return new ResponseEntity<List<NoticeDto>>(listArticle, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/count")
	public ResponseEntity<?> getArticleCount(@RequestParam Map<String, Object> map) {
		try {
			System.out.println(map.keySet());
			int count = noticeService.getCountNoticeList(map);
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{articleno}")
	public ResponseEntity<?> getArticle(@PathVariable("articleno") int articleno) {

		try {
			noticeService.updateHit(articleno);
			NoticeDto article = noticeService.getArticle(articleno);
			return new ResponseEntity<NoticeDto>(article, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{articleno}")
	public ResponseEntity<?> modifyArticle(@PathVariable("articleno") int articleno,
			@RequestBody NoticeDto noticeDto) {

		try {
			noticeDto.setArticleNo(articleno);
			boolean isModify = noticeService.modifyArticle(noticeDto);

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

	@DeleteMapping("/{articleno}")
	public ResponseEntity<?> deleteArticle(@PathVariable("articleno") int articleno) {

		try {
			boolean isDelete = noticeService.deleteArticle(articleno);

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
	public ResponseEntity<?> writeArticle(@RequestBody NoticeDto noticeDto) {

		try {
			boolean isWrite = noticeService.writeArticle(noticeDto);

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
