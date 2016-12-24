<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "ch11.logon.LogonDBBean" %>

<% request.setCharacterEncoding("utf-8");%>

<%
	// 사용자가 입력한 아아디와 비밀번호
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	
	LogonDBBean manager = LogonDBBean.getInstance();
	int check = manager.userCheck(id, passwd);
	
	// 사용자 인증 성공 시 세션속성을 설정
	if (check == 1) {
		session.setAttribute("id", id);
	}
	
	out.println(check);
%>