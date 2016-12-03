<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<% request.setCharacterEncoding("utf-8");%>

<jsp:useBean id="testBean" class="ch08.ex.ExTestBean">
   <jsp:setProperty name="testBean" property="*" />
</jsp:useBean>

이름: <jsp:getProperty name="testBean" property="name" /><br>
이메일: <jsp:getProperty name="testBean" property="email" /><br>
방문소감: <jsp:getProperty name="testBean" property="comment" /><br>