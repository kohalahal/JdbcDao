package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao extends Dao {
	private static UserDao instance;
	
	static String tableName = "USER_INFO";
		
	private String sqlInsertData = "INSERT INTO user_info VALUES (?, ?, ?, ?, ?, ?)";
	private String sqlUpdateUser = "UPDATE USER_INFO SET PASSWORD=?, NAME=?, EMAIL=?, IN_DATE=?, UP_DATE=? WHERE USER_ID=?"; // sql
	private String sqlDellUser = "DELETE FROM user_info WHERE USER_ID=?";
	private String sqlDellAllUser = "DELETE FROM user_info";
	private String sqlSelectUser = "SELECT * FROM USER_INFO WHERE USER_ID=?";		
	private String sqlSelectAllUser = "SELECT * FROM USER_INFO"; // 시스템의 날짜를 출력한다.

	private UserDao() {
		super(tableName);
		// TODO Auto-generated constructor stub
	}
	private UserDao(Connection conn) {
		super(conn, tableName);
		// TODO Auto-generated constructor stub
	}
	
	public static synchronized UserDao getInstance() {
		if(instance==null) {
			instance = new UserDao();		
		}
		return instance;
	}
	
	public int insertUser(User u) {
		try {	
			pstmt = conn.prepareStatement(sqlInsertData);
			pstmt.setString(1, u.getUser_Id());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getName());
			pstmt.setString(4, u.getEmail());
			pstmt.setDate(5, new java.sql.Date(u.getIn_date().getTime()));
			pstmt.setDate(6, new java.sql.Date(u.getUp_date().getTime()));
			pstmt.executeUpdate();
			System.out.println(tableName+" 새레코드 추가");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(tableName+"새레코드 추가 실패");
			return 0;
		} finally {
			closeQuery();
		}
	}
	
	public int updateUser(User u) {
		try {		
			pstmt = conn.prepareStatement(sqlUpdateUser); // prepareStatement에서 해당 sql을 미리 컴파일한다.
			pstmt.setString(6, u.getUser_Id());
			pstmt.setString(1, u.getPassword());
			pstmt.setString(2, u.getName());
			pstmt.setString(3, u.getEmail());
			pstmt.setDate(4, new java.sql.Date(u.getIn_date().getTime()));
			pstmt.setDate(5, new java.sql.Date(u.getUp_date().getTime()));
			pstmt.executeUpdate();
			System.out.println(tableName+"유저 업데이트.");
			return 1;
		} catch (Exception e) { // 예외가 발생하면 예외 상황을 처리한다.
			e.printStackTrace();
			System.out.println(tableName+"유저 업데이트 실패");
			return 0;
		} finally { // 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)
			closeQuery();
		}
	}
	
	public int deleteUser(String userId) {
		try {
			pstmt = conn.prepareStatement(sqlDellUser);
			pstmt.setString(1, userId);			
			pstmt.executeUpdate();
			System.out.println(tableName+"유저 삭제");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(tableName+"유저 삭제 실패");
			return 0;
		} finally { // 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)
			closeQuery();
		}
	}
	
	public int deleteUser(User u) {
		return deleteUser(u.getUser_Id());
	}
	
	public int deleteAllUser() {
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(sqlDellAllUser);
			System.out.println(tableName+"다 삭제");
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(tableName+"다 삭제 실패");
			return 0;
		} finally { // 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)
			closeQuery();
		}
	}
	
	public User selectUser(String userId) throws Exception {
		User u = null;
		
		try { 
			pstmt = conn.prepareStatement(sqlSelectUser); // prepareStatement에서 해당 sql을 미리 컴파일한다.
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				u = getUserFromRs(rs);
			}
			if (rs.next()) {
				throw new Exception("셀렉트유저가 1명이상");
			}
		} catch (SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
		} finally { // 사용순서와 반대로 close 함
			closeQuery();
		}
		return u;
	}
	
	public User selectUser(User u) throws Exception {
		return selectUser(u.getUser_Id());
	}
	
	public List<User> selectAllUsers() {
		List<User> userList = new ArrayList<>();		
		try {
			stmt = conn.createStatement(); // Statement를 가져온다.
			rs = stmt.executeQuery(sqlSelectAllUser); // SQL문을 실행한다.
			while (rs.next()) {
				userList.add(getUserFromRs(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeQuery();
		}
		return userList;
	}
	
	public int countAllUsers() {
		int count = 0;
		try {
			stmt = conn.createStatement(); // Statement를 가져온다.
			rs = stmt.executeQuery(sqlSelectAllUser); // SQL문을 실행한다.
			while (rs.next()) {
				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeQuery();
		}
		return count;
	}
	
	private User getUserFromRs(ResultSet rs) {
		try {
			String id = rs.getString("user_id");
			String pass = rs.getString("password");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Date in_date = new Date(rs.getDate("in_date").getTime());
			Date up_date = new Date(rs.getDate("up_date").getTime());
			return new User(id, pass, name, email, in_date, up_date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

}
