<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<% request.setCharacterEncoding("utf-8"); %>

<jsp:useBean id="testBean" class="ch08.bean.TestBean">
	<jsp:setProperty name="testBean" property="id"/>
</jsp:useBean>

입력된 아이디: <jsp:getProperty property="id" name="testBean" />