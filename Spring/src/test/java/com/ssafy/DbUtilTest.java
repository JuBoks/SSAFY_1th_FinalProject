package com.ssafy;

import java.io.File;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.util.DbUtilDao;



/**
 * 이 파일은 src/test/java 밑에 위치해야 함.
 * 
 * 3306 포트이고 유저아이디가 ssafy, 비밀번호가 ssafy 가 디폴트입니다.
 * 포트번호나 유저 정보가 다르다면 DbUtilDao 에서 직접 수정 요망.
 * 
 * 1. 데이터를 넣을 DB 지정하기
 * 2. csv 파일들이 있는 경로 지정하기
 * 3. 컬럼명 지정하기
 * 4. 생성할 테이블 이름 정하기
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DbUtilTest {

	DbUtilDao dbUtilDao = new DbUtilDao("housedata2"); // 1. 데이터를 넣을 DB 지정하기
	
	@Transactional
	@Test
	public void insertAndGetOne() {
		
		String folderPath = "C:\\crawling\\aptdeal"; // 2. csv 파일들이 있는 경로 지정하기
		String folderPathForDB = "/crawling/aptdeal/"; // 3. 2번에서 작성한 경로를 예시와 같은 형태로 변환하기
		
		File folder = new File(folderPath);
		File[] fileList = folder.listFiles();
		
		
		// 3. 컬럼명 지정하기
		String[] columns = {"시군구","번지","본번","부번","단지명","전용면적(㎡)","계약년월","계약일","거래금액(만원)","층","건축년도","도로명","해제사유발생일","거래유형","중개사소재지"};
		// 4. 생성할 테이블 이름 정하기
		String tableName = "apt_deal_dump";
		
		
		// 테이블 생성하는 코드
		try {
			dbUtilDao.createTable(columns, tableName);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println(tableName + " 테이블 생성 실패.");
			return;
		}
		
		// csv 넣어주는 코드
		for(File file : fileList) {
			String fileName = file.getName();
			try {
				dbUtilDao.insertByCSV(fileName, columns, tableName, folderPathForDB);
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
		}
		
		System.out.println("모든 작업이 완료되었습니다.");
	}
}
