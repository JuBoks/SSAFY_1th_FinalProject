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

import com.ssafy.aptdeal.model.dto.AptDealDto;
import com.ssafy.aptdeal.model.service.AptDealService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AptDealTest extends AbstractTest {
	
	// 로깅 처리를 위해 Logger 추가
	private static Logger logger = LoggerFactory.getLogger(AptDealTest.class);
	
	// 픽스쳐 시작
	@Autowired
	AptDealService aptDealService;
	
	AptDealDto aptDealDto1;
	AptDealDto aptDealDto2;
	// 픽스쳐 끝
	
	@Before
	public void setUp() {
		aptDealDto1 = new AptDealDto("20,000", 2022, 11, 29, "82.164", "5", null, 999999);
		aptDealDto2 = new AptDealDto("70,000", 2021, 3, 11, "516.53", "8", null, 999999);
	}
	
	@Test
	public void test1_insertAndUpdateAndDeleteTest() {
		
		// 추가
		aptDealService.insert(aptDealDto1);
		
		AptDealDto expect1 = aptDealService.search(aptDealDto1.getNo());
		
		assertEquals(expect1.getDealAmount(), aptDealDto1.getDealAmount());
		assertEquals(expect1.getAptCode(), aptDealDto1.getAptCode());
		assertEquals(expect1.getDealYear(), aptDealDto1.getDealYear());
		assertEquals(expect1.getDealDay(), aptDealDto1.getDealDay());
		assertEquals(expect1.getArea(), aptDealDto1.getArea());
		assertEquals(expect1.getFloor(), aptDealDto1.getFloor());
		assertEquals(expect1.getCancelDealDay(), aptDealDto1.getCancelDealDay());
		
		// 수정
		aptDealDto1.setDealAmount(aptDealDto2.getDealAmount());
		aptDealDto1.setDealYear(aptDealDto2.getDealYear());
		aptDealDto1.setDealDay(aptDealDto2.getDealDay());
		aptDealDto1.setArea(aptDealDto2.getArea());
		aptDealDto1.setFloor(aptDealDto2.getFloor());
		
		boolean expected = aptDealService.update(aptDealDto1);
		assertEquals(expected, true);
		
		expect1 = aptDealService.search(aptDealDto1.getNo());
		
		assertEquals(expect1.getAptCode(), aptDealDto1.getAptCode()); // aptCode 는 변함없음
		assertEquals(expect1.getDealAmount(), aptDealDto2.getDealAmount());
		assertEquals(expect1.getDealYear(), aptDealDto2.getDealYear());
		assertEquals(expect1.getDealDay(), aptDealDto2.getDealDay());
		assertEquals(expect1.getArea(), aptDealDto2.getArea());
		assertEquals(expect1.getFloor(), aptDealDto2.getFloor());
		
		// 삭제
		aptDealService.delete(aptDealDto1.getNo());
		
		expect1 = aptDealService.search(aptDealDto1.getNo());
		
		assertNull(expect1);
		
	}
	
}
