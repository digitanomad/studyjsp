<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<%
	int bufferSize = out.getBufferSize(); // 버퍼크기 얻어냄
	int remainSize = out.getRemaining();	// 남은 버퍼의 크기 얻어냄
	int usedSize = bufferSize - remainSize;	// 사용 가능한 버퍼크기 얻어냄
%>

<h2>out 객체 예제 - getBufferSize(), getRemaining(), println()메소드 사용</h2>
<b>현재 페이지의 버퍼 사용현황</b><br>
출력 버퍼의 전체 크기 : <%=bufferSize%>byte<br>
현재 사용한 버퍼의 크기 : <%=usedSize%>byte<br>
남은 버퍼의 크기 : <% out.println(remainSize);%>byte