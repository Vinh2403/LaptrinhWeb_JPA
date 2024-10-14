<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<a href="${pageContext.request.contextPath}/admin/category/add"> Add Category</a>		<!-- Them 1 cate moi -->
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Images</th>
		<th>CategoryID</th>
		<th>CategoryName</th>
		<th>Status</th>
		<th>Action</th>
		
		<!-- dung de update/delete -->
	</tr>
	<c:forEach items="${listcate}" var="cate" varStatus="STT">
		<tr>
			<td>${STT.index+1}</td>
			
			<td>
			<c:if test="${cate.images.substring(0, 5) !='https'}"> 
				<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
			</c:if>
			<c:if test="${cate.images.substring(0, 5) =='https'}"> 
				<c:url value="${cate.images}" var="imgUrl"></c:url>
			</c:if>
		
			
			<img height="150" width="200" src="${imgUrl}"/>
			</td>
			
			
			<td>${cate.categoryId }</td>
			<td>${cate.categoryname }</td>
			
			<td>
			<c:if test="${cate.status ==1 }">
			<span>Còn hoạt động</span>	
			</c:if>
			<c:if test="${cate.status !=1 }">
			<span>Ngưng kinh doanh</span>	
			</c:if>
			</td>
			<td><a
				href="<c:url value='/admin/category/edit?id=${cate.categoryId}'/>">Sửa</a> | <a
				href="<c:url value='/admin/category/delete?id=${cate.categoryId}'/>">Xóa</a></td>
		</tr>
	</c:forEach>
</table>