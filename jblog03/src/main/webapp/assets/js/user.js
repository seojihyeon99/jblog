$(function(){
	$("#btn-checkId").click(function() {
		var id = $("#blog-id").val();
		if(id == "") {
			return;
		}
		
		$.ajax({
			url: `${contextPath}/user/checkid?id=${id}`,
			type: "get",
			dataType: "json",
			success: function(response) {
				if(response.exist) {
					alert("이미 존재하는 아이디입니다.");
					$("#blog-id").val("");
					$("#blog-id").focus();
					return;
				}
				
				$("#img-checkId").show();
				$("#btn-checkId").hide();
				$("input[type='submit']").prop("disabled", false);
			},
			error: function(xhr, status, err) {
				console.error(err);
			}
		});
	});
	
	// id값 변경 시 이벤트
	$("#blog-id").on("input", function() {
		// 초기화
	    $("#btn-checkId").show();
	    $("#img-checkId").hide();
	    $("input[type='submit']").prop("disabled", true);
	});	
});