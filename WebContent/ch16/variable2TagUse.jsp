<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tagFile" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>

<h3>태그 파일을 사용한 커스텀태그 작성 예제  - 속성이 있는 태그</h3>
<c:set var="x" value="1"/>
<p>variable태그 전 : x = ${x}
<tagFile:variable2>
  <p>variable태그 안 : x =  ${x}
</tagFile:variable2>
<p>variable태그 밖 : x = ${x}