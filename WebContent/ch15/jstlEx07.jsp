<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>

<h3>JSTL fmt 태그예제 - bundle, message</h3>
<%--<fmt:setLocale value="en"/> --%>
<fmt:bundle basename="ch15.bundle.testBundle">
	<fmt:message key="name" />
	<fmt:message key="message" var="msg" />
	<p><c:out value="${msg}" />
</fmt:bundle>