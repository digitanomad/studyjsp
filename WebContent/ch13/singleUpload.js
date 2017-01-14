$(document).ready(function() {
	// Ajax 방식으로 요청페이지를 호출해 파일을 업로드한다.
	$("#upForm1").ajaxForm({
		success: function(data, status) {
			$("#upResult").html(data);
		}
	});
})