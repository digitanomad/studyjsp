var status = true;

$(document).ready(function() {
	// modify.jsp 페이지의 [정보수정]버튼을 클릭하면 실행
	// 입력한 비밀번호를 갖고 memberCheck.jsp 페이지 실행
	$("#modify").click(function() {
		var query = {passwd:$("#passwd").val()};
		
		$.ajax({
			type: "post",
			url: "memberCheck.jsp",
			data: query,
			success: function(data) {
				if (data == 1) {
					// 비밀번호가 맞음
					$("#main_auth").load("modifyForm.jsp?passwd=" + $("#passwd").val());
				} else {
					// 비밀번호가 틀림
					alert("비밀번호가 맞지 않습니다.");
					$("#passwd").val("");
					$("#passwd").focus();
				}
			}
		});
	});
	
	// modifyForm.jsp 페이지의 [수정]버튼을 클릭하면 실행
	// 수정폼에 입력한 값을 갖고 modifyPro.jsp 실행
	$("#modifyProcess").click(function() {
		var query = {id:$("#id").val(), 
				  passwd:$("#passwd").val(),
			      name:$("#name").val(),
			      address:$("#address").val(),
			      tel:$("#tel").val()};
		
		$.ajax({
			type: "post",
			url: "modifyPro.jsp",
			data: query,
			success: function(data) {
				if (data == 1) {
					// 정보수정 성공
					alert("회원정보가 수정되었습니다.");
					window.location.href("main.jsp");
				}
			}
		});
	});
	
	// modifyForm.jsp 페이지의 [취소]버튼 클릭 시 실행
	$("#cancel").click(function() {
		window.location.href("main.jsp");
	});
	
	// modify.jsp 페이지의 [탈퇴]버튼을 클릭하면 자동실행
	// 입력한 비밀번호를 갖고 memberCheck.jsp 실행 후 비밀번호가 맞으면 deletePro.jsp 페이지 실행
	$("#delete").click(function() {
		var query = {passwd:$("#passwd").val()};
		
		// 입력한 비밀번호를 갖고 memberCheck.jsp 페이지 실행
		$.ajax({
			type: "post",
			url: "memberCheck.jsp",
			data: query,
			success: function(data) {
				if (data == 1) {
					// 비밀번호 맞음
					$.ajax({
						type: "post",
						url: "deletePro.jsp",
						data: query,
						success: function(data) {
							if (data == 1) {
								// 탈퇴 성공
								alert("회원 탈퇴가 되었습니다.");
								$("#main_auth").load("loginForm.jsp");
							}
						}
					});
				} else {
					// 비밀번호 틀림
					alert("비밀번호가 맞지 않습니다.");
					$("#passwd").val("");
					$("#passwd").focus();
				}
			}
		});
	});
	
});