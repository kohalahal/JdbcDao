package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	private static Dao instance;
	
	private final String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private final String DB_USER = "student";
	private final String DB_PASSWORD = "1234";
	
	protected Connection conn = null;
	protected Statement stmt = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null; // SQL 질의에 의해 생성된 테이블을 저장하는 객체
	
	protected String tableName = "";
	
	private boolean autoCommit = true;

	protected Dao() {
		this(null, "");
	}
	 
	protected Dao(String tableName) {
		this(null, tableName );
	}
	
	protected Dao(Connection conn, String tableName) {
		this.tableName = tableName;
		setConn(conn);
	}
	
	public static synchronized Dao getInstance() {
		if(instance==null) {
			instance = new Dao();
		}
		return instance;
	}
	
	void setConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("new conn");
			if(!autoCommit) {
				try {
					conn.setAutoCommit(autoCommit);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void setConn(Connection conn) {
		setConn(conn, autoCommit);
	}

	void setConn(Connection conn, boolean autoCommit) {
		if(conn==null) {
			setConn();
			return;
		}
		this.conn = conn;
		setAutoCommit(autoCommit);
	}
		
	void setAutoCommit(boolean auto) {
		autoCommit = auto;
		if(conn!=null) {
			try {
				conn.setAutoCommit(auto);
				System.out.println("conn.겟오토커밋:"+conn.getAutoCommit());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void rollback() {
		if(conn!=null) {
			try {
				System.out.println("rollback");
				conn.rollback();
			} catch(SQLException e){
			   
			}
		}
	}
	
	void commit() {
		if(conn!=null) {
			try {
				System.out.println("commit");
				conn.commit();
			} catch(SQLException e){
			   
			}
		}
	}
	 
	void close(AutoCloseable... acs)  {
		for(AutoCloseable a:acs) {
			if (a != null)
				try {
					a.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
	}
	
	void closeQuery() {
		close(stmt, pstmt, rs);
	}
	
	void closeConn() {
		close(conn);
	}
	
	void closeAll() {
		close(stmt, pstmt, rs, conn);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
