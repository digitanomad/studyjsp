<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>ex07-02</title>

<script src="../../js/jquery-1.11.0.min.js"></script>
<script>
	$(document).ready(function(){
		$("#pro").click(function(){
			$("#display").load("ex07-01.jsp");
		});
	});
</script>
</head>
<body>
  <button id="pro">로드</button>
  <div id="display"></div>
</body>
</html>