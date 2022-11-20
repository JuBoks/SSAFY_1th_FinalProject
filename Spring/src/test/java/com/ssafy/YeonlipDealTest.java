package com.ssafy;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.yeonlipdeal.model.dto.YeonlipDealDto;
import com.ssafy.yeonlipdeal.model.service.YeonlipDealService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class YeonlipDealTest extends AbstractTest {
	
	// 로깅 처리를 위해 Logger 추가
	private static Logger logger = LoggerFactory.getLogger(YeonlipDealTest.class);
	
	// 픽스쳐 시작
	@Autowired
	YeonlipDealService yeonlipDealService;
	
	YeonlipDealDto yeonlipDealDto1;
	YeonlipDealDto yeonlipDealDto2;
	// 픽스쳐 끝
	
	@Before
	public void setUp() {
		yeonlipDealDto1 = new YeonlipDealDto("20,000", 2022, 11, 29, "82.164", "99914", "5", null, 999999);
		yeonlipDealDto2 = new YeonlipDealDto("70,000", 2021, 3, 11, "516.53", "75.121", "8", null, 999999);
	}
	
	@Test
	public void test1_insertAndUpdateAndDeleteTest() {
		
		// 추가
		yeonlipDealService.insert(yeonlipDealDto1);
		
		YeonlipDealDto expect1 = yeonlipDealService.search(yeonlipDealDto1.getNo());
		
		assertEquals(expect1.getDealAmount(), yeonlipDealDto1.getDealAmount());
		assertEquals(expect1.getYeonlipCode(), yeonlipDealDto1.getYeonlipCode());
		assertEquals(expect1.getDealYear(), yeonlipDealDto1.getDealYear());
		assertEquals(expect1.getDealDay(), yeonlipDealDto1.getDealDay());
		assertEquals(expect1.getArea(), yeonlipDealDto1.getArea());
		assertEquals(expect1.getFloor(), yeonlipDealDto1.getFloor());
		assertEquals(expect1.getCancelDealDay(), yeonlipDealDto1.getCancelDealDay());
		
		// 수정
		yeonlipDealDto1.setDealAmount(yeonlipDealDto2.getDealAmount());
		yeonlipDealDto1.setDealYear(yeonlipDealDto2.getDealYear());
		yeonlipDealDto1.setDealDay(yeonlipDealDto2.getDealDay());
		yeonlipDealDto1.setArea(yeonlipDealDto2.getArea());
		yeonlipDealDto1.setLandArea(yeonlipDealDto2.getLandArea());
		yeonlipDealDto1.setFloor(yeonlipDealDto2.getFloor());
		
		boolean expected = yeonlipDealService.update(yeonlipDealDto1);
		assertEquals(expected, true);
		
		expect1 = yeonlipDealService.search(yeonlipDealDto1.getNo());
		
		assertEquals(expect1.getDealAmount(), yeonlipDealDto2.getDealAmount());
		assertEquals(expect1.getDealYear(), yeonlipDealDto2.getDealYear());
		assertEquals(expect1.getDealDay(), yeonlipDealDto2.getDealDay());
		assertEquals(expect1.getArea(), yeonlipDealDto2.getArea());
		assertEquals(expect1.getLandArea(), yeonlipDealDto2.getLandArea());
		assertEquals(expect1.getFloor(), yeonlipDealDto2.getFloor());
		
		// 삭제
		yeonlipDealService.delete(yeonlipDealDto1.getNo());
		
		expect1 = yeonlipDealService.search(yeonlipDealDto1.getNo());
		
		assertNull(expect1);
		
	}
	
}
