<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="write.js"></script>

<% request.setCharacterEncoding("utf-8");%>
<%
	// 제목글의 경우 갖는 값
	int num = 0, ref = 1, reStep = 0, reLevel = 0;
	int pageNum = 1;
	
	try {
		// 댓글의 경우 갖는 값
		if (request.getParameter("num") != null) {
			// 제목글의 글번호, 그룹화번호, 그룹화내의 순서, 들여쓰기 정도가 list.jsp에서 넘어옴
			num = Integer.parseInt(request.getParameter("num"));
			ref = Integer.parseInt(request.getParameter("ref"));
			reStep = Integer.parseInt(request.getParameter("reStep"));
			reLevel = Integer.parseInt(request.getParameter("reLevel"));
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
%>
<input type="hidden" id="num" value="<%= num %>">
<input type="hidden" id="ref" value="<%= ref %>">
<input type="hidden" id="re_step" value="<%= reStep %>">
<input type="hidden" id="re_level" value="<%= reLevel %>">

<div id="writeForm" class="box">
	<ul>
		<li><label for="subject">제목</label>
			<% if (num != 0) { // 댓글 %>
				<img src="../images/re.gif">
			<% } %>
				<input id="subject" name="subject" type="text"
					size="50" placeholder="제목" maxlength="50">
		<li><label for="content">내용</label>
			<textarea id="content" rows="13" cols="50"></textarea>
		<li><label for="passwd">비밀번호</label>
			<input id="passwd" name="passwd" type="password"
				size="20" placeholder="6~16자 숫자/문자" maxlength="16">
		<li class="label2">
			<button id="regist" value="<%= pageNum %>">등록</button>
			<button id="cancel" value="<%= pageNum %>">취소</button>
	</ul>
</div>
<% } catch (Exception e) { } %>