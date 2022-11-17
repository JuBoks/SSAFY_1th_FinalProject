package com.ssafy;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 아래 두개의 Annotation 이 test 클래스마다 중복이기 때문에 따로 클래스를 빼놓은 것
 */
@RunWith(SpringRunner.class)
// xml 파일일 경우 locations 을 사용함
// class 파일일 경우 classes 를 사용함
//@ContextConfiguration(locations = "/application.xml")
//@ContextConfiguration(classes = ApplicationConfig.class)
//@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
@SpringBootTest
public class AbstractTest {
	
}
