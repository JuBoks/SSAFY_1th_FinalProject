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

import com.ssafy.aptrent.model.dto.AptRentDto;
import com.ssafy.aptrent.model.service.AptRentService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AptRentTest extends AbstractTest {
	
	// 로깅 처리를 위해 Logger 추가
	private static Logger logger = LoggerFactory.getLogger(AptRentTest.class);
	
	// 픽스쳐 시작
	@Autowired
	AptRentService aptRentService;
	
	AptRentDto aptRentDto1;
	AptRentDto aptRentDto2;
	// 픽스쳐 끝
	
	@Before
	public void setUp() {
		aptRentDto1 = new AptRentDto("전세", 25000, "30", 2022, 12, 1, "82.164", "5", 999999);
		aptRentDto2 = new AptRentDto("월세", 75000, "10", 2021, 6, 11, "354.55", "7", 999998);
	}
	
	@Test
	public void test1_insertAndUpdateAndDeleteTest() {
		
		// 추가
		aptRentService.insert(aptRentDto1);
		
		AptRentDto expect1 = aptRentService.search(aptRentDto1.getNo());
		
		assertEquals(expect1.getType(), aptRentDto1.getType());
		assertEquals(expect1.getDeposit(), aptRentDto1.getDeposit());
		assertEquals(expect1.getMonthlyRent(), aptRentDto1.getMonthlyRent());
		assertEquals(expect1.getDealYear(), aptRentDto1.getDealYear());
		assertEquals(expect1.getDealMonth(), aptRentDto1.getDealMonth());
		assertEquals(expect1.getDealDay(), aptRentDto1.getDealDay());
		assertEquals(expect1.getArea(), aptRentDto1.getArea());
		assertEquals(expect1.getFloor(), aptRentDto1.getFloor());
		assertEquals(expect1.getAptCode(), aptRentDto1.getAptCode());
		
		// 수정
		aptRentDto1.setType(aptRentDto2.getType());
		aptRentDto1.setDeposit(aptRentDto2.getDeposit());
		aptRentDto1.setMonthlyRent(aptRentDto2.getMonthlyRent());
		aptRentDto1.setDealYear(aptRentDto2.getDealYear());
		aptRentDto1.setDealMonth(aptRentDto2.getDealMonth());
		aptRentDto1.setDealDay(aptRentDto2.getDealDay());
		aptRentDto1.setArea(aptRentDto2.getArea());
		aptRentDto1.setFloor(aptRentDto2.getFloor());
		aptRentDto1.setAptCode(aptRentDto2.getAptCode());
		
		boolean expected = aptRentService.update(aptRentDto1);
		assertEquals(expected, true);
		
		expect1 = aptRentService.search(aptRentDto1.getNo());
		
		assertEquals(expect1.getType(), aptRentDto2.getType());
		assertEquals(expect1.getDeposit(), aptRentDto2.getDeposit());
		assertEquals(expect1.getMonthlyRent(), aptRentDto2.getMonthlyRent());
		assertEquals(expect1.getDealYear(), aptRentDto2.getDealYear());
		assertEquals(expect1.getDealMonth(), aptRentDto2.getDealMonth());
		assertEquals(expect1.getDealDay(), aptRentDto2.getDealDay());
		assertEquals(expect1.getArea(), aptRentDto2.getArea());
		assertEquals(expect1.getFloor(), aptRentDto2.getFloor());
		assertEquals(expect1.getAptCode(), aptRentDto2.getAptCode());
		
		// 삭제
		aptRentService.delete(aptRentDto1.getNo());
		
		expect1 = aptRentService.search(aptRentDto1.getNo());
		
		assertNull(expect1);
		
	}
	
}
