<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "ch12.board.BoardDBBean" %>
<%@ page import = "java.sql.Timestamp" %>

<% request.setCharacterEncoding("utf-8");%>

<%-- 글쓰기 폼에 입력한 값을 갖고 BoardDataBean 클래스 객체 article을 생성 --%>
<jsp:useBean id="article" scope="page" class="ch12.board.BoardDataBean">
	<jsp:setProperty name="article" property="*" />
</jsp:useBean>

<%
	String id = "";
	try {
		id = (String)session.getAttribute("id");	
	} catch (Exception e) { }
	
	// 폼으로부터 넘어오지 않는 값을 데이터 저장빈 BoardDataBean 객체 article에 직접 저장
	article.setWriter(id);
	article.setRegDate(new Timestamp(System.currentTimeMillis()));
	article.setIp(request.getRemoteAddr());
	
	// DB처리빈의 객체를 얻어냄
	BoardDBBean dbPro = BoardDBBean.getInstance();
	// DB처리빈 BoardDBBean 클래스의 insertArticle() 메소드를 호출해서 레코드 추가
	int check = dbPro.insertArticle(article);
	
	// 이 페이지를 호출한 write.js로 처리결과값 check를 반환
	out.println(check);
%>