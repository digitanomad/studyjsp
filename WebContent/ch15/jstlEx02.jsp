<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>

<h3>JSTL core 태그예제 - if, choose, when, otherwise</h3>
<c:set var="country" value="${'Korea'}" />
<c:if test="${country != null}">
	국가명: <c:out value="${country}" />
</c:if>

<c:choose>
	<c:when test="${country == 'Korea'}">
		<p><c:out value="${country}" />의 겨울은 춥다.
	</c:when>
	<c:when test="${country == 'Canada'}">
		<p><c:out value="${country }" />의 겨울은 너무 춥다.
	</c:when>
	<c:otherwise>
		<p>그 외의 나라들의 겨울은 알 수 없다.
	</c:otherwise>
</c:choose>