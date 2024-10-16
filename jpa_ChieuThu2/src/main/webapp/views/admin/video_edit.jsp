<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<form action="${pageContext.request.contextPath}/admin/video/update"
	method="post" enctype="multipart/form-data">
	
	<input
		type="text" id="videoid" name="videoid"
		value="${vid.videoId}"><br>
		
	<label for="videoname">Video Title:</label>
	<input
		type="text" id="videotitle" name="videotitle"
		value="${vid.title}"><br> 
		
	<label for="images">Poster:</label><br>
	<c:if test="${vid.poster.substring(0, 5) !='https'}">
		<c:url value="/image?fname=${vid.poster}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${vid.poster.substring(0, 5) =='https'}">
		<c:url value="${vid.poster}" var="imgUrl"></c:url>
	</c:if>


	<img height="150" width="200" src="${imgUrl}" /> 	<!-- hien thi anh co san tu database trong dot upload lan truoc -->
	<input type="file" id="images" name="images" value="${vid.poster}"><br> 
		
		 <div>
		  <!-- Nếu vid.active == 1 thì radio button "active" sẽ được chọn -->
	        <label for="status">Status:</label>
	        
	        <input type="radio" id="active" name="status" value="active" <c:if test="${vid.active == 1}">checked</c:if>>
 			<label for="active">Hoạt động</label>
	        <input type="radio" id="inactive" name="status" value="inactive" <c:if test="${vid.active != 1}">checked</c:if> >
	        <label for="inactive">Khóa</label>
  	  	</div>
  	  	
		<div>
        <label for="Description">Description:</label>
        <textarea id="Description" name="Description" value="${vid.description}"></textarea>
    </div>
    
    <div>
 		<label for="ViewCount"> View Count</label>
 		<input type="text" id="ViewCount" name="ViewCount" value="${vid.views}">
 	</div>
 	
 	<div>
        <label for="Category">Category:</label>
        <select id="Category" name="Category" >
            <c:forEach var="category" items="${listcate}"  >
                <option value="${category.categoryId}"  <c:if test="${category.categoryId == vid.category.categoryId}">selected</c:if> >
						${category.categoryname}
            	</option>
            </c:forEach>
        </select>
    </div>
		<input type="submit" value="Submit">
</form>