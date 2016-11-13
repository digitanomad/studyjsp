<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>jQuery Ajax메소드 - $.post()</title>
<style type="text/css">
  #result{
    width  : 200px;
    height : 200px;
    border : 5px double #6699FF;
  }
</style>
<script src="../js/jquery-1.11.0.min.js"></script>
<script>
	$(document).ready(function(){
		// [결과]버튼을 클릭하면 자동실행
		$("#b1").click(function(){
			// process.jsp페이지에 요청데이터를 보낸 후 결과를 반환받음
			$.post("process.jsp", {
				name:"kingdora",
				stus:"homebody"
			},
			// 응답내용 처리
			function(data, status){
				if (status == "success")
					$("#result").html(data);
			});
		});
	});
</script>
<body>
  <button id="b1">결과</button>
  <div id="result"></div>
</body>
</html>