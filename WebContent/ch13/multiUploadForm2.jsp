<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="../js/jquery.form.min.js"></script> 
<script src="../js/jquery.MetaData.js"></script> 
<script src="../js/jquery.MultiFile.js"></script> 
<script src="../js/jquery.blockUI.js"></script> 
<script src="multiUpload.js"></script>

<form id="upForm1" action="multiUploadPro.jsp" method="post" enctype="multipart/form-data">
	<div id="form">
		<ul>
			<li>
				<p class="cau">※ 최대 업로드 파일 수 : 5개</p>
				<input type="file" id="file1" name="file1" class="multi" maxlength="5">
			<li>
				<input type="submit" id="upPro1" value="다중 파일 업로드">
		</ul>
	</div>
</form>
<div id="upResult"></div>