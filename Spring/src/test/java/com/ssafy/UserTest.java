package com.ssafy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.user.model.dao.UserDao;
import com.ssafy.user.model.dto.UserDto;
import com.ssafy.util.SizeConstant;

public class UserTest extends AbstractTest {
	
	// 로깅 처리를 위해 Logger 추가
	private static Logger logger = LoggerFactory.getLogger(UserTest.class);
	
	// 픽스쳐 시작
	@Autowired
	UserDao userDao;
	
	Map<String, Object> param1;
	
	UserDto user1;
	UserDto user2;
	// 픽스쳐 끝
	
	@Before
	public void setUp() {
		user1 = new UserDto("ssafy", "1234", "싸피1", "aa", "010", 1);
		user2 = new UserDto("admin", "1234", "관리자1", "aa", "010", 0);
		
		param1 = new HashMap<String, Object>();
		
		param1.put("start", 0);
		param1.put("spl", SizeConstant.SIZE_PER_LIST);
	}
	
	@Test
	public void insertAndGetOne() throws SQLException { // 추가, 조회 테스트
		
		userDao.deleteAll();
		
		userDao.insert(user1);
		
		UserDto result1 = userDao.select(user1.getUserId());
		assertEquals(result1.getUserName(), user1.getUserName());
		assertEquals(result1.getUserPhone(), user1.getUserPhone());
		assertEquals(result1.getUserAddr(), user1.getUserAddr());
		assertEquals(result1.getUserAuth(), user1.getUserAuth());
		
	}
	
	@Test
	public void updateAndGetOne() throws SQLException { // 업데이트, 조회 테스트
		
		userDao.deleteAll();
		
		userDao.insert(user1);
		
		user1.setUserName("[변경] 이름");
		user1.setUserPhone("[변경] 핸드폰");
		user1.setUserPwd("[변경] 패스워드");
		user1.setUserAuth(0);
		user1.setUserAddr("[변경] 주소");
		
		userDao.update(user1);
		
		UserDto result1 = userDao.select(user1.getUserId());
		assertEquals(result1.getUserName(), user1.getUserName());
		assertEquals(result1.getUserPhone(), user1.getUserPhone());
		assertEquals(result1.getUserAddr(), user1.getUserAddr());
		assertEquals(result1.getUserAuth(), user1.getUserAuth());
	}
	
	@Test
	public void DeleteAndGetOne() throws SQLException { // 삭제, 조회 테스트
		
		userDao.deleteAll();

		userDao.insert(user1);
		
		userDao.delete(user1.getUserId());
		
		UserDto result1 = userDao.select(user1.getUserId());
		assertNull(result1);
		
	}
	
	@Test
	public void login() throws SQLException {
		
		userDao.deleteAll();

		userDao.insert(user1);
		
		UserDto result1 = userDao.login(user1);
		assertEquals(result1.getUserName(), user1.getUserName());
		assertEquals(result1.getUserPhone(), user1.getUserPhone());
		assertEquals(result1.getUserAddr(), user1.getUserAddr());
		assertEquals(result1.getUserAuth(), user1.getUserAuth());
		
	}
	
	@Test
	public void selectAll() throws SQLException {
		
		userDao.deleteAll();
		
		userDao.insert(user1);
		userDao.insert(user2);
		
		ArrayList<UserDto> userList = new ArrayList<UserDto>();
		userList.add(user2);
		userList.add(user1);
		
		ArrayList<UserDto> result = (ArrayList<UserDto>) userDao.selectAll(param1);
		for(int i = 0 ; i < userList.size() ; i++) {
			UserDto result1 = result.get(i);
			UserDto actual1 = userList.get(i);
			
			assertEquals(result1.getUserName(), actual1.getUserName());
			assertEquals(result1.getUserPhone(), actual1.getUserPhone());
			assertEquals(result1.getUserAddr(), actual1.getUserAddr());
			assertEquals(result1.getUserAuth(), actual1.getUserAuth());
		}
		
	}
	
	@Test
	public void selectCount() throws SQLException {
		
		userDao.deleteAll();
		
		userDao.insert(user1);
		userDao.insert(user2);
		
		int result = userDao.selectCount();
		
		assertEquals(result, 2);
		
	}
	
}
