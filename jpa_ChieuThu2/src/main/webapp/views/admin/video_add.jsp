<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
	<h1>UPLOAD VIDEO</h1>
	<div>
		 <label for="images">Choose File:</label>
		 <img height="150" width="200" src =""/>
		 <input type="file" id="images" name="images">
	</div>
	 
	<div>
	  	<label for="VideoID">Video ID: </label>
	  	<input type="text" id="VideoID" name="VideoID" >
  	</div> 
  	
  	<div>
	  <label for="VideoTitle">Video Title: </label>
	  <input type="text" id="VideoTitle" name="VideoTitle" >
 	</div>
 	
 	<div>
 		<label for="ViewCount"> View Count</label>
 		<input type="text" id="ViewCount" name="ViewCount" value="0">
 	</div>
 	
 	<div>
        <label for="Category">Category:</label>
        <select id="Category" name="Category">
            <c:forEach var="category" items="${listcate}"  >
                <option value="${category.categoryId}">${category.categoryname}</option>
            </c:forEach>
        </select>
    </div>
    
 	<div>
        <label for="Description">Description:</label>
        <textarea id="Description" name="Description"></textarea>
    </div>
 	
    <div>
        <label for="status">Status:</label><br>
        <input type="radio" id="active" name="status" value="active">
        <label for="active">Hoạt động</label>
        <input type="radio" id="inactive" name="status" value="inactive">
        <label for="inactive">Khóa</label>
    </div>
  <input type="submit" value="Submit">
</form>