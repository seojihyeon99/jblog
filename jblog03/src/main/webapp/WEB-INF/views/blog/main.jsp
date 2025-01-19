<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog/header.jsp" />
		
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postDetail.title}</h4>
					<p>
						${fn:replace(postDetail.contents, newLine, "<br>")}
					<p>
				</div>
				<ul class="blog-list">
					<c:if test="${empty posts}">
						<h2>글 목록이 존재하지 않습니다.</h2>
					</c:if>
					<c:if test="${not empty posts}">
						<c:forEach items="${posts}" var="post">
							<li><a href="${pageContext.request.contextPath}/${blog.blogId}/${post.categoryId}/${post.id}">${post.title}</a><span>${post.regDate}</span></li>				
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blog.profile}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categories}" var="category">
					<li><a href="${pageContext.request.contextPath}/${blog.blogId}/${category.id}">${category.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/includes/blog/footer.jsp" />
	</div>
</body>
</html>