<%@ tag body-content="scriptless" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ variable name-given="x" scope="AT_END" %>

<c:set var="x" value="2"/>
<jsp:doBody/>
<c:set var="x" value="4"/>