package ch12.board;

import java.sql.Timestamp;

public class BoardDataBean {
	// �۹�ȣ
	private int num;
	// �۾���
    private String writer;
    // ������
    private String subject;
    // �۳���
    private String content;
    // �� ��й�ȣ
    private String passwd;
    // �۾� ��¥
    private Timestamp regDate;
    // ��ȸ��
    private int readCount;
    // �۾��� IP
    private String ip;
    // �� �׷�ȭ ��ȣ
    private int ref;
    // �׷� ���� ����
    private int reStep;
    // ������ �鿩����
    private int reLevel;
    
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getReStep() {
		return reStep;
	}
	public void setReStep(int reStep) {
		this.reStep = reStep;
	}
	public int getReLevel() {
		return reLevel;
	}
	public void setReLevel(int reLevel) {
		this.reLevel = reLevel;
	}
}
