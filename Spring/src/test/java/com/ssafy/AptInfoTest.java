package com.ssafy;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.aptinfo.model.dto.AptInfoDto;
import com.ssafy.aptinfo.model.service.AptInfoService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AptInfoTest extends AbstractTest {

	// 로깅 처리를 위해 Logger 추가
	private static Logger logger = LoggerFactory.getLogger(AptInfoTest.class);
	
	// 픽스쳐 시작
	@Autowired
	AptInfoService aptInfoService;
	
	AptInfoDto aptInfoDto1;
	AptInfoDto aptInfoDto2;
	// 픽스쳐 끝
	
	@Before
	public void setUp() {
		aptInfoDto1 = new AptInfoDto("아파트테스트1", "2022", "도로명1", "번지1", "1234567890");
		aptInfoDto2 = new AptInfoDto("아파트테스트2", "2022", "도로명2", "번지2", "9876543210");
	}
	
	@Test
	public void searchTest() {
		ArrayList<AptInfoDto> list = (ArrayList<AptInfoDto>) aptInfoService.getAptByDong("1111011000");
		for(AptInfoDto apt : list) {
			System.out.println(apt);
		}
	}
	
	@Ignore
	@Test
	public void test1_insertAndUpdateAndDeleteTest() {
		
		// 삽입
		aptInfoService.insert(aptInfoDto1);
		
		AptInfoDto result = aptInfoService.search(aptInfoDto1.getAptCode());
		
		assertEquals(aptInfoDto1.getApartmentName(), result.getApartmentName());
		assertEquals(aptInfoDto1.getAptCode(), result.getAptCode());
		assertEquals(aptInfoDto1.getBuildYear(), result.getBuildYear());
		assertEquals(aptInfoDto1.getBunji(), result.getBunji());
		assertEquals(aptInfoDto1.getRoadName(), result.getRoadName());
		assertEquals(aptInfoDto1.getDongCode(), result.getDongCode());
		assertEquals(aptInfoDto1.getSidoCode(), result.getSidoCode());
		assertEquals(aptInfoDto1.getGugunCode(), result.getGugunCode());
		
		// 수정
		aptInfoDto1.setApartmentName(aptInfoDto2.getApartmentName());
		aptInfoDto1.setBuildYear(aptInfoDto2.getBuildYear());
		aptInfoDto1.setBunji(aptInfoDto2.getBunji());
		aptInfoDto1.setRoadName(aptInfoDto2.getRoadName());
		aptInfoDto1.setDongCode(aptInfoDto2.getDongCode());
		
		boolean resultBoolean = aptInfoService.update(aptInfoDto1);
		assertEquals(true, resultBoolean);
		
		result = aptInfoService.search(aptInfoDto1.getAptCode());
		
		assertEquals(aptInfoDto1.getAptCode(), result.getAptCode()); // aptCode 는 변함없음
		assertEquals(aptInfoDto1.getApartmentName(), aptInfoDto2.getApartmentName());
		assertEquals(aptInfoDto1.getBuildYear(), aptInfoDto2.getBuildYear());
		assertEquals(aptInfoDto1.getBunji(), aptInfoDto2.getBunji());
		assertEquals(aptInfoDto1.getRoadName(), aptInfoDto2.getRoadName());
		assertEquals(aptInfoDto1.getDongCode(), aptInfoDto2.getDongCode());
		assertEquals(aptInfoDto1.getSidoCode(), aptInfoDto2.getDongCode().substring(0, 2));
		assertEquals(aptInfoDto1.getGugunCode(), aptInfoDto2.getDongCode().substring(0, 5));
		
		// 삭제
		aptInfoService.delete(aptInfoDto1.getAptCode());
		
		result = aptInfoService.search(aptInfoDto1.getAptCode());
		
		assertNull(result);
	}

}
