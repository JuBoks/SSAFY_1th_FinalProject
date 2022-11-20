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

import com.ssafy.yeonliprent.model.dto.YeonlipRentDto;
import com.ssafy.yeonliprent.model.service.YeonlipRentService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class YeonlipRentTest extends AbstractTest {
	
	// 로깅 처리를 위해 Logger 추가
	private static Logger logger = LoggerFactory.getLogger(YeonlipRentTest.class);
	
	// 픽스쳐 시작
	@Autowired
	YeonlipRentService yeonlipRentService;
	
	YeonlipRentDto yeonlipRentDto1;
	YeonlipRentDto yeonlipRentDto2;
	// 픽스쳐 끝
	
	@Before
	public void setUp() {
		yeonlipRentDto1 = new YeonlipRentDto("전세", 25000, "30", 2022, 12, 1, "82.164", "5", 999999);
		yeonlipRentDto2 = new YeonlipRentDto("월세", 75000, "10", 2021, 6, 11, "354.55", "7", 999998);
	}
	
	@Test
	public void test1_insertAndUpdateAndDeleteTest() {
		
		// 추가
		yeonlipRentService.insert(yeonlipRentDto1);
		
		YeonlipRentDto expect1 = yeonlipRentService.search(yeonlipRentDto1.getNo());
		
		assertEquals(expect1.getType(), yeonlipRentDto1.getType());
		assertEquals(expect1.getDeposit(), yeonlipRentDto1.getDeposit());
		assertEquals(expect1.getMonthlyRent(), yeonlipRentDto1.getMonthlyRent());
		assertEquals(expect1.getDealYear(), yeonlipRentDto1.getDealYear());
		assertEquals(expect1.getDealMonth(), yeonlipRentDto1.getDealMonth());
		assertEquals(expect1.getDealDay(), yeonlipRentDto1.getDealDay());
		assertEquals(expect1.getArea(), yeonlipRentDto1.getArea());
		assertEquals(expect1.getFloor(), yeonlipRentDto1.getFloor());
		assertEquals(expect1.getYeonlipCode(), yeonlipRentDto1.getYeonlipCode());
		
		// 수정
		yeonlipRentDto1.setType(yeonlipRentDto2.getType());
		yeonlipRentDto1.setDeposit(yeonlipRentDto2.getDeposit());
		yeonlipRentDto1.setMonthlyRent(yeonlipRentDto2.getMonthlyRent());
		yeonlipRentDto1.setDealYear(yeonlipRentDto2.getDealYear());
		yeonlipRentDto1.setDealMonth(yeonlipRentDto2.getDealMonth());
		yeonlipRentDto1.setDealDay(yeonlipRentDto2.getDealDay());
		yeonlipRentDto1.setArea(yeonlipRentDto2.getArea());
		yeonlipRentDto1.setFloor(yeonlipRentDto2.getFloor());
		yeonlipRentDto1.setYeonlipCode(yeonlipRentDto2.getYeonlipCode());
		
		boolean expected = yeonlipRentService.update(yeonlipRentDto1);
		assertEquals(expected, true);
		
		expect1 = yeonlipRentService.search(yeonlipRentDto1.getNo());
		
		assertEquals(expect1.getType(), yeonlipRentDto2.getType());
		assertEquals(expect1.getDeposit(), yeonlipRentDto2.getDeposit());
		assertEquals(expect1.getMonthlyRent(), yeonlipRentDto2.getMonthlyRent());
		assertEquals(expect1.getDealYear(), yeonlipRentDto2.getDealYear());
		assertEquals(expect1.getDealMonth(), yeonlipRentDto2.getDealMonth());
		assertEquals(expect1.getDealDay(), yeonlipRentDto2.getDealDay());
		assertEquals(expect1.getArea(), yeonlipRentDto2.getArea());
		assertEquals(expect1.getFloor(), yeonlipRentDto2.getFloor());
		assertEquals(expect1.getYeonlipCode(), yeonlipRentDto2.getYeonlipCode());
		
		// 삭제
		yeonlipRentService.delete(yeonlipRentDto1.getNo());
		
		expect1 = yeonlipRentService.search(yeonlipRentDto1.getNo());
		
		assertNull(expect1);
		
	}
	
}
