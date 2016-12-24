<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<% session.invalidate(); %>

<script>
    alert("로그아웃 되었습니다.");
	location.href="sessionLogin.jsp";
</script>