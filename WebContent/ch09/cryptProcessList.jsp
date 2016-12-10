<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List" %>
<%@ page import = "ch09.update.UpdateDataBean" %>
<%@ page import = "ch09.update.UpdateDBBean" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="style.css"/>
 
<%
	List<UpdateDataBean> memberList = null;
	UpdateDBBean dbPro = UpdateDBBean.getInstance();
	memberList = dbPro.getMembers();
%>

<table>
	<tr class="label">
		<td>아이디
		<td>비밀번호
<%
	for (int i = 0; i < memberList.size(); i++) {
		UpdateDataBean member = (UpdateDataBean)memberList.get(i);
		String id = member.getId();
		String passwd = member.getPasswd();
%>
	<tr>
		<td><%= id %>
		<td><%= passwd %>
<%} %>
</table>