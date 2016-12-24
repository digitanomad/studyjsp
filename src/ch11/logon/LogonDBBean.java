package ch11.logon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import work.crypt.BCrypt;
import work.crypt.SHA256;

public class LogonDBBean {
	
	// LogonDBBean ���� ��ü ����
	// �� ���� ��ü�� �����ؼ� ����
	private static LogonDBBean instance = new LogonDBBean();
	
	// LogonDBBean ��ü�� �����ϴ� �޼ҵ�
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	private LogonDBBean() { }
	
	/**
	 * Ŀ�ؼ� Ǯ���� Ŀ�ؼ� ��ü�� ���� �޼ҵ�
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
	 * ȸ�� ���� ó��(registerPro.jsp)���� ����ϴ� �� ���ڵ� �߰� �޼ҵ�
	 * @param member
	 */
	public void insertMember(LogonDataBean member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		SHA256 sha = SHA256.getInsatnce();
		
		try {
			conn = getConnection();
			
			String orgPass = member.getPasswd();
			String shaPass = sha.getSha256(orgPass.getBytes());
			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
			
			pstmt = conn.prepareStatement("insert into member values (?,?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, bcPass);
			pstmt.setString(3, member.getName());
			pstmt.setTimestamp(4, member.getReg_date());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getTel());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException ex) { }
			}
			if (conn != null) {
				try { conn.close(); } catch(SQLException ex) { }
			}
		}
		
	}
	
	/**
	 * �α��� �� ó��(loginPro.jsp) �������� ����� ���� ó�� ��
	 * ȸ�� ���� ����/Ż�� ����� ����(memberCheck.jsp)���� ����ϴ� �޼ҵ�
	 * @param id
	 * @param passwd
	 * @return
	 */
	public int userCheck(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = getConnection();
			
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			pstmt = conn.prepareStatement("select passwd from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String dbPasswd = rs.getString("passwd");
				if (BCrypt.checkpw(shaPass, dbPasswd)) {
					// ���� ����
					x = 1;
				} else {
					// ��й�ȣ Ʋ��
					x = 0;
				}
			} else {
				// ���̵� ����
				x = -1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException ex) { }
			}
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException ex) { }
			}
			if (conn != null) {
				try { conn.close(); } catch(SQLException ex) { }
			}
		}
		
		return x;
	}
	
	
	/**
	 * ���̵� �ߺ� Ȯ��(confirmId.jsp)���� ���̵��� �ߺ� ���θ� Ȯ���ϴ� �޼ҵ�
	 * @param id
	 * @return
	 */
	public int confirmId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select id from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// ���� ���̵� ����
				x = 1;
			} else {
				// ���� ���̵� ����
				x = -1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException ex) { }
			}
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException ex) { }
			}
			if (conn != null) {
				try { conn.close(); } catch(SQLException ex) { }
			}
		}
		
		return x;
	}
	
	/**
	 * ȸ�� ���� ���� ��(modifyForm.jsp)�� ���� ���� ���� ������ �������� �޼ҵ�
	 * @param id
	 * @param passwd
	 * @return
	 */
	public LogonDataBean getMember(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogonDataBean member = null;
		
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = getConnection();
			
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			pstmt = conn.prepareStatement("select * from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String dbPasswd = rs.getString("passwd");
				// ����ڰ� �Է��� ��й�ȣ�� ���̺��� ��й�ȣ�� ������ ����
				if (BCrypt.checkpw(shaPass, dbPasswd)) {
					member = new LogonDataBean();
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setReg_date(rs.getTimestamp("reg_date"));
					member.setAddress(rs.getString("address"));
					member.setTel(rs.getString("tel"));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException ex) { }
			}
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException ex) { }
			}
			if (conn != null) {
				try { conn.close(); } catch(SQLException ex) { }
			}
		}
		
		return member;
	}
	
	/**
	 * ȸ�� ���� ���� ó��(modifyPro.jsp)���� ȸ�� ���� ������ ó���ϴ� �޼ҵ�
	 * @param member
	 * @return
	 */
	public int updateMember(LogonDataBean member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = getConnection();
			
			String orgPass = member.getPasswd();
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			pstmt = conn.prepareStatement("select passwd from member where id = ?");
			pstmt.setString(1, member.getId());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String dbPasswd = rs.getString("passwd");
				// ����ڰ� �Է��� ��й�ȣ�� ���̺��� ��й�ȣ�� ������ ����
				if (BCrypt.checkpw(shaPass, dbPasswd)) {
					pstmt = conn.prepareStatement("update member set name=?, address=?, tel=?"
							+ "where id=?");
					pstmt.setString(1, member.getName());
					pstmt.setString(2, member.getAddress());
					pstmt.setString(3, member.getTel());
					pstmt.setString(4, member.getId());
					pstmt.executeUpdate();
					// ȸ�� ���� ���� ó�� ����
					x = 1;
				} else {
					// ȸ�� ���� ���� ó�� ����
					x = 0;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException ex) { }
			}
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException ex) { }
			}
			if (conn != null) {
				try { conn.close(); } catch(SQLException ex) { }
			}
		}
		
		return x;
	}
	
	/**
	 * ȸ�� Ż�� ó�� (deletePro.jsp)���� ȸ�� ������ �����ϴ� �޼ҵ�
	 * @param id
	 * @param passwd
	 * @return
	 */
	public int deleteMember(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = getConnection();
			
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());
			
			pstmt = conn.prepareStatement("select passwd from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String dbPasswd = rs.getString("passwd");
				// ����ڰ� �Է��� ��й�ȣ�� ���̺��� ��й�ȣ�� ������ ����
				if (BCrypt.checkpw(shaPass, dbPasswd)) {
					pstmt = conn.prepareStatement("delete from member where id = ?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					// ȸ�� Ż�� ó�� ����
					x = 1;
				} else {
					// ȸ�� Ż�� ó�� ����
					x = 0;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException ex) { }
			}
			if (pstmt != null) {
				try { pstmt.close(); } catch (SQLException ex) { }
			}
			if (conn != null) {
				try { conn.close(); } catch(SQLException ex) { }
			}
		}
		
		return x;
	}
	
}
