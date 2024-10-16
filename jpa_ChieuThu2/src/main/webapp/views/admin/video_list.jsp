<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<a href="${pageContext.request.contextPath}/admin/video/add"> Add Video</a>		<!-- Them 1 video moi -->
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Video ID</th>
		<th>Poster</th>
		<th>Video Title</th>
		<th>Description</th>
		<th>Views</th>
		<th>Category Name</th>
		<th>Active</th>
		<!-- dung de update/delete -->
	</tr>
	
	<c:forEach items="${listvid}" var="vid" varStatus="STT">
		<tr>
			<td>${STT.index+1}</td>
			
			<td>${vid.videoId }</td>
			
			<!-- Poster: -->
			<td>
			<c:if test="${vid.poster.substring(0, 5) !='https'}"> 
				<c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${vid.poster.substring(0, 5) =='https'}"> 
				<c:url value="${vid.poster}" var="imgUrl"></c:url>
			</c:if>
			
			
			<img height="150" width="200" src="${imgUrl}"/>
			</td>
			
			<!-- Title: -->
			<td>${vid.title}</td>
			<!-- Description -->
			<td>${vid.description}</td>
			
			<!-- Views: -->
			<td>${vid.views}</td>
			<!-- Category Name: -->
			<td>${vid.category.categoryname}</td>
			<!-- Active: -->
			<td>
			<c:if test="${vid.active == 1 }">
			<span>Còn hoạt động</span>	
			</c:if>
			<c:if test="${vid.active !=1 }">
			<span>Khóa</span>	
			</c:if>
			</td>
			<td><a
				href="<c:url value='/admin/video/edit?id=${vid.videoId}'/>">Sửa</a> | <a
				href="<c:url value='/admin/video/delete?id=${vid.videoId}'/>">Xóa</a></td>
		</tr>
	</c:forEach>
</table>