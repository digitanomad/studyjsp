<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<%
	String id = "kingdora@kings.com";
	String passwd = "123456";
	
	session.setAttribute("id", id);
	session.setAttribute("passwd", passwd);
%>

<form method="post" action="viewSession.jsp">
   <table>
    <tr>
      <td><input type="submit" value="세션 속성 확인">
  </table>
</form>