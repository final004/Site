package com.hanains.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hanains.mysite.vo.BoardVo;

public class BoardDao {
	public List<BoardVo> getList() {

		List<BoardVo> list = new ArrayList<BoardVo>();

		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1.드라이버 로딩(클래스 동적 로딩)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.DB 연결
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(dbUrl, "webdb", "webdb");

			// 3.statement 생성
			stmt = connection.createStatement();

			// 4.SQL 실행
			String sql = "select a.no," + " a.title," + " a.member_no," + " b.name as member_name," + " a.view_cnt,"
					+ " to_char(a.reg_date, 'yyyy-mm-dd hh:mi:ss')" + " from board a, member b"
					+ " where a.member_no = b.no" + " order by a.reg_date desc";
			rs = stmt.executeQuery(sql);

			// 5.결과 가져오기
			while (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String member_no = rs.getString(3);
				String name = rs.getString(4);
				String view_cnt = rs.getString(5);
				String date = rs.getString(6);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setMember_no(member_no);
				vo.setName(name);
				vo.setView_cnt(view_cnt);
				vo.setDate(date);
				list.add(vo);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("에러 - " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public BoardVo view(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_u = null;
		ResultSet rs = null;
		BoardVo vo1 = null;
		try {
			// 1.드라이버 로딩(클래스 동적 로딩)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.DB 연결
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(dbUrl, "webdb", "webdb");

			// 3.statement 준비
			String sql = "select no, title, content, member_no" + " from board" + " where no=?";

			pstmt = connection.prepareStatement(sql);

			String update = "update board set view_cnt = view_cnt + 1 where no=?";
			pstmt_u = connection.prepareStatement(update);

			// 4.binding
			pstmt.setLong(1, vo.getNo());
			pstmt_u.setLong(1, vo.getNo());

			rs = pstmt.executeQuery();
			Long no = 0L;
			String title = "";
			String content = "";

			if (rs.next()) {
				no = rs.getLong(1);
				title = rs.getString(2);
				content = rs.getString(3);
			}

			// 5.SQL 실행
			pstmt.executeUpdate();
			pstmt_u.executeUpdate();

			vo1 = new BoardVo();
			vo1.setNo(no);
			vo1.setTitle(title);
			vo1.setContent(content);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("에러 - " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo1;
	}

	public void insert(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버 로딩(클래스 동적 로딩)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.DB 연결
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(dbUrl, "webdb", "webdb");

			// 3.statement 준비
			String sql = "insert into board" + " values ( board_no_seq.nextval, ?, ?, ?, 0, SYSDATE )";
			pstmt = connection.prepareStatement(sql);

			// 4.binding
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getMember_no());

			// 5.SQL 실행
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("에러 - " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버 로딩(클래스 동적 로딩)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.DB 연결
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(dbUrl, "webdb", "webdb");

			// 3.statement 준비
			String sql = "delete from board" + " where no = ?";
			pstmt = connection.prepareStatement(sql);

			// 4.binding
			pstmt.setLong(1, vo.getNo());

			// 5.SQL 실행
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("에러 - " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void revise(BoardVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			// 1.드라이버 로딩(클래스 동적 로딩)
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.DB 연결
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
			connection = DriverManager.getConnection(dbUrl, "webdb", "webdb");

			// 3.statement 준비
			String sql = "update board" + " set title=?, content=?" + " where no=?";
			pstmt = connection.prepareStatement(sql);

			// 4.binding
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());

			// 5.SQL 실행
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("에러 - " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}