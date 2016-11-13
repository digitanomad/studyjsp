<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>ex07-03</title>
<style type="text/css">
  #result{
    width  : 200px;
    height : 100px;
    border : 5px double #6699FF;
  }
</style>
<script src="../../js/jquery-1.11.0.min.js"></script>
<script>
	$(document).ready(function(){
		$("#pro").click(function(){
			var query = {name: $("#name").val()};
			$.ajax({
				type: "POST",
				url: "ex07-03Pro.jsp",
				data: query,
				success: function(data){
					$("#result").text(data);	
				}
			});
		});
	});
</script>
</head>
<body>
  <dl>
    <dd>
      <label for="name">이름</label>
      <input id="name" name="name" type="text"
                 placeholder="김개동" autofocus required>
    </dd>
    <dd>
      <button id="pro">처리</button>
    </dd>
  </dl>
  <div id="result"></div>
</body>
</html>