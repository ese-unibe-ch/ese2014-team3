<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


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
							class="btn btn-primary pull-right"> Unbookmark Ad </a>
					</c:otherwise>
				</c:choose>
			</sec:authorize>

			<c:if test="${param.success eq true}">
				<div class="alert alert-success">Message sent!</div>
			</c:if>

			<h1>
				<c:out value="${ad.title}" />
			</h1>
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
					<label for="numberOfRooms" class="col-sm-4">Number of room
						mates</label>
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
							<fmt:formatNumber value="${ad.rentPerMonth}" type="currency"
								currencySymbol="CHF" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="name" class="col-sm-4 ">Address:</label>
					<div class="col-sm-8">
						<div class="input-group">
							<address>
								<a>${ad.street}<br> ${ad.zip} ${ad.city}<br>
								</a>
							</address>
						</div>
					</div>
				</div>


				<div class="form-group">
					<label for="name" class="col-sm-4 ">Shared Apartment:</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="checkbox"
								<c:if test="${ad.sharedApartment}">checked="checked"</c:if>
								onclick="return false">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="name" class="col-sm-4 ">Smoking allowed:</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="checkbox"
								<c:if test="${ad.smokingAllowed}">checked="checked"</c:if>
								onclick="return false">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="name" class="col-sm-4 ">Pets allowed:</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="checkbox"
								<c:if test="${ad.petsAllowed}">checked="checked"</c:if>
								value="${ad.petsAllowed}" onclick="return false">
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="name" class="col-sm-4 ">Instruments allowed:</label>
					<div class="col-sm-8">
						<div class="input-group">
							<input type="checkbox"
								<c:if test="${ad.instrumentsAllowed}">checked="checked"</c:if>
								value="${ad.instrumentsAllowed}" onclick="return false">
						</div>
					</div>
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
							<a href="<spring:url value="/message/${ad.id}.html"/>"
								class="btn btn-default btn-md" role="button"> <span
								class="glyphicon glyphicon-envelope"></span>
							</a>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label for="name" class="col-sm-4 ">Visits:</label>
					<div class="col-sm-8">
						<c:forEach items="${appointments}" var="appointments">
							<div class="col-sm-8">
								<div class="input-group">

									<!-- 	<a href="#myModal" role="button" class="btn" -->
									<!--  		data-toggle="modal"> -->
									<!-- Button trigger modal -->
									<a
										href="<spring:url value="/ad/${ad.id}/appointment/${appointments.id}.html"/>">
										<c:out  value="${appointments.appointDate.appointDate}" /> <c:out
												value="${appointments.appointDate.startTime} - "  /> <c:out
												value="${appointments.appointDate.endTime}" />
									</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>



				<div class="form-group">
					<label for="name" class="col-sm-4 ">See images of your
						future room:</label>
					<div class="col-sm-8">
						<div class="input-group">
							<button id="toggleImagesBtn" type="button"
								class="btn btn-default btn-md">
								<span class="glyphicon glyphicon-film"></span>
							</button>
						</div>
					</div>
				</div>

			</form>
		</div>
		<!-- end col-md-6 -->

		<div class="col-md-6">
			<div id="googleMap" style="width: 450px; height: 380px;"></div>
		</div>
	</div>


	<div id="adImages" style="display: none">

		<c:forEach items="${ad.images}" var="image">
			<img src="data:image/jpeg;base64,${image.imageAsString}" alt="image"
				class="img-thumbnail">

		</c:forEach>

	</div>




</div>


<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBEGI1Wn8tNYbkCxFF0lupb81SQgLhtHEU&sensor=false">
	
</script>


<script>
	var geocoder;
	var map;
	var mapCenter;
	function initialize(adr) {
		var street = "${ad.street}";
		var zip = $
		{
			ad.zip
		}
		;
		var city = "${ad.city}";
		var address = street + ", " + zip + "" + city;
		geocoder = new google.maps.Geocoder();
		var latlng = new google.maps.LatLng(46.952106, 7.444181);
		var mapOptions = {
			zoom : 8,
			center : latlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		}
		map = new google.maps.Map(document.getElementById("googleMap"),
				mapOptions);
		codeAddress(address);
	}

	function codeAddress(adr) {
		var address = adr;
		geocoder.geocode({
			'address' : address
		}, function(results, status) {
			if (status == google.maps.GeocoderStatus.OK) {
				mapCenter = results[0].geometry.location;
				map.setCenter(results[0].geometry.location);
				var marker = new google.maps.Marker({
					map : map,
					position : results[0].geometry.location
				});
			} else {
				alert("Geocode was not successful for the following reason: "
						+ status);
			}
		});
	}

	google.maps.event.addDomListener(window, 'load', initialize);

	//Resize google map after showing in modal
	$(document).ready(function() {
		$("#locationModal").on('shown.bs.modal', function() {
			google.maps.event.trigger(map, "resize");
			map.setCenter(mapCenter);
		});

		//Toggle images
		$("#toggleImagesBtn").click(function() {
			$("#adImages").toggle("slow", function() {
				// Animation complete.
			});
		});
	});
</script>


<!-- Modal Show Google Maps in Modal-->
<div class="modal fade" id="locationModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Location</h4>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row">
						<div id="googleMap1" style="width: 450px; height: 380px;"></div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>



