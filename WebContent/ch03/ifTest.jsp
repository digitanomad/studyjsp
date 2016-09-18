<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<%-- 폼으로부터 넘어온 데이터의 한글이 깨지지 않게 처리 --%>
<% 
	request.setCharacterEncoding("utf-8");
%>

<h2>if-else문 예제 - 좋아하는 색 선택</h2>

<%-- 입력한 값을 얻어내서 처리 --%>
<%
	// ifTestForm.jsp의 10,14라인의 파라미터변수 name과 color의 값을 얻어냄
	String name = request.getParameter("name");
	String color = request.getParameter("color");
	
	String selectColor = "";
	if ("blue".equals(color)) {
		selectColor = "파랑색";
	} else if ("green".equals(color)) {
		selectColor = "초록색";
	} else if ("red".equals(color)) {
		selectColor = "빨강색";
	} else {
		selectColor = "기타색";
	}
%>

<%-- 결과 출력 --%>
<%= name %>님이 선택한 색은 <%= selectColor %>입니다.<p>
선택한 색 : <br>
<img src="img/<%=color + ".jpg"%>" border="0">