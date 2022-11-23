package com.ssafy.notice.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.notice.model.dto.NoticeDto;

public interface NoticeDao {

	int writeArticle(NoticeDto noticeDto) throws SQLException;
	
	List<NoticeDto> listArticle(Map<String, Object> map) throws SQLException;
	
	NoticeDto getArticle(int articleNo) throws SQLException;
	
	void updateHit(int articleNo) throws SQLException;
	
	int modifyArticle(NoticeDto noticeDto) throws SQLException;
	
	int deleteArticle(int articleNo) throws SQLException;
	
	int getCountNoticeList(Map<String, Object> map) throws SQLException; // 글 전체 개수 가져오기
	
	int deleteAll() throws SQLException;
}
