<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<form:form action="searchAds.html" class="form-inline" role="form" method="post" >
    <div class="form-group">
    <label class="sr-only" for="searchTextZIP">City</label>
    <input type="text" class="form-control" id="searchTextZIP" placeholder="ZIP">
  </div> 
  <div class="form-group">
    <label class="sr-only" for="searchTextCity">City</label>
    <input type="text" class="form-control" id="searchTextCity" placeholder="City">
  </div>
  <div class="form-group">
    <label class="sr-only" for="searchTextMaxPrice">Max. price</label>
    <input type="text" class="form-control" id="searchTextMaxPrice" placeholder="Max. price">
  </div>
  

   <div class="checkbox">
    <label>
      <input type="checkbox"> Shared appartments
    </label>
  </div>

  <button  type="submit" class="btn btn-default">Search</button>
  <button type="button" class="btn btn-default">More criteria</button>
</form:form>
<hr>
<div class="row">
	<c:forEach items="${ads}" var="ad">
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <img data-src="holder.js/300x300" src="data:image/jpeg;base64,${ad.images[0].imageAsString}"  alt="images/wohnung.JPG" height="300" width="300">
		      <div class="caption">
		        <h3>${ad.title}</h3>
		        <p>${ad.description}</p>
		        <p><a href="<spring:url value="/ads/${ad.id}.html"/>" class="btn btn-primary" role="button">View Details</a></p>
		      </div>
		    </div>
		  </div>
	</c:forEach>
	

	
</div>