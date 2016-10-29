<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<%
	String id = (String)request.getAttribute("id");
	String name = (String)request.getAttribute("name");
%>

forwardTo.jsp 페이지입니다. <br>
아이디 : <b><%=id%></b><br>
패스워드 : <b><%=name%></b>