<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>ex07-01</title>
<style type="text/css">
  #result{
    width  : 200px;
    height : 200px;
    border : 5px double #6699FF;
  }
</style>
<script src="../../js/jquery-1.11.0.min.js"></script>
<script>
	$(document).ready(function(){
		$("#pro").click(function(){
			var result = "아이디:" + $("#id").val();
			result += ", 비밀번호:" + $("#passwd").val();
			$("#result").text(result);
		});
	});
</script>
</head>
<body>
  <p>아이디와 비밀번호 입력</p>
  <dl>
    <dd>
      <label for="id">아이디</label>
      <input id="id" name="id" type="text"
                 placeholder="aaaa@aaa.com" autofocus required>
    </dd>
    <dd>
      <label for="passwd">비밀번호</label>
      <input id="passwd" name="passwd" type="password"
                 placeholder="비밀번호" required>
    </dd>
    <dd>
      <button id="pro">확인</button>
    </dd>
  </dl>
  <p>결과</p>
  <div id="result"></div>
</body>
</html>