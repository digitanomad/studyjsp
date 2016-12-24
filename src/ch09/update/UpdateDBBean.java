package ch09.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import work.crypt.BCrypt;
import work.crypt.SHA256;

public class UpdateDBBean {
	private static UpdateDBBean instance = new UpdateDBBean();
	
	public static UpdateDBBean getInstance() {
		return instance;
	}
	
	private UpdateDBBean() { }
	
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsptest");
		return ds.getConnection();
	}
	
	// member ���̺��� ������ ����, cryptProcessList.jsp���� ���
	public List<UpdateDataBean> getMembers() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UpdateDataBean> memberList = null;
		int x = 0;
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select count(*) from member");
			rs = pstmt.executeQuery();
			
			if (rs.next())
				x = rs.getInt(1);
			
			pstmt = conn.prepareStatement("select id, passwd from member");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				memberList = new ArrayList<UpdateDataBean>(x);
				do {
					UpdateDataBean member = new UpdateDataBean();
					member.setId(rs.getString("id"));
					member.setPasswd(rs.getString("passwd"));
					memberList.add(member);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) 
				try { rs.close(); } catch (SQLException ex) { }
			if (pstmt != null)
				try { pstmt.close(); } catch (SQLException ex) { }
			if (conn != null)
				try { conn.close(); } catch (SQLException ex) { }
		}
		
		return memberList;
	}
	
	// member ���̺��� ��й�ȣ�� �ϰ������� ��ȣȭ�ؼ� �����ϸ�,
	// cryptProcess.jsp���� ���
	public void updateMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// SHA-256�� ����ϴ� SHA256 Ŭ������ ��ü�� ����.
		SHA256 sha = SHA256.getInsatnce();
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("select id, passwd from member");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String id = rs.getString("id");
				String orgPass = rs.getString("passwd");
				
				// SHA256 Ŭ������ getSha256() �޼ҵ带 �����
				// ������ ��й�ȣ�� SHA-256 ������� ��ȣȭ
				String shaPass = sha.getSha256(orgPass.getBytes());
				
				// SHA-256 ������� ��ȣȭ�� ���� �ٽ� BCyprt Ŭ������
				// hashpw() �޼ҵ带 ����ؼ� bcrypt ������� ��ȣȭ
				// BCyprt.getsalt() �޼ҵ带 salt ���� ������ ����� ����
				String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());
				
				pstmt = conn.prepareStatement("update member set passwd=? where id=?");
				pstmt.setString(1, bcPass);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) 
				try { rs.close(); } catch (SQLException ex) { }
			if (pstmt != null)
				try { pstmt.close(); } catch (SQLException ex) { }
			if (conn != null)
				try { conn.close(); } catch (SQLException ex) { }
		}
	}
}