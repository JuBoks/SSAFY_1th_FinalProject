package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 해당 폴더는 src/main/java 밑에 (아무 패키지에) 들어가야 함.
 */
public class DbUtilDao {

	private String driverName;
	private String TABLE_NAME;
	private String url;
	private String user = "ssafy";
	private String pass = "ssafy";
	
	public DbUtilDao(String DBName) {
		driverName = "com.mysql.cj.jdbc.Driver";
		TABLE_NAME = DBName;
		url = "jdbc:mysql://localhost:3306/" + TABLE_NAME + "?serverTimezone=UTC";
		user = "ssafy";
		pass = "ssafy";
		
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버 생성 실패.");
		}
	}


	public void createTable(String[] columns, String tableName) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("CREATE TABLE " + tableName +"( \n");
			for(int i = 0 ; i < columns.length - 1 ; i++) {
				sql.append("`" + columns[i] +"`text,");
			}
			sql.append("`" + columns[columns.length-1] +"`text \n");
			sql.append(") \n");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.execute(sql.toString());
			System.out.println(tableName + "테이블이 생성되었습니다.");
		}
		finally {
			close(pstmt, conn);
		}
	}
	
	public void insertByCSV(String FileName, String[] columns, String tableName, String folderPath) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("LOAD DATA INFILE '" +folderPath + FileName +"' \n");
			sql.append("INTO TABLE " + tableName +" \n");
			sql.append("CHARACTER SET euckr \n");
			sql.append("FIELDS TERMINATED BY ',' \n");
			sql.append("ENCLOSED BY '\"' \n");
			sql.append("LINES TERMINATED BY '\\n' \n");
			sql.append("IGNORE 1 ROWS \n");
			sql.append("(");
			for(int i = 0 ; i < columns.length - 1 ; i++) {
				sql.append("@`" + columns[i] +"`,");
			}
			sql.append("@`" + columns[columns.length-1] +"`) \n");
			sql.append("SET ");
			for(int i = 0 ; i < columns.length - 1 ; i++) {
				sql.append("`" + columns[i] +"` = nullif(@`" + columns[i] + "`, ''),");
			}
			sql.append("`" + columns[columns.length-1] +"` = nullif(@`" + columns[columns.length-1] + "`, '')");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.execute(sql.toString());
			System.out.println(FileName + "파일이 저장되었습니다. [table:" + tableName + "]");
		}
		finally {
			close(pstmt, conn);
		}
	}

	
	public Connection getConnection(String dbName) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=UTC";
		return DriverManager.getConnection(url, user, pass);
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}
	
	public void close(AutoCloseable... closeables) {
		for (AutoCloseable c : closeables) {
			if (c != null) {
				try {
					c.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
