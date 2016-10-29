<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page info = "page 내장객체 사용 예제" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
 
<% String info = this.getServletInfo();%>
<h2>page내장객체 예제</h2>
page디렉티브의 info속성값 " <%=info%> " 을 얻어낸다.