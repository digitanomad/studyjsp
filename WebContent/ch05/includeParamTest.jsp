<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<% request.setCharacterEncoding("utf-8");%>

<%
	String name = "김개동";
	String pageName = "includedParamTest.jsp";
%>
포함하는 페이지 includeParamTest.jsp 입니다.<br>
포함되는 페이지에 파라미터 값을 전달합니다.<br>
<hr>
<jsp:include page="<%= pageName %>" flush="false">
	<jsp:param name="name" value="<%= name %>"/>
	<jsp:param value="<%= pageName %>" name="pageName"/>
</jsp:include>
includeParamTest.jsp의 나머지 내용입니다.