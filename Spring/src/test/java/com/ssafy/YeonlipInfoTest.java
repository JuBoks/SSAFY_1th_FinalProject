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

import com.ssafy.yeonlipinfo.model.dto.YeonlipInfoDto;
import com.ssafy.yeonlipinfo.model.service.YeonlipInfoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class YeonlipInfoTest extends AbstractTest {

	// 로깅 처리를 위해 Logger 추가
	private static Logger logger = LoggerFactory.getLogger(YeonlipInfoTest.class);
	
	// 픽스쳐 시작
	@Autowired
	YeonlipInfoService yeonlipInfoService;
	
	YeonlipInfoDto yeonlipInfoDto1;
	YeonlipInfoDto yeonlipInfoDto2;
	// 픽스쳐 끝
	
	@Before
	public void setUp() {
		yeonlipInfoDto1 = new YeonlipInfoDto("연립테스트1", "2022", "도로명1", "번지1", "1234567890");
		yeonlipInfoDto2 = new YeonlipInfoDto("연립테스트2", "2022", "도로명2", "번지2", "9876543210");
	}
	
	@Test
	public void test1_insertAndUpdateAndDeleteTest() {
		
		// 삽입
		yeonlipInfoService.insert(yeonlipInfoDto1);
		
		YeonlipInfoDto result = yeonlipInfoService.search(yeonlipInfoDto1.getYeonlipCode());
		
		assertEquals(yeonlipInfoDto1.getYeonlipName(), result.getYeonlipName());
		assertEquals(yeonlipInfoDto1.getYeonlipCode(), result.getYeonlipCode());
		assertEquals(yeonlipInfoDto1.getBuildYear(), result.getBuildYear());
		assertEquals(yeonlipInfoDto1.getBunji(), result.getBunji());
		assertEquals(yeonlipInfoDto1.getRoadName(), result.getRoadName());
		assertEquals(yeonlipInfoDto1.getDongCode(), result.getDongCode());
		assertEquals(yeonlipInfoDto1.getSidoCode(), result.getSidoCode());
		assertEquals(yeonlipInfoDto1.getGugunCode(), result.getGugunCode());
		
		// 수정
		yeonlipInfoDto1.setYeonlipName(yeonlipInfoDto2.getYeonlipName());
		yeonlipInfoDto1.setBuildYear(yeonlipInfoDto2.getBuildYear());
		yeonlipInfoDto1.setBunji(yeonlipInfoDto2.getBunji());
		yeonlipInfoDto1.setRoadName(yeonlipInfoDto2.getRoadName());
		yeonlipInfoDto1.setDongCode(yeonlipInfoDto2.getDongCode());
		
		boolean resultBoolean = yeonlipInfoService.update(yeonlipInfoDto1);
		assertEquals(true, resultBoolean);
		
		result = yeonlipInfoService.search(yeonlipInfoDto1.getYeonlipCode());
		
		assertEquals(yeonlipInfoDto1.getYeonlipCode(), result.getYeonlipCode()); // aptCode 는 변함없음
		assertEquals(yeonlipInfoDto1.getYeonlipName(), yeonlipInfoDto2.getYeonlipName());
		assertEquals(yeonlipInfoDto1.getBuildYear(), yeonlipInfoDto2.getBuildYear());
		assertEquals(yeonlipInfoDto1.getBunji(), yeonlipInfoDto2.getBunji());
		assertEquals(yeonlipInfoDto1.getRoadName(), yeonlipInfoDto2.getRoadName());
		assertEquals(yeonlipInfoDto1.getDongCode(), yeonlipInfoDto2.getDongCode());
		assertEquals(yeonlipInfoDto1.getSidoCode(), yeonlipInfoDto2.getDongCode().substring(0, 2));
		assertEquals(yeonlipInfoDto1.getGugunCode(), yeonlipInfoDto2.getDongCode().substring(0, 5));
		
		// 삭제
		yeonlipInfoService.delete(yeonlipInfoDto1.getYeonlipCode());
		
		result = yeonlipInfoService.search(yeonlipInfoDto1.getYeonlipCode());
		
		assertNull(result);
	}

}
