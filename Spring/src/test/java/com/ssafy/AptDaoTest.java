//package com.ssafy;
//
//import static org.junit.Assert.assertEquals;
//
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.ssafy.apt.model.dao.AptDao;
//import com.ssafy.apt.model.dto.AptDto;
//import com.ssafy.board.model.dao.BoardDao;
//import com.ssafy.board.model.dto.BoardDto;
//
//public class AptDaoTest extends AbstractTest {
//
//	// 로깅 처리를 위해 Logger 추가
//	private static Logger logger = LoggerFactory.getLogger(AptDaoTest.class);
//	
//	// 픽스쳐 시작
//	@Autowired
//	AptDao aptDao;
//	
//	Map<String, Object> param1; // for selectAll
//	Map<String, Object> param2; // for searchApt
//	Map<String, Object> param3; // for selectAptSearchCount
//	// 픽스쳐 끝
//	
//	@Before
//	public void setUp() {
//		param1 = new HashMap<String, Object>();
//		param2 = new HashMap<String, Object>();
//		param3 = new HashMap<String, Object>();
//		param1.put("start", 0);
//		param1.put("listsize", 10);
//	}
//	
//	@Test
//	public void searchAptListTest() throws SQLException {
//		
//		// LIMIT 만 있을 경우
//		List<AptDto> selectAll = aptDao.searchAptList(param1);
//		assertEquals(selectAll.size(), 10);
//		
//		param1.remove("start");
//		param1.remove("listsize");
//		
//		// dongCode만 있을 경우
//		param1.put("dongCode", "1111011500");
//		selectAll = aptDao.searchAptList(param1);
//		assertEquals(selectAll.size(), 310);
//		
//		// dongCode + dealYear
//		param1.put("dealYear", "2021");
//		selectAll = aptDao.searchAptList(param1);
//		assertEquals(selectAll.size(), 25);
//		
//		// dongCode + dealYear + dealMonth
//		param1.put("dealMonth", "3");
//		selectAll = aptDao.searchAptList(param1);
//		assertEquals(selectAll.size(), 6);
//		
//		// aptName 만 있을 경우
//		param2.put("aptName", "매트로빌리지");
//		List<AptDto> searchApt = aptDao.searchAptList(param2);
//		assertEquals(searchApt.size(), 3);
//		
//		// aptName + dongCode
//		param2.put("aptName", "매");
//		param2.put("dongCode", "1111011800");
//		searchApt = aptDao.searchAptList(param2);
//		assertEquals(searchApt.size(), 18);
//	}
//	
//	@Test
//	public void selectAptSearchCountTest() throws SQLException {
//		
//		// aptName 만 있을 경우
//		param3.put("aptName", "매트로빌리지");
//		int count = aptDao.selectAptSearchCount(param3);
//		assertEquals(count, 3);
//		
//		// aptName + dongCode
//		param3.put("aptName", "매");
//		param3.put("dongCode", "1111011800");
//		count = aptDao.selectAptSearchCount(param3);
//		assertEquals(count, 18);
//	}
//	
//	
//	
//}
