<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
    const contextPath = "${pageContext.request.contextPath}";
    const blogId = "${user.id}";
</script>
<script src="${pageContext.request.contextPath}/assets/js/blog.js"></script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog/header.jsp" />
		
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog/admin-menu.jsp" />
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var="count" value="${fn:length(list)}"></c:set>
		      		<c:forEach items="${list}" var="item" varStatus="status">
						<tr>
							<td>${count-status.index}</td>
							<td>${item.name}</td>
							<td>1(수정 필요!!)</td>
							<td>${item.description}</td>
							<td><img class="btn-deleteCategory" src="${pageContext.request.contextPath}/assets/images/delete.jpg" data-id="${item.id}" data-name="${item.name}" ></td>
						</tr>  
		      		</c:forEach>	  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="category-name" type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="category-description" type="text" name="description"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="btn-addCategory" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/blog/footer.jsp" />
	</div>
</body>
</html>