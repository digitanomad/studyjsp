package ch08.register;

import java.sql.Timestamp;

public class RegisterBean {
	// ������Ƽ
	private String idt;	// ���̵�
	private String passwd;	// ��й�ȣ
	private String name;	// �̸�
	private Timestamp regDate;	// ������
	
	public String getIdt() {
		return idt;
	}
	public void setIdt(String idt) {
		this.idt = idt;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
}
