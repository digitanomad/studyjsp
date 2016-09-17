<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="color.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>디렉티브 예제</title>
</head>
<body bgcolor="<%=bodyback_c%>">
	<form action="">
		<dl>
		  <dd>
		  	<label for="id">아이디</label>
		  	<input id="id" type="email" placeholder="example@aaaa.com" required>
		  </dd>
		  <dd>
		  	<label for="pass">비밀번호</label>
		  	<input id="pass" type="password" placeholder="비밀번호" required>
		  </dd>
		  <dd>
		  	<label for="name">이름</label>
		  	<input id="name" type="text" placeholder="홍길동" required>
		  </dd>
		  <dd>
		  	<input type="submit" value="등록">
		  </dd>
		</dl>

	</form>
</body>
</html>