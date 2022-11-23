package com.ssafy.notice.model;
public interface NoticeDao {

	int writeArticle(BoardDto boardDto) throws SQLException;
	
	List<BoardDto> listArticle(Map<String, Object> map) throws SQLException;
	
	BoardDto getArticle(int articleNo) throws SQLException;
	
	void updateHit(int articleNo) throws SQLException;
	
	int modifyArticle(BoardDto boardDto) throws SQLException;
	
	int deleteArticle(int articleNo) throws SQLException;
	
	int getCountBoardList(Map<String, Object> map) throws SQLException; // 글 전체 개수 가져오기
	
	int deleteAll() throws SQLException;
}
