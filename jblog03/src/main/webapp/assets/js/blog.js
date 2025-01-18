/* 카테고리 추가 */
$(function(){
	$("#btn-addCategory").click(function() {
		var name = $("#category-name").val().trim();
		var description = $("#category-description").val().trim();
		if(name == "") {
			alert("카테고리명을 입력해주세요.");
			return;
		}
		
		$.ajax({
			url: `${contextPath}/${blogId}/admin/category`,
			type: "post",
			contentType: "application/json",
			dataType: "json",
			// requestBody로 보낼 데이터
			data: JSON.stringify({
			    name: name,
			    description: description
			}),
			success: function(response) {
				if(response.result != "success") {
					console.error(response.message);
					return;
				}
				
				alert("[" + name + "]" + " 카테고리 등록이 완료되었습니다.");
				$("#category-name").val("");
				$("#category-description").val("");
				
				// 목록 다시 불러오기
				window.location.href = `${contextPath}/${blogId}/admin/category`;
				return;
			},
			error: function(xhr, status, err) {
				console.error(err);
			}
		});
	});
});

/* 카테고리 삭제 */
$(function(){
	$(".btn-deleteCategory").click(function() {
		// data-* 속성값 가져오기
		var categoryId = $(this).data("id");
		var name = $(this).data("name");
		console.log(categoryId + " " + name);
		
		$.ajax({
			url: `${contextPath}/${blogId}/admin/category?categoryId=${categoryId}`,
			type: "delete",
			dataType: "json",
			success: function(response) {
				if(response.result != "success") {
					console.error(response.message);
					return;
				}
				
				alert("[" + name + "]" + " 카테고리 삭제가 완료되었습니다.");

				// 목록 다시 불러오기
				window.location.href = `${contextPath}/${blogId}/admin/category`;
				return;
			},
			error: function(xhr, status, err) {
				console.error(err);
			}
		});
	});
});