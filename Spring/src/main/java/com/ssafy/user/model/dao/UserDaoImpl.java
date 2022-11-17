package com.ssafy.user.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.board.model.dto.BoardDto;
import com.ssafy.user.model.dto.UserDto;
import com.ssafy.util.DBUtil;

import java.sql.ResultSet;

public class UserDaoImpl {

	private static UserDaoImpl instance = new UserDaoImpl();
	private static DBUtil dbUtil = DBUtil.getInstance();
	
	private UserDaoImpl() { }

	public static UserDaoImpl getInstance() {
		return instance;
	}

	public int insert(UserDto userDto) throws SQLException {
		int cnt = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO user(USER_ID, USER_PWD, USER_NAME, USER_ADDR, USER_PHONE, USER_AUTH) \n");
		sql.append("VALUES(?, ?, ?, ?, ?, ?) \n");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, userDto.getUserId());
			pstmt.setString(++idx, userDto.getUserPwd());
			pstmt.setString(++idx, userDto.getUserName());
			pstmt.setString(++idx, userDto.getUserAddr());
			pstmt.setString(++idx, userDto.getUserPhone());
			pstmt.setInt(++idx, userDto.getUserAuth());
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
	
		return cnt;
	}
	
	public UserDto login(String userId, String userPwd) throws SQLException {
		UserDto user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		DBUtil dbutil = DBUtil.getInstance();

		try {
			conn = dbutil.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append("select USER_ID,USER_PWD,USER_NAME,USER_ADDR,USER_PHONE,USER_AUTH \n");
			sb.append("from user \n");
			sb.append("where USER_ID = ? && USER_PWD = ? \n");
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);

			result = pstmt.executeQuery();
			if (result.next()) {
				user = new UserDto();
				user.setUserId(result.getString("USER_ID"));
				user.setUserName(result.getString("USER_NAME"));
				user.setUserAddr(result.getString("USER_ADDR"));
				user.setUserPhone(result.getString("USER_PHONE"));
				user.setUserAuth(Integer.parseInt(result.getString("USER_AUTH")));
			}		
		} finally {
			dbutil.close(result, pstmt, conn);
		}

		return user;
	}

	public UserDto select(String id) throws SQLException {
		UserDto user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append("select USER_ID,USER_PWD,USER_NAME,USER_ADDR,USER_PHONE,USER_AUTH \n");
			sb.append("from user \n");
			sb.append("where USER_ID = ? \n");
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new UserDto();
				user.setUserId(rs.getString("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserPwd(rs.getString("USER_PWD"));
				user.setUserAddr(rs.getString("USER_ADDR"));
				user.setUserPhone(rs.getString("USER_PHONE"));
				user.setUserAuth(Integer.parseInt(rs.getString("USER_AUTH")));
			}
		
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

		return user;
	}

	public int update(UserDto userDto) throws SQLException {
		int cnt = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE user \n");
		sql.append("SET USER_NAME= ?, USER_PWD = ?, USER_ADDR = ?, USER_PHONE = ?, USER_AUTH = ? \n");
		sql.append("WHERE USER_ID = ? \n");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			
			System.out.println("유저객체임 : " + userDto);
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, userDto.getUserName());
			pstmt.setString(++idx, userDto.getUserPwd());
			pstmt.setString(++idx, userDto.getUserAddr());
			pstmt.setString(++idx, userDto.getUserPhone());
			pstmt.setInt(++idx, userDto.getUserAuth());
			pstmt.setString(++idx, userDto.getUserId());
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
	
		return cnt;
	}

	public int delete(String userId) throws SQLException {
		int cnt = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM user \n");
		sql.append("WHERE USER_ID = ? \n");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setString(++idx, userId);
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
	
		return cnt;
	}

	public ArrayList<UserDto> selectAll(Map<String, String> map) throws SQLException {
		ArrayList<UserDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT USER_ID, USER_NAME, USER_ADDR, USER_PHONE, USER_AUTH \n");
			sb.append("FROM user \n");
			String userId = map.get("userId");
			if (userId != null) {
				sb.append("WHERE USER_ID LIKE ? \n");
			}
			sb.append("ORDER BY USER_AUTH ASC, USER_ID ASC \n");
			sb.append("LIMIT ?, ? \n");
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			int idx = 0;
			if (userId != null) {
				pstmt.setString(++idx, "%" + userId + "%");
			}
			pstmt.setInt(++idx, Integer.parseInt(map.get("start")));
			pstmt.setInt(++idx, Integer.parseInt(map.get("spl")));
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserDto user = new UserDto();
				user.setUserId(rs.getString("USER_ID"));
				user.setUserName(rs.getString("USER_NAME"));
				user.setUserAddr(rs.getString("USER_ADDR"));
				user.setUserPhone(rs.getString("USER_PHONE"));
				user.setUserAuth(Integer.parseInt(rs.getString("USER_AUTH")));
				
				list.add(user);
			}
		
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}

		return list;
	}

	public int selectCount() throws SQLException {
		
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();

			StringBuilder sb = new StringBuilder();
			sb.append("SELECT COUNT(*) \n");
			sb.append("FROM user \n");
			
			String sql = sb.toString();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		
		return cnt;
	}

	public int deleteAll() throws SQLException {
		int cnt = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM user \n");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbUtil.getConnection();
			
			pstmt = conn.prepareStatement(sql.toString());
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			dbUtil.close(pstmt, conn);
		}
	
		return cnt;
	}
}
