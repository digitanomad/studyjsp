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
	
	// jsp ���������� DB �������� BoardDBBean Ŭ������ �޼ҵ忡 ���� �� �ʿ�
	public static BoardDBBean getInstance() {
		return instance;
	}
	
	private BoardDBBean() { }
	
	/**
	 * Ŀ�ؼ� Ǯ�κ��� Connection ��ü�� ��
	 * DB �������� �������� �����ϴ� �޼ҵ忡�� ���
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
	 * board ���̺� ���� �߰�
	 * @param article
	 * @return
	 */
	public int insertArticle(BoardDataBean article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		// ���̺� �� �� ��ȣ
		int number = 0;
		String sql = "";
		
		// ���� ���� �� ��ȣ
		int num = article.getNum();
		// ���� ���� �׷�ȭ ���̵�
		int ref = article.getRef();
		// �׷� ���� ���� ����
		int reStep = article.getReStep();
		// �� ������ �鿩����
		int reLevel = article.getReLevel();
		
		try {
			conn = getConnection();
			// board ���̺� ���ڵ��� ���� �Ǵܰ� �� ��ȣ ����
			pstmt = conn.prepareStatement("select max(num) from board");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// ���� �� ��ȣ�� ���� ū �� ��ȣ + 1
				number = rs.getInt(1) + 1;
			} else {
				// ù��° ��
				number = 1;
			}
			
			// ����۰� ��� ���� ���� ����
			if (num != 0) {
				// ��� - ������� �۹�ȣ ����
				sql = "update board set re_step = re_step + 1 where ref=? and re_step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, reStep);
				pstmt.executeUpdate();
				reStep = reStep + 1;
				reLevel = reLevel + 1;
			} else {
				// ����� - �۹�ȣ ����
				ref = number;
				reStep = 0;
				reLevel = 0;
			}
			
			// board ���̺� ���ο� ���ڵ� �߰�
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
			// ���ڵ� �߰� ����
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
	 * board ���̺� ����� ��ü���� ���� ��
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
	 * ���� ����� ������.
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
	 * �� ���� ������ ����� ���� ���� (1���� ��)
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
	 * �� ���� ó������ ���
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
	 * �� ���� ó�� �� ���
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
					// �� ���� ����
					x = 1;
				} else {
					// ��й�ȣ Ʋ��
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
