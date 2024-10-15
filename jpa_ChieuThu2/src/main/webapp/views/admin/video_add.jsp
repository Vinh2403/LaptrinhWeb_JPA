<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
	<h1>UPLOAD VIDEO</h1>
	<div><label for="images">Poster:</label>
		 <input type="file" id="images" name="images">
	</div>
	 
	 
  <label for="VideoID">Video ID: </label>
  <input type="text" id="VideoID" name="VideoID" ><br>
  
  <label for="VideoTitle">Video Title: </label>
  <input type="text" id="VideoTitle" name="VideoTitle" ><br>
 
 	
  <img height="150" width="200" src =""/>
  <input type="file" id="images" name="images" ><br>
    <label for="status">Status:</label><br>
  <input type="text" id="status" name="status" ><br>
  <br> <input type="submit" value="Submit">
</form>