package com.ssafy.board.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.board.model.dto.BoardDto;
import com.ssafy.board.model.service.BoardService;


@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardRestController {

	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	

	@Autowired
	private BoardService boardService;

	@GetMapping
	public ResponseEntity<?> listArticle(@RequestParam Map<String, Object> map) {
		try {
			List<BoardDto> listArticle = boardService.listArticle(map);
			return new ResponseEntity<List<BoardDto>>(listArticle, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/count")
	public ResponseEntity<?> getArticleCount(@RequestParam Map<String, Object> map) {
		try {
			System.out.println(map.keySet());
			int count = boardService.getCountBoardList(map);
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{articleno}")
	public ResponseEntity<?> getArticle(@PathVariable("articleno") int articleno) {

		try {
			boardService.updateHit(articleno);
			BoardDto article = boardService.getArticle(articleno);
			return new ResponseEntity<BoardDto>(article, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{articleno}")
	public ResponseEntity<?> modifyArticle(@PathVariable("articleno") int articleno,
			@RequestBody BoardDto boardDto) {

		try {
			boardDto.setArticleNo(articleno);
			boolean isModify = boardService.modifyArticle(boardDto);

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

		System.out.println("??");
		try {
			boolean isDelete = boardService.deleteArticle(articleno);

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
	public ResponseEntity<?> writeArticle(@RequestBody BoardDto boardDto) {

		try {
			boolean isWrite = boardService.writeArticle(boardDto);

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
