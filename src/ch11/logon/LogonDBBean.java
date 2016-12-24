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
	
	// LogonDBBean 전역 객체 생성
	// 한 개의 객체만 생성해서 공유
	private static LogonDBBean instance = new LogonDBBean();
	
	// LogonDBBean 객체를 리턴하는 메소드
	public static LogonDBBean getInstance() {
		return instance;
	}
	
	private LogonDBBean() { }
	
	/**
	 * 커넥션 풀에서 커넥션 객체를 얻어내는 메소드
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
	 * 회원 가입 처리(registerPro.jsp)에서 사용하는 새 레코드 추가 메소드
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
	 * 로그인 폼 처리(loginPro.jsp) 페이지의 사용자 인증 처리 및
	 * 회원 정보 수정/탈퇴를 사용자 인증(memberCheck.jsp)에서 사용하는 메소드
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
					// 인증 성공
					x = 1;
				} else {
					// 비밀번호 틀림
					x = 0;
				}
			} else {
				// 아이디 없음
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
	 * 아이디 중복 확인(confirmId.jsp)에서 아이디의 중복 여부를 확인하는 메소드
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
				// 같은 아이디 있음
				x = 1;
			} else {
				// 같은 아이디 없음
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
	 * 회원 정보 수정 폼(modifyForm.jsp)을 위한 기존 가입 정보를 가져오는 메소드
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
				// 사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
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
	 * 회원 정보 수정 처리(modifyPro.jsp)에서 회원 정보 수정을 처리하는 메소드
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
				// 사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
				if (BCrypt.checkpw(shaPass, dbPasswd)) {
					pstmt = conn.prepareStatement("update member set name=?, address=?, tel=?"
							+ "where id=?");
					pstmt.setString(1, member.getName());
					pstmt.setString(2, member.getAddress());
					pstmt.setString(3, member.getTel());
					pstmt.setString(4, member.getId());
					pstmt.executeUpdate();
					// 회원 정보 수정 처리 성공
					x = 1;
				} else {
					// 회원 정보 수정 처리 실패
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
	 * 회원 탈퇴 처리 (deletePro.jsp)에서 회원 정보를 삭제하는 메소드
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
				// 사용자가 입력한 비밀번호와 테이블의 비밀번호가 같으면 수행
				if (BCrypt.checkpw(shaPass, dbPasswd)) {
					pstmt = conn.prepareStatement("delete from member where id = ?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					// 회원 탈퇴 처리 성공
					x = 1;
				} else {
					// 회원 탈퇴 처리 실패
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
