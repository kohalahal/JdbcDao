package com.hal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao extends Dao<User> {
	private static UserDao instance = new UserDao();
	
	protected String tableName;
	protected boolean autoCommit = true;
	
	private String sqlInsertData = "INSERT INTO user_info VALUES (?, ?, ?, ?, ?, ?)";
	private String sqlUpdateUser = "UPDATE USER_INFO SET PASSWORD=?, NAME=?, EMAIL=?, IN_DATE=?, UP_DATE=? WHERE USER_ID=?"; // sql
	private String sqlDellUser = "DELETE FROM user_info WHERE USER_ID=?";
	private String sqlDellAllUser = "DELETE FROM user_info";
	private String sqlSelectUser = "SELECT * FROM USER_INFO WHERE USER_ID=?";		
	private String sqlSelectAllUser = "SELECT * FROM USER_INFO"; // 시스템의 날짜를 출력한다.
	
	public static Dao<User> getInstance() {
		return (Dao<User>) instance;
	}
	
	private UserDao() {
		super();
	}
	
	private UserDao(String tableName) {
		super();
		this.tableName = tableName;
	}

	@Override
	public int insertData(User user) {
		PreparedStatement pstmt = null;
		if(selectData(user)==null) {
		// TODO Auto-generated method stub
			try {	
				pstmt = conn.prepareStatement(sqlInsertData);
				pstmt.setString(1, user.getUser_Id());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
				pstmt.setDate(5, new java.sql.Date(user.getIn_date().getTime()));
				pstmt.setDate(6, new java.sql.Date(user.getUp_date().getTime()));
				pstmt.executeUpdate();
				System.out.println(tableName+" 새레코드 추가");
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(tableName+"새레코드 추가 실패");
				return 0;
			} finally {
				close(pstmt);
			}
		}
		return 0;
	}

	@Override
	public int updateData(User user) {
		PreparedStatement pstmt = null;
		try {		
			pstmt = conn.prepareStatement(sqlUpdateUser); // prepareStatement에서 해당 sql을 미리 컴파일한다.
			pstmt.setString(6, user.getUser_Id());
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setDate(4, new java.sql.Date(user.getIn_date().getTime()));
			pstmt.setDate(5, new java.sql.Date(user.getUp_date().getTime()));
			pstmt.executeUpdate();
			System.out.println(tableName+"유저 업데이트.");
			return 1;
		} catch (Exception e) { // 예외가 발생하면 예외 상황을 처리한다.
			e.printStackTrace();
			System.out.println(tableName+"유저 업데이트 실패");
			return 0;
		} finally { // 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)
			close(pstmt);
		}
	}

	@Override
	public User deleteData(User user) {
		return deleteUser(user.getUser_Id());
	}
	
	public User deleteUser(String userId) {
		User user = selectData(userId);
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sqlDellUser);
			pstmt.setString(1, userId);			
			pstmt.executeUpdate();
			System.out.println(tableName+"유저 삭제");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(tableName+"유저 삭제 실패");
		} finally { // 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)
			close(pstmt);
		}
		return user;
	}
	
	@Override
	public List<User> deleteAll() {
		List<User> list = selectAll();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(sqlDellAllUser);
			System.out.println(tableName+"다 삭제");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(tableName+"다 삭제 실패");
		} finally { // 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)
			close(stmt);
		}
		return list;
	}

	@Override
	public User selectData(User user) {
		return selectData(user.getUser_Id());
	}
	

	public User selectData(String userId) {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // SQL 질의에 의해 생성된 테이블을 저장하는 객체
		try { 
			pstmt = conn.prepareStatement(sqlSelectUser); // prepareStatement에서 해당 sql을 미리 컴파일한다.
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				user = getUserFromRs(rs);
			}
			if (rs.next()) {
				throw new Exception("셀렉트 유저가 1명이상");
			}
		} catch (SQLException e) {
			System.out.println("SQL Error : " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 사용순서와 반대로 close 함
			close(pstmt);
		}
		return user;
	}

	@Override
	public List<User> selectAll() {
		List<User> userList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null; // SQL 질의에 의해 생성된 테이블을 저장하는 객체
		try {
			stmt = conn.createStatement(); // Statement를 가져온다.
			rs = stmt.executeQuery(sqlSelectAllUser); // SQL문을 실행한다.
			while (rs.next()) {
				userList.add(getUserFromRs(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(stmt, rs);
		}
		return userList;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return tableName;
	}

	@Override
	public void setTableName(String tableName) {
		this.tableName = tableName;		
	}

	/* 편의 메소드  */
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
