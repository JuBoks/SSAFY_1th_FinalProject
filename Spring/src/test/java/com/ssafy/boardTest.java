//package com.ssafy;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.ssafy.board.model.dao.BoardDao;
//import com.ssafy.board.model.dto.BoardDto;
//import com.ssafy.util.SizeConstant;
//
//public class boardTest  extends AbstractTest {
//	
//	// 로깅 처리를 위해 Logger 추가
//	private static Logger logger = LoggerFactory.getLogger(UserTest.class);
//	
//	// 픽스쳐 시작
//	@Autowired
//	BoardDao boardDao;
//
//	Map<String, Object> param;//페이지 넘길 start/spl
//	
//	Map<String, Object> param1;//아무것도 없는 경우
//	Map<String, Object> param2;//작성자 명
//	Map<String, Object> param3;//제목
//	Map<String, Object> param4;//내용별
//	
//	BoardDto board1;
//	BoardDto board2;
//	BoardDto board3;
//	BoardDto board4;
//	// 픽스쳐 끝
//	
//	@Before
//	public void setUp() {
//		board1 = new BoardDto(1,"ssafy1","김싸피","안녕하세요","안녕하세요1",0,"1",6);
//		board2 = new BoardDto(2,"ssafy1","이싸피","안뇽","안녕하세요2",0,"1",6);
//		board3 = new BoardDto(3,"ssafy2","김싸피","제목별","안녕하세요1",0,"1",6);
//		board4 = new BoardDto(4,"ssafy3","이싸피","제목별","안녕하세요2",0,"1",6);
//		
//		param = new HashMap<String, Object>();
//		param1 = new HashMap<String, Object>();
//		param2 = new HashMap<String, Object>();
//		param3 = new HashMap<String, Object>();
//		param4 = new HashMap<String, Object>();
//		
//		param1.put("start", 0);
//		param1.put("spl", SizeConstant.SIZE_PER_LIST);
//		param2.put("start", 0);
//		param2.put("spl", SizeConstant.SIZE_PER_LIST);
//		param3.put("start", 0);
//		param3.put("spl", SizeConstant.SIZE_PER_LIST);
//		param4.put("start", 0);
//		param4.put("spl", SizeConstant.SIZE_PER_LIST);
//	}
//	
//	@Test
//	public void writeArticleAndGetOne() throws SQLException { // 추가, 조회 테스트
//		
//		boardDao.deleteAll();
//		
//		boardDao.writeArticle(board1);
//		
//		BoardDto result1= boardDao.getArticle(board1.getArticleNo());
//		
//		assertEquals(result1.getArticleNo(),board1.getArticleNo());
//		assertEquals(result1.getUserId(),board1.getUserId());
//		assertEquals(result1.getSubject(),board1.getSubject());
//		assertEquals(result1.getContent(),board1.getContent());
//		assertEquals(result1.getHit(),board1.getHit());
//		
//	}
//	
//	@Test
//	public void listarticle() throws SQLException {
//		
//		boardDao.deleteAll();
//		
//		//검색조건x
//		param1.put("key", "");
//		param1.put("word", "");
//		//작성자별 글 검색
//		param2.put("key", "userid");
//		param2.put("word", "ssafy1");
//		//제목별 글 검색
//		param3.put("key", "subject");
//		param3.put("word", "안뇽");
//
//		// 게시글 추가
//		boardDao.writeArticle(board1);
//		boardDao.writeArticle(board2);
//		boardDao.writeArticle(board3);
//		boardDao.writeArticle(board4);
//		
//		// 1. 검색조건 x
//		List<BoardDto> expectList = boardDao.listArticle(param1);
//		
//		ArrayList<BoardDto> actualList = new ArrayList<BoardDto>();
//		actualList.add(board4);
//		actualList.add(board3);
//		actualList.add(board2);
//		actualList.add(board1);
//		
//		for(int i = 0 ; i < expectList.size() ; i++) {
//			BoardDto expect = expectList.get(i);
//			BoardDto actual = actualList.get(i);
//			
//			assertEquals(expect.getArticleNo(),actual.getArticleNo());
//			assertEquals(expect.getUserId(),actual.getUserId());
//			assertEquals(expect.getSubject(),actual.getSubject());
//			assertEquals(expect.getContent(),actual.getContent());
//			assertEquals(expect.getHit(),actual.getHit());
//		}
//		
//		// 2. 작성자별 글 검색
//		expectList = boardDao.listArticle(param2);
//	
//		actualList = new ArrayList<BoardDto>();
//		actualList.add(board2);
//		actualList.add(board1);
//		
//		for(int i = 0 ; i < expectList.size() ; i++) {
//			BoardDto expect = expectList.get(i);
//			BoardDto actual = actualList.get(i);
//			
//			assertEquals(expect.getArticleNo(),actual.getArticleNo());
//			assertEquals(expect.getUserId(),actual.getUserId());
//			assertEquals(expect.getSubject(),actual.getSubject());
//			assertEquals(expect.getContent(),actual.getContent());
//			assertEquals(expect.getHit(),actual.getHit());
//		}
//		
//		// 3. 제목 글 검색
//		expectList = boardDao.listArticle(param3);
//		
//		actualList = new ArrayList<BoardDto>();
//		actualList.add(board2);
//		
//		for(int i = 0 ; i < expectList.size() ; i++) {
//			BoardDto expect = expectList.get(i);
//			BoardDto actual = actualList.get(i);
//			
//			assertEquals(expect.getArticleNo(),actual.getArticleNo());
//			assertEquals(expect.getUserId(),actual.getUserId());
//			assertEquals(expect.getSubject(),actual.getSubject());
//			assertEquals(expect.getContent(),actual.getContent());
//			assertEquals(expect.getHit(),actual.getHit());
//		}
//		
//	}
//	
//	@Test
//	public void getArticleTest()  throws SQLException {
//		boardDao.deleteAll();
//		
//		boardDao.writeArticle(board1);
//		boardDao.writeArticle(board2);
//		boardDao.writeArticle(board3);
//		boardDao.writeArticle(board4);
//		
//		BoardDto expect = boardDao.getArticle(board3.getArticleNo());
//		assertEquals(expect.getArticleNo(),board3.getArticleNo());
//		assertEquals(expect.getUserId(),board3.getUserId());
//		assertEquals(expect.getSubject(),board3.getSubject());
//		assertEquals(expect.getContent(),board3.getContent());
//		assertEquals(expect.getHit(),board3.getHit());
//	}
//	
//	@Test
//	public void updateHitTest() throws SQLException {
//		
//		boardDao.deleteAll();
//		
//		boardDao.writeArticle(board1);
//		
//		boardDao.updateHit(board1.getArticleNo());
//		
//		BoardDto expect = boardDao.getArticle(board1.getArticleNo());
//		
//		assertEquals(expect.getHit(), new Integer(1));
//		
//	}
//	
//	@Test
//	public void updateAndGetOne() throws SQLException { // 업데이트, 조회 테스트
//		
//		boardDao.deleteAll();
//		
//		boardDao.writeArticle(board1);
//		
//		board1.setSubject("변경 제목");
//		board1.setContent("변경 내용");
//		
//		boardDao.modifyArticle(board1);
//		
//		BoardDto result1= boardDao.getArticle(board1.getArticleNo());
//		
//		assertEquals(result1.getArticleNo(),board1.getArticleNo());
//		assertEquals(result1.getUserId(),board1.getUserId());
//		assertEquals(result1.getSubject(),board1.getSubject());
//		assertEquals(result1.getContent(),board1.getContent());
//		assertEquals(result1.getHit(),board1.getHit());
//		
//	}
//	
//	@Test
//	public void deleteArticleTest() throws SQLException { // 삭제, 조회 테스트
//		
//		boardDao.deleteAll();
//
//		boardDao.writeArticle(board1);
//
//		boardDao.deleteArticle(board1.getArticleNo());
//		
//		BoardDto result1 = boardDao.getArticle(board1.getArticleNo());
//		assertNull(result1);
//		
//	}
//	
//	@Test
//	public void getCountBoardListTest() throws SQLException {
//		
//		boardDao.deleteAll();
//		
//		// 게시글 추가
//		boardDao.writeArticle(board1);
//		boardDao.writeArticle(board2);
//		boardDao.writeArticle(board3);
//		boardDao.writeArticle(board4);
//		
//		// 1. 전부 검색
//		param.put("key", "");
//		param.put("word", "");
//		
//		int expect = boardDao.getCountBoardList(param);
//		assertEquals(expect, 4);
//		
//		// 2. 제목별 개수 검색
//		param.put("key", "subject");
//		param.put("word", "제목");
//		
//		expect = boardDao.getCountBoardList(param);
//		assertEquals(expect, 2);
//		
//		// 3. 작성자별 개수 검색
//		param.put("key", "userid");
//		param.put("word", "ssafy1");
//		
//		expect = boardDao.getCountBoardList(param);
//		assertEquals(expect, 2);
//	}
//	
//	
//	
//}