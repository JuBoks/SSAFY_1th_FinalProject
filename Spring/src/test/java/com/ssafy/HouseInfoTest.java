//package com.ssafy;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.ssafy.aptinfo.model.dao.AptInfoDao;
//import com.ssafy.aptinfo.model.dto.AptInfoDto;
//
//public class HouseInfoTest extends AbstractTest {
//	
//	// 로깅 처리를 위해 Logger 추가
//	private static Logger logger = LoggerFactory.getLogger(AptDaoTest.class);
//	
//	// 픽스쳐 시작
//	@Autowired
//	AptInfoDao houseinfodao;
//	
//	AptInfoDto houseinfo1;
//	AptInfoDto houseinfo2;
//	
//	Long aptCode = 999999999999999L;
//	// 픽스쳐 끝
//	
//	@Before
//	public void setUp() {
//		houseinfo1 = new AptInfoDto("삼정그린코아",aptCode,"0009","0000",2008,"녹산동","1","1","1","2","1.5746540320628","1.968575235313", "녹산산단3길","0","00001","00001","100135","01","11111"); 
//		houseinfo2 = new AptInfoDto("삼성전기",123456771L,"2009","0200",2008,"송정동","2","2","2","2","2.5746540320628","2.968575235313", "녹산산단3길","1","00002","00002","100135","02","11112"); 
//	}
//	
//	@Test
//	public void insertAndGetOne() {//select 그리고 insert
//		
//		AptInfoDto result1 = houseinfodao.select(aptCode);
//		if(result1!=null) {
//			houseinfodao.delete(aptCode);
//		}
//		houseinfodao.insert(houseinfo1);
//		
//		result1 = houseinfodao.select(aptCode);
//		assertEquals(result1.getApartmentName(), houseinfo1.getApartmentName());
//		assertEquals(result1.getAptCode(),houseinfo1.getAptCode());
//		assertEquals(result1.getBonbun(),houseinfo1.getBonbun());
//		assertEquals(result1.getBubun(),houseinfo1.getBubun());
//		assertEquals(result1.getBuildYear(),houseinfo1.getBuildYear());
//		assertEquals(result1.getDong(),houseinfo1.getDong());
//		assertEquals(result1.getDongCode(),houseinfo1.getDongCode());
//		assertEquals(result1.getEubmyundongCode(),houseinfo1.getEubmyundongCode());
//		assertEquals(result1.getJibun(),houseinfo1.getJibun());
//		assertEquals(result1.getLandCode(),houseinfo1.getLandCode());
//		assertEquals(result1.getLat(),houseinfo1.getLat());
//		assertEquals(result1.getLng(),houseinfo1.getLng());
//		assertEquals(result1.getRoadName(),houseinfo1.getRoadName());
//		assertEquals(result1.getRoadNameBasementCode(),houseinfo1.getRoadNameBasementCode());
//		assertEquals(result1.getRoadNameBonbun(),houseinfo1.getRoadNameBonbun());
//		assertEquals(result1.getRoadNameBubun(),houseinfo1.getRoadNameBubun());
//		assertEquals(result1.getRoadNameCode(),houseinfo1.getRoadNameCode());
//		assertEquals(result1.getRoadNameSeq(),houseinfo1.getRoadNameSeq());
//		assertEquals(result1.getSigunguCode(),houseinfo1.getSigunguCode());
//		
//		
//	}
//	@Test
//	public void updateAndGetOne() {//select 그리고 modify
//		AptInfoDto result1 = houseinfodao.select(aptCode);
//		if(result1 != null) {
//			houseinfodao.delete(aptCode);
//		}
//		houseinfodao.insert(houseinfo1);
//		
//		houseinfodao.update(houseinfo2);
//		
//		result1 = houseinfodao.select(aptCode);
//		assertEquals(result1.getApartmentName(), houseinfo1.getApartmentName());
//		assertEquals(result1.getAptCode(),houseinfo1.getAptCode());
//		assertEquals(result1.getBonbun(),houseinfo1.getBonbun());
//		assertEquals(result1.getBubun(),houseinfo1.getBubun());
//		assertEquals(result1.getBuildYear(),houseinfo1.getBuildYear());
//		assertEquals(result1.getDong(),houseinfo1.getDong());
//		assertEquals(result1.getDongCode(),houseinfo1.getDongCode());
//		assertEquals(result1.getEubmyundongCode(),houseinfo1.getEubmyundongCode());
//		assertEquals(result1.getJibun(),houseinfo1.getJibun());
//		assertEquals(result1.getLandCode(),houseinfo1.getLandCode());
//		assertEquals(result1.getLat(),houseinfo1.getLat());
//		assertEquals(result1.getLng(),houseinfo1.getLng());
//		assertEquals(result1.getRoadName(),houseinfo1.getRoadName());
//		assertEquals(result1.getRoadNameBasementCode(),houseinfo1.getRoadNameBasementCode());
//		assertEquals(result1.getRoadNameBonbun(),houseinfo1.getRoadNameBonbun());
//		assertEquals(result1.getRoadNameBubun(),houseinfo1.getRoadNameBubun());
//		assertEquals(result1.getRoadNameCode(),houseinfo1.getRoadNameCode());
//		assertEquals(result1.getRoadNameSeq(),houseinfo1.getRoadNameSeq());
//		assertEquals(result1.getSigunguCode(),houseinfo1.getSigunguCode());
//		
//	}
//	
//	@After
//	@Test
//	public void deleteAndGetOne() {//select 그리고 delete
//		
//		AptInfoDto result1 = houseinfodao.select(aptCode);
//		if(result1 != null) {
//			houseinfodao.delete(aptCode);
//			
//			result1 = houseinfodao.select(aptCode);
//			assertNull(result1);
//		}
//		
//	}
//	
//	
//}
