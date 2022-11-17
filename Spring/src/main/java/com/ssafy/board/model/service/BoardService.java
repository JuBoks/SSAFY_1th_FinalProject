package com.ssafy.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.board.model.dto.BoardDto;
import com.ssafy.util.PageNavigation;

public interface BoardService {

	boolean writeArticle(BoardDto boardDto) throws Exception;
	
	List<BoardDto> listArticle(Map<String, Object> map) throws Exception;
	
	BoardDto getArticle(int articleNo) throws Exception;
	
	void updateHit(int articleNo) throws Exception;
	
	boolean modifyArticle(BoardDto boardDto) throws Exception;
	
	boolean deleteArticle(int articleNo) throws Exception;
	
	int getCountBoardList(Map<String, Object> map) throws Exception;
	
	PageNavigation makePageNavigation(Map<String, Object> map) throws Exception;
}
