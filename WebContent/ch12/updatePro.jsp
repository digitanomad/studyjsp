<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "ch12.board.BoardDBBean" %>

<% request.setCharacterEncoding("utf-8");%>

<%-- BoardDataBean클래스의 객체 article을 생성 --%>
<jsp:useBean id="article" scope="page" class="ch12.board.BoardDataBean">
	<jsp:setProperty name="article" property="*" />
</jsp:useBean>

<%
	BoardDBBean dbPro = BoardDBBean.getInstance();
	// 글 수정 처리 후 결과를 check변수에 저장
	int check = dbPro.updateArticle(article);
	
	// 이 페이지를 호출한 update.js로 처리결과 값 check 반환
	out.println(check);
%>