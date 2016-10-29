<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<% if (session.getAttribute("id") == null) { // 세션이 설정된 경우 %>
<form method="post" action="sessionTest.jsp">
	<div id="unauth">
		<dl>
			<dd>
				<label for="id">아이디</label>
				<input id="id" name="id" type="text"
					placeholder="kingdora" autofocus="autofocus" required="required">
			</dd>
			<dd>
				<label for="pass">비밀번호</label>
				<input id="pass" name="pass" type="password" 
		            placeholder="1234" required>
			</dd>
			<dd>
				<input type="submit" value="로그인">
			</dd>
		</dl>
	</div>
</form>		
<% } else {	// 세션이 설정되지 않은 경우 %>
<form method="post" action="logout.jsp">
  <div id="auth">
  <dl>
    <dd>
      <%=session.getAttribute("id")%>님 오셨구려..
    </dd>
    <dd>
      <input type="submit" value="로그아웃">
    </dd>
  </dl>
  </div>
</form>
<% } %>