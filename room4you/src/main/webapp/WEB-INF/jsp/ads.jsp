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
   <div class="checkbox">
    <label>
      <input type="checkbox" name="searchSharedApartment" id="searchSharedApartment" checked> Shared apartments
    </label>
  </div>  
  <button  type="submit" class="btn btn-default">Search</button>
  <button id="toggleMoreSearchCriteriaBtn" type="button" class="btn btn-default">More criteria</button>
  <p>
  <div id="moreSearchCriteria" style="display:none">
   <div class="form-group">
    <label class="sr-only" for="searchTextMinPrice">Rent per month from</label>
    <input type="number" class="form-control" name="searchTextMinPrice" id="searchTextMinPrice" step="100" placeholder="Min. rent in CHF">
  </div>
    <div class="form-group">
    <label class="sr-only" for="searchTextMaxPrice">Rent per month to</label>
    <input type="number" class="form-control" name="searchTextMaxPrice" id="searchTextMaxPrice" step="100" placeholder="Max. rent in CHF">
  </div>
   <div class="form-group">
    <label class="sr-only" for="searchTextNbrRoomMatesMin">Number of room mates from</label>
    <input type="number" class="form-control" name="searchTextNbrRoomMatesMin" id="searchTextNbrRoomMatesMin" placeholder="Min. room mates">
  </div>
   <div class="form-group">
    <label class="sr-only" for="searchTextNbrRoomMatesMax">Number of room mates to</label>
    <input type="number" class="form-control" name="searchTextNbrRoomMatesMax" id="searchTextNbrRoomMatesMax" placeholder="Max. room mates">
  </div>
   <div class="form-group">
    <label class="sr-only" for="searchTextNbrRoomsMin">Number of rooms from</label>
    <input type="number" class="form-control" name="searchTextNbrRoomsMin" id="searchTextNbrRoomsMin" placeholder="Min. of rooms">
  </div>
   <div class="form-group">
    <label class="sr-only" for="searchTextNbrRoomsMax">Number of rooms to</label>
    <input type="number" class="form-control" name="searchTextNbrRoomsMax" id="searchTextNbrRoomsMax" placeholder="Max. of rooms">
  </div>
  </div> 
</form:form>

<hr>

 <div id="Layout" class="container">
            <div class="row">

<c:forEach items="${ads}" var="ad">
		<div class="col-sm-6 col-md-4 col-lg-3">
			<div id="thumbnail" class="thumbnail" style="padding-bottom:10px">
				
				<a href="<spring:url value="/ads/${ad.id}.html"/>"> <img src="data:image/jpeg;base64,${ad.images[0].imageAsString}" class="img-responsive"></img></a>
				
				<div class="caption" >
					<h4>${ad.title}</h4>
					<p>${ad.description}</p>
					<p>City: ${ad.city}</p>
					<p>Rent per month: <fmt:formatNumber value="${ad.rentPerMonth}" type="currency" currencySymbol="CHF"/></p>
					
					<a href="<spring:url value="/ads/${ad.id}.html"/>"  class="btn btn-primary" role="button">
					View Details
					</a>
					
				</div>
			</div>
			</div>
</c:forEach>	
</div>
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