<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<form:form action="searchAds.html" class="form-inline" role="form" method="post" >
    <div class="form-group">
    <label class="sr-only" for="searchTextZIP">ZIP</label>
    <input type="text" class="form-control" name="searchTextZip" id="searchTextZip" placeholder="ZIP">
  </div> 
  <div class="form-group">
    <label class="sr-only" for="searchTextCity">City</label>
    <input type="text" class="form-control" name="searchTextCity" id="searchTextCity" placeholder="City">
  </div>
  <div class="form-group">
    <label class="sr-only" for="searchTextMaxPrice">Max. price</label>
    <input type="text" class="form-control" name="searchTextMaxPrice" id="searchTextMaxPrice" placeholder="Max. price in CHF">
  </div>

   <div class="checkbox">
    <label>
      <input type="checkbox" name="searchSharedApartment" id="searchSharedApartment"> Shared apartments
    </label>
  </div>  
  <button  type="submit" class="btn btn-default">Search</button>
  <button id="toggleMoreSearchCriteriaBtn" type="button" class="btn btn-default">More criteria</button>
  <p>
  <div id="moreSearchCriteria" style="display:none">
  <div  class="form-group">
    <label class="sr-only" for="searchTextZIP">ZIP</label>
    <input type="text" class="form-control" name="searchTextZip" id="searchTextZip" placeholder="ZIP">
  </div> 
  <div class="form-group">
    <label class="sr-only" for="searchTextCity">City</label>
    <input type="text" class="form-control" name="searchTextCity" id="searchTextCity" placeholder="City">
  </div>
  <div class="form-group">
    <label class="sr-only" for="searchTextMaxPrice">Max. price</label>
    <input type="text" class="form-control" name="searchTextMaxPrice" id="searchTextMaxPrice" placeholder="Max. price in CHF">
  </div>

   <div class="checkbox">
    <label>
      <input type="checkbox" name="searchSharedApartment" id="searchSharedApartment"> Shared apartments
    </label>
  </div>
  </div> 
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


<script>

	$(document).ready(function(){
	
	//Toggle search criteria
	$( "#toggleMoreSearchCriteriaBtn" ).click(function() {
	  $( "#moreSearchCriteria" ).toggle( "slow", function() {
	    // Animation complete.
	  });
	});
	});

</script>