package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hanains.mysite.vo.GuestbookVo;

public class GuestbookDao {
	
	public List<GuestbookVo> getList(){
		
		List<GuestbookVo> list = new ArrayList<GuestbookVo>();
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1.드라이버 로딩(클래스 동적 로딩)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DB 연결
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(dbUrl, "webdb", "webdb");
			
			//3.statement 생성
			stmt = connection.createStatement();
			
			//4.SQL 실행
			String sql = "select no, name, password, message, to_char(reg_date,'YYYY-MM-DD HH:MI:SS') from guestbook";
			rs = stmt.executeQuery(sql);
			
			//5.결과 가져오기
			while(rs.next()){
				long no = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String message = rs.getString(4);
				String date = rs.getString(5);
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setMessage(message);
				vo.setDate(date);
				list.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패 - " + e);
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("에러 - " + e);
		} finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if(connection != null){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public void insert(GuestbookVo vo){
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			//1.드라이버 로딩(클래스 동적 로딩)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DB 연결
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(dbUrl, "webdb", "webdb");
			
			//3.statement 준비
			String sql = "insert into guestbook values(guestbook_seq.nextval, ?, ?, ?, SYSDATE)";
			pstmt = connection.prepareStatement(sql);
			
			//4.binding
			System.out.println(vo.getName() +":"+vo.getPassword()+":" + vo.getMessage() +":" +vo.getDate());
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getMessage());
			
			//5.SQL 실행
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패 - " + e);
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("에러 - " + e);
		} finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void delete(GuestbookVo vo){
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		try {
			//1.드라이버 로딩(클래스 동적 로딩)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2.DB 연결
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(dbUrl, "webdb", "webdb");
			
			//3.statement 준비
			String sql = "delete from guestbook where no=? and password=?";
			pstmt = connection.prepareStatement(sql);

			//4.binding
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getPassword());
			//5.SQL 실행
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패 - " + e);
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("에러 - " + e);
		} finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(connection != null){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
