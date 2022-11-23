package com.ssafy.notice.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.notice.model.dto.NoticeDto;
import com.ssafy.util.PageNavigation;

public interface NoticeService {

	boolean writeArticle(NoticeDto noticeDto) throws Exception;
	
	List<NoticeDto> listArticle(Map<String, Object> map) throws Exception;
	
	NoticeDto getArticle(int articleNo) throws Exception;
	
	void updateHit(int articleNo) throws Exception;
	
	boolean modifyArticle(NoticeDto noticeDto) throws Exception;
	
	boolean deleteArticle(int articleNo) throws Exception;
	
	int getCountNoticeList(Map<String, Object> map) throws Exception;
	
	PageNavigation makePageNavigation(Map<String, Object> map) throws Exception;
}
