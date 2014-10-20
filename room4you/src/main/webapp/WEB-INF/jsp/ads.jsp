<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>


<div class="row">
	<c:forEach items="${ads}" var="ad">
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <img data-src="holder.js/300x300" src="http://openphoto.net/volumes/sizes/taluda/21138/2.jpg"  alt="images/wohnung1.JPG" height="300" width="300">
		      <div class="caption">
		        <h3>${ad.title}</h3>
		        <p>${ad.description}</p>
		        <p><a href="<spring:url value="/ads/${ad.id}.html"/>" class="btn btn-primary" role="button">View Details</a></p>
		      </div>
		    </div>
		  </div>
	</c:forEach>
</div>