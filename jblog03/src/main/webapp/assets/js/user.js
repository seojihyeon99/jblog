$(function(){
	$("#btn-checkId").click(function() {
		var id = $("#blog-id").val();
		if(id == "") {
			return;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath}/user/check?id=" + id,
			type: "get",
			dataType: "json",
			success: function(response) {
				if(response.result != "success") {
					console.error(response.message);
					return;
				}
				
				if(response.data.exist) {
					alert("이미 존재하는 아이디입니다.");
					$("#blog-id").val("");
					$("#blog-id").focus();
					return;
				}
				
				$("#img-checkId").show();
				$("#btn-checkId").hide();
			},
			error: function(xhr, status, err) {
				console.error(err);
			}
		});
	});
});