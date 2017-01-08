package ch12.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();
	
	// jsp 페이지에서 DB 연동빈인 BoardDBBean 클래스의 메소드에 접근 시 필요
	public static BoardDBBean getInstance() {
		return instance;
	}
	
	private BoardDBBean() { }
	
	/**
	 * 커넥션 풀로부터 Connection 객체를 얻어냄
	 * DB 연동빈의 쿼리문을 수행하는 메소드에서 사용
	 * @return
	 * @throws Exception
	 */
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsptest");
		return ds.getConnection();
	}
	
	/**
	 * board 테이블에 글을 추가
	 * @param article
	 * @return
	 */
	public int insertArticle(BoardDataBean article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		// 테이블에 들어갈 글 번호
		int number = 0;
		String sql = "";
		
		// 제목 글의 글 번호
		int num = article.getNum();
		// 제목 글의 그룹화 아이디
		int ref = article.getRef();
		// 그룹 내의 글의 순서
		int reStep = article.getReStep();
		// 글 제목의 들여쓰기
		int reLevel = article.getReLevel();
		
		try {
			conn = getConnection();
			// board 테이블에 레코드의 유무 판단과 글 번호 설정
			pstmt = conn.prepareStatement("select max(num) from board");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// 다음 글 번호는 가장 큰 글 번호 + 1
				number = rs.getInt(1) + 1;
			} else {
				// 첫번째 글
				number = 1;
			}
			
			// 제목글과 댓글 간의 순서 결정
			if (num != 0) {
				// 댓글 - 제목글의 글번호 가짐
				sql = "update board set re_step = re_step + 1 where ref=? and re_step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, reStep);
				pstmt.executeUpdate();
				reStep = reStep + 1;
				reLevel = reLevel + 1;
			} else {
				// 제목글 - 글번호 없음
				ref = number;
				reStep = 0;
				reLevel = 0;
			}
			
			// board 테이블에 새로운 레코드 추가
			sql = "insert into board(writer, subject, content, passwd, reg_date, ";
			sql += "ip, ref, re_step, re_level) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getContent());
			pstmt.setString(4, article.getPasswd());
			pstmt.setTimestamp(5, article.getRegDate());
			pstmt.setString(6, article.getIp());
			pstmt.setInt(7, ref);
			pstmt.setInt(8, reStep);
			pstmt.setInt(9, reLevel);
			pstmt.executeUpdate();
			// 레코드 추가 성공
			x = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException ex) { }
			if (pstmt != null) try { pstmt.close(); } catch (SQLException ex) { }
			if (conn != null) try { conn.close(); } catch (SQLException ex) { }
		}
		
		return x;
	}
	
	/**
	 * board 테이블에 저장된 전체글의 수를 얻어냄
	 * @return
	 */
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException ex) { }
			if (pstmt != null) try { pstmt.close(); } catch (SQLException ex) { }
			if (conn != null) try { conn.close(); } catch (SQLException ex) { }
		}
		
		return x;
	}
	
	/**
	 * 글의 목록을 가져옴.
	 * @param start
	 * @param end
	 * @return
	 */
	public List<BoardDataBean> getArticles(int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDataBean> articleList = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select * from board order by ref desc, re_step asc limit ?,?");
			pstmt.setInt(1, start -1);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				articleList = new ArrayList<BoardDataBean>(end);
				do {
					BoardDataBean article = new BoardDataBean();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setContent(rs.getString("content"));
					article.setPasswd(rs.getString("passwd"));
					article.setRegDate(rs.getTimestamp("reg_date"));
					article.setReadCount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setReStep(rs.getInt("re_step"));
					article.setReLevel(rs.getInt("re_level"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					
					articleList.add(article);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException ex) { }
			if (pstmt != null) try { pstmt.close(); } catch (SQLException ex) { }
			if (conn != null) try { conn.close(); } catch (SQLException ex) { }
		}
		
		return articleList;
	}
	
	/**
	 * 글 수정 폼에서 사용할 글의 내용 (1개의 글)
	 * @param num
	 * @return
	 */
	public BoardDataBean updateGetArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean article = null;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				article = new BoardDataBean();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setContent(rs.getString("content"));
				article.setPasswd(rs.getString("passwd"));
				article.setRegDate(rs.getTimestamp("reg_date"));
				article.setReadCount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setReStep(rs.getInt("re_step"));
				article.setReLevel(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException ex) { }
			if (pstmt != null) try { pstmt.close(); } catch (SQLException ex) { }
			if (conn != null) try { conn.close(); } catch (SQLException ex) { }
		}
		
		return article;
	}
	
	/**
	 * 글 수정 처리에서 사용
	 * @param article
	 * @return
	 */
	public int updateArticle(BoardDataBean article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select passwd from board where num = ?");
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String dbpasswd = rs.getString("passwd");
				if (dbpasswd.equals(article.getPasswd())) {
					String sql = "update board set subject=?, ";
					sql += "content=? where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, article.getSubject());
					pstmt.setString(2, article.getContent());
					pstmt.setInt(3, article.getNum());
					pstmt.executeUpdate();
					x = 1;
				} else {
					x = 0;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException ex) { }
			if (pstmt != null) try { pstmt.close(); } catch (SQLException ex) { }
			if (conn != null) try { conn.close(); } catch (SQLException ex) { }
		}
		
		return x;
	}
	
	/**
	 * 글 삭제 처리 시 사용
	 * @param num
	 * @param passwd
	 * @return
	 */
	public int deleteArticle(int num, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select passwd from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String dbpasswd = rs.getString("passwd");
				if (dbpasswd.equals(passwd)) {
					pstmt = conn.prepareStatement("delete from board where num=?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					// 글 삭제 성공
					x = 1;
				} else {
					// 비밀번호 틀림
					x = 0;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException ex) { }
			if (pstmt != null) try { pstmt.close(); } catch (SQLException ex) { }
			if (conn != null) try { conn.close(); } catch (SQLException ex) { }
		}
		
		return x;
	}
	
}
