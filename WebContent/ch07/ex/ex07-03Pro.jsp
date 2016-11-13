<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8");%>

<%
  String resultStr = "";
  String name = request.getParameter("name");
  
  if(name.equals("김개동"))
     resultStr = "관리자님 어서오세요";
  else
	  resultStr = "회원님 어서오세요"; 
  
  out.println(resultStr);
%>