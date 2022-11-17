package com.ssafy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.housedeal.model.dao.HouseDealDao;
import com.ssafy.housedeal.model.dto.HouseDealDto;

public class HouseDealTest extends AbstractTest {
	
	// 로깅 처리를 위해 Logger 추가
	private static Logger logger = LoggerFactory.getLogger(AptDaoTest.class);
	
	// 픽스쳐 시작
	@Autowired
	HouseDealDao houseDealDao;
	
	HouseDealDto houseDeal1;
	HouseDealDto houseDeal2;
	Long no = 666677778888877L;
	Long aptCode = 999999999999999L;
	// 픽스쳐 끝
	
	@Before
	public void setUp() {
		// no 는 <selectKey> 로 넣고 있으니 현재 값은 무의미한 값이다.
		houseDeal1 = new HouseDealDto(no, "100000", 2020, 1, 14, "100.11", "10", "1", aptCode);
		houseDeal2 = new HouseDealDto(no, "99999", 1999, 12, 30, "9999.12", "7", "2", aptCode);
	}
	
	@Test
	public void insertAndGetOne() {
		
		HouseDealDto result1 = houseDealDao.select(aptCode);
		if(result1 != null) {
			houseDealDao.delete(aptCode);
		}
		houseDealDao.insert(houseDeal1);
		
		result1 = houseDealDao.select(aptCode);
		assertEquals(result1.getArea(), houseDeal1.getArea());
		assertEquals(result1.getCancelDealType(), houseDeal1.getCancelDealType());
		assertEquals(result1.getDealAmount(), houseDeal1.getDealAmount());
		assertEquals(result1.getDealDay(), houseDeal1.getDealDay());
		assertEquals(result1.getDealMonth(), houseDeal1.getDealMonth());
		assertEquals(result1.getDealYear(), houseDeal1.getDealYear());
		assertEquals(result1.getFloor(), houseDeal1.getFloor());
		assertEquals(result1.getNo(), houseDeal1.getNo());
	}
	
	@Test
	public void updateAndGetOne() {
		HouseDealDto result1 = houseDealDao.select(aptCode);
		if(result1 != null) {
			houseDealDao.delete(aptCode);
		}
		houseDealDao.insert(houseDeal1);
		
		houseDealDao.update(houseDeal2);
		
		result1 = houseDealDao.select(aptCode);
		assertEquals(result1.getArea(), houseDeal2.getArea());
		assertEquals(result1.getCancelDealType(), houseDeal2.getCancelDealType());
		assertEquals(result1.getDealAmount(), houseDeal2.getDealAmount());
		assertEquals(result1.getDealDay(), houseDeal2.getDealDay());
		assertEquals(result1.getDealMonth(), houseDeal2.getDealMonth());
		assertEquals(result1.getDealYear(), houseDeal2.getDealYear());
		assertEquals(result1.getFloor(), houseDeal2.getFloor());
		assertEquals(result1.getNo(), houseDeal2.getNo());
		
	}
	
	@After
	@Test
	public void deleteAndGetOne() {
		
		HouseDealDto result1 = houseDealDao.select(aptCode);
		if(result1 != null) {
			houseDealDao.delete(aptCode);
			
			result1 = houseDealDao.select(aptCode);
			assertNull(result1);
		}
		
	}
	
	
}
