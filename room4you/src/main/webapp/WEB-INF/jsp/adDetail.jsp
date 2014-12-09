<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<div class="container-fluid">

<div class="row">
  <div class="col-md-6">
  
  	<sec:authorize access="isAuthenticated()">

				<c:choose>
					<c:when test="${isBkmrkedAd eq false}">
						<a href="<spring:url value="/ad/bookmarkAd/${ad.id}.html" />"
							class="btn btn-primary pull-right" id="bkmrk"> Bookmark Ad </a>
					</c:when>
					<c:otherwise>
						<a href="<spring:url value="/ad/unBookmarkAd/${ad.id}.html" />"
							class="btn btn-primary pull-right"> Unbookmark Ad  </a>
					</c:otherwise>
				</c:choose>
	</sec:authorize>

  <c:if test="${param.success eq true}">
		<div class="alert alert-success" id="msgsent">Message sent!</div>
	</c:if>
  
  <h1><c:out value="${ad.title}" /></h1>
  <p></p>		
		
		<form class="form-horizontal" role="form">
		  <div class="form-group">
		    <label for="availableFrom" class="col-sm-4">Available from</label>
		    <div class="col-sm-8">
		      <fmt:formatDate type="date" value="${ad.availableFrom}" />
		    </div>
		  </div>
		  
			  <div class="form-group">
			    <label for="numberOfRooms" class="col-sm-4">Number of rooms</label>
			    <div class="col-sm-8">
			      	<div class="input-group">
						<p>${ad.nbrRooms}</p>							
					</div>
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="numberOfRooms" class="col-sm-4">Number of room mates</label>
			    <div class="col-sm-8">
			    	<div class="input-group">
						<p>${ad.nbrRoomsMates}</p>							
					</div>
			    </div>
			  </div>
		
			<div class="form-group">
				<label for="name" class="col-sm-4 ">Rent per month:</label>
				<div class="col-sm-8">
				<div class="input-group">
					<fmt:formatNumber value="${ad.rentPerMonth}" type="currency" currencySymbol="CHF"/>
				</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-4 ">Address:</label>
				<div class="col-sm-8">
					<div class="input-group">
						<address>
						<a>${ad.street}<br>
						${ad.zip} ${ad.city}<br>
						</a>								
						</address>							
					</div>
				</div>
			</div>
			

			<div class="form-group">
				<label for="name" class="col-sm-4 ">Shared Apartment:</label>
				<div class="col-sm-8">
					<div class="input-group">
					<input type="checkbox" <c:if test="${ad.sharedApartment}">checked="checked"</c:if> onclick="return false">						
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-4 ">Smoking allowed:</label>
				<div class="col-sm-8">
					<div class="input-group">
					<input type="checkbox" <c:if test="${ad.smokingAllowed}">checked="checked"</c:if> onclick="return false">						
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-4 ">Pets allowed:</label>
				<div class="col-sm-8">
					<div class="input-group">
					<input type="checkbox" <c:if test="${ad.petsAllowed}">checked="checked"</c:if> value="${ad.petsAllowed}" onclick="return false">						
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-4 ">Instruments allowed:</label>
				<div class="col-sm-8">
					<div class="input-group">
					<input type="checkbox" <c:if test="${ad.instrumentsAllowed}">checked="checked"</c:if> value="${ad.instrumentsAllowed}" onclick="return false">						
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-4 ">We are looking for:</label>
				<div class="col-sm-8">
					<div class="input-group">
						<p>${ad.weAreLookingFor}</p>							
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-4 ">Your future room mates:</label>
				<div class="col-sm-8">
					<div class="input-group">
						<c:forEach items="${roomMates}" var="roomMate">
							<a href="<spring:url value="/users/${roomMate.user.id}.html" />">
								<c:out value="${roomMate.user.name}" />
							</a>	
						</c:forEach>								
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-4 ">Contact us:</label>
				<div class="col-sm-8">
					<div class="input-group">
						<a href="<spring:url value="/message/${ad.id}.html"/>" class="btn btn-default btn-md" role="button">
							<span class="glyphicon glyphicon-envelope"></span>
						</a>						
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label for="name" class="col-sm-4 ">Visits:</label>
				<div class="col-sm-8">
				
			
				<sec:authorize access="isAuthenticated()"> 
					
					<!--  	<button type="button" class="btn-group btn-group-lg" data-toggle="modal" data-target="#myModal">  -->
					<c:choose>
					<c:when test="${empty appointmentList}">
					<p>No appointments scheduled</p>
					</c:when>
					<c:otherwise>
					<p>To be allowed to visit, please click on a date to get added to the visitor's list</p>
					</c:otherwise>
					</c:choose>	
					<c:forEach items="${appointmentList}" var="appointment">
				  	<c:choose> 
					<c:when test="${appointment.nmbrVisitors > 0}">
						<li><a 	href="<spring:url value="/ad/${ad.id}/appointment/${appointment.id}.html"/>" class="confirm"> 
								    <c:out 	value="${appointment.appointDate.startTime} - "  /> 
								    <c:out	value="${appointment.appointDate.endTime} " />
								    <c:out  value=" ${appointment.appointDate.appointDate}" />
						</a>
						</li>
						<p></p>
					</c:when>
					<c:otherwise>
						<li><span title="Already max visitors"> 
								<c:out	value="${appointment.appointDate.startTime} - "  /> 
								<c:out	value="${appointment.appointDate.endTime} " />
								<c:out  value=" ${appointment.appointDate.appointDate}" /> 
						</span>
						</li>
						<p></p>
					</c:otherwise>
					</c:choose>
					</c:forEach>
					</sec:authorize>
				
					
					<sec:authorize access="!isAuthenticated()">
					Please <a href="<spring:url value="/login.html"/>">Login</a> or <a href="<spring:url value="/register.html" />"> Register </a> to see the appointments </p>
					</sec:authorize>
			
													
								
			
				</div>
			</div>
			
		  
		</form>
	</div> <!-- end col-md-6 -->
	
	<div class="col-md-6">
		<div id="googleMap"  style="width:450px;height:380px;"></div>
				
		<div id="adImages2">
			<c:forEach items="${ad.images}" var="image">
				<img id="img${image.id}" src="data:image/jpeg;base64,${image.imageAsString}" alt="image" style="width:25%;height:25%;" class="img-thumbnail">
	
			</c:forEach>
		</div>
		
						
		<div class="form-group">
			<label for="name" class="col-sm-4 ">Description:</label>
			<div class="col-sm-8">
				<div class="input-group">
					<p>${ad.description}</p>							
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-4 ">Additional information:</label>
			<div class="col-sm-8">
				<div class="input-group">
					<p>${ad.additionalInformation}</p>							
				</div>
			</div>
		</div>
	</div>
</div>

</div>



<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBEGI1Wn8tNYbkCxFF0lupb81SQgLhtHEU&sensor=false">
</script>

<script type="text/javascript">
    $('.confirm').on('click', function () {
        return confirm('Add me to the appointment ?');
    });
</script>


<script>
  var geocoder;
  var map;
  var mapCenter;
  function initialize(adr) {
	var street = "${ad.street}";
	var zip=${ad.zip};
	var city="${ad.city}";
	var address=street+", "+zip+""+city;
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(46.952106, 7.444181);
    var mapOptions = {
      zoom: 8,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById("googleMap"), mapOptions);
   codeAddress(address);
  }

  function codeAddress(adr) {
    var address = adr;
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
    	  mapCenter = results[0].geometry.location;
        map.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });
  }		  
 
 	google.maps.event.addDomListener(window, 'load', initialize);
 	
 	//Resize google map after showing in modal
 	$(document).ready(function(){
  	$("#locationModal").on('shown.bs.modal', function () {
  		 google.maps.event.trigger(map, "resize");
  		 map.setCenter(mapCenter);
  	});
  	

 	$('#adImages2 img${image.id}').on({
 	    mouseenter: function(){
 	        $(this).animate({width: '100%', height: '100%'}, 500);
 	    },
 	    mouseleave: function(){
 	        $(this).animate({width: '25%', height: '25%'}, 500);
 	    }
 	});

 });



</script>

			

