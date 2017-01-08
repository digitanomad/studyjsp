package ch12.board;

import java.sql.Timestamp;

public class BoardDataBean {
	// 글번호
	private int num;
	// 글쓴이
    private String writer;
    // 글제목
    private String subject;
    // 글내용
    private String content;
    // 글 비밀번호
    private String passwd;
    // 글쓴 날짜
    private Timestamp regDate;
    // 조회수
    private int readCount;
    // 글쓴이 IP
    private String ip;
    // 글 그룹화 번호
    private int ref;
    // 그룹 내의 순서
    private int reStep;
    // 글제목 들여쓰기
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
