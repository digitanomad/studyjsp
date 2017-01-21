<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>

<fmt:requestEncoding value="utf-8" />

<h3>JSTL fmt 태그예제 - requestEncoding</h3>
<p>파라메터:<c:out value="${param.name}"/>
<form action="jstlEx06.jsp" method="post">
	<ul>
		<li><label for="name">이름</label>
        <input type="text" id="name" name="name">
        <input type="submit" value="확인">
	</ul>
</form>