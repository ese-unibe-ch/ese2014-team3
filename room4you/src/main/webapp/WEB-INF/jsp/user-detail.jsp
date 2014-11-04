<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>



<div class="container-fluid">

	<div class="row">
	  <div class="col-md-12">
		<h1>About <c:out value="${user.name}" /></h1>
			
			<form class="form-horizontal" role="form">
			  <div class="form-group">
			    <label for="availableFrom" class="col-sm-4">About me</label>
			    <div class="col-sm-8">
			      ${user.aboutMe}
			    </div>
			  </div>	  
			</form>
		</div> <!-- end col-md-12 -->
		



	<div id="adImages" style="display:none">
	
		<c:forEach items="${ad.images}" var="image">
			<img src="data:image/jpeg;base64,${image.imageAsString}" alt="image" class="img-thumbnail">
	
		</c:forEach>
	
	</div>




	</div>
</div>

