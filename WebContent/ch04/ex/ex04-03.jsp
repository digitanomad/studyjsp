<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>

<form method="get" action="#">
  <dl>
    <dd>
      <label for="var1">값1</label>
      <input id="var1" name="var1" type="text"
             placeholder="1" required>
    </dd>
    <dd>
      <label for="var2">값2</label>
      <input id="var2" name="var2" type="text"
             placeholder="1" required>
    </dd>
    <dd>
      <input type="submit" value="결과">
    </dd>
  </dl>
</form>
<%
  int var1 = Integer.parseInt(request.getParameter("var1"));
  int var2 = Integer.parseInt(request.getParameter("var2"));
  
  if( var1 + var2 >= 5)
	out.println("두 값의 합이 5이상");  
  else
	out.println("두 값의 합이 5미만"); 
%>