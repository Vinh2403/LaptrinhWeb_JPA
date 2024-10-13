<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="${pageContext.request.contextPath}/admin/category/insert" method="post" enctype="multipart/form-data">
  <label for="category_name">Category Name:</label><br>
  <input type="text" id="category_name" name="category_name" ><br>
  
  <label for="images">Images:</label><br>
  <img height="150" width="200" src =""/>
  <input type="file" id="images" name="images" ><br>
    <label for="status">Status:</label><br>
  <input type="text" id="status" name="status" ><br>
  <br> <input type="submit" value="Submit">
</form>