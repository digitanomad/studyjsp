<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<jsp:forward page="templateTest.jsp">
	<jsp:param value="content.jsp" name="CONTENTPAGE"/>
</jsp:forward>