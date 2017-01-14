<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="../js/jquery.form.min.js"></script> 
<script src="multiUpload.js"></script>

<form id="upForm1" action="multiUploadPro.jsp" method="post" enctype="multipart/form-data">
	<div id="form">
		<ul>
			<li>
				<p class="cau">※ 파일선택기에서 ctrl, shift키를 눌러 파일을 다중선택 하십시오.</p>
				<label for="file1">파일선택</label>
				<input type="file" id="file1" name="file1" multiple>
			<li>
				<input type="submit" id="upPro1" value="다중 파일 업로드">
		</ul>
	</div>
</form>
<div id="upResult"></div>