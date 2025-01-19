<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:if test="${not empty error && error == 'blogNotFound'}">
    <script>
        alert('존재하지 않는 블로그입니다.');
        window.location.href = "${pageContext.request.contextPath}/";
    </script>
</c:if>
<div id="header">
	<h1><a href="${pageContext.request.contextPath}/${blog.blogId}" style="color:#fff">${blog.title}</a></h1>
	<ul>
		<c:choose>
			<c:when test="${empty user}">
				<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
				<c:if test="${blog.blogId == user.id}">
					<li><a href="${pageContext.request.contextPath}/${user.id}/admin/basic">블로그 관리</a></li>
				</c:if>
			</c:otherwise>
		</c:choose>	
	</ul>
</div>