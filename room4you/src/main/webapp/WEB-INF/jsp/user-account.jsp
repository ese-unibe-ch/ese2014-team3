<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<style type="text/css">
.scroll-area {
	height: 600px;
	position: relative;
	overflow: auto;
}

.thumbnail {
	width: 250px;
	height: 400px;
	overflow: auto;
}

.thumbnail img {
	width: 250px;
	height: 200px;
}
</style>

<c:if test="${param.success eq true}">
	<div class="alert alert-success">Message sent!</div>
</c:if>


<div class="container">

	<h2>${user.name}</h2>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse user-nav" id="navbarCollapse">
		<ul class="nav navbar-nav">
			<li class="active"><a href="#section-2">Placed ads</a></li>
			<li><a href="#section-3">Bookmarked ads</a></li>
			<!--<li><a href="#section-4">Messages</a></li>-->
			<li><a href="#section-5">Alerts</a></li>
			<li><a href="#scetion-6">Visitors</a></li>
			<li><a class="link" data-toggle="modal" data-target="#myModal">Place
					new ad</a></li>
		</ul>
	</div>

	<div class="scroll-area" data-spy="scroll" data-target="navbarCollapse"
		data-offset="0">



		<h4 id="section-2" class="section">Placed ads</h4>

		<div id="Layout" class="container">
			<div class="row" id="createdAds">

				<c:forEach items="${user.ads}" var="ad">
					<div class="col-md-6 col-md-4 user-ads profile-ads">
						<div style="width: 75%; height: 75%; padding-bottom: 5%"
							id="thumbnail" class="thumbnail">

							<a href="<spring:url value="/ads/${ad.id}.html"/>"> <c:choose>
									<c:when test="${not empty ad.images}">
										<img
											src="data:image/jpeg;base64,${ad.images[0].imageAsString}"
											class="img-responsive"></img>
									</c:when>
									<c:otherwise>
										<img
											src="data:image/png;base64,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"
											class="img-responsive"></img>
									</c:otherwise>
								</c:choose>
							</a>

							<div class="caption">
								<h4>${ad.title}</h4>
								<p>City: ${ad.city}</p>
								<p>
									Rent per month:
									<fmt:formatNumber value="${ad.rentPerMonth}" type="currency"
										currencySymbol="CHF" />
								</p>

								<a href="<spring:url value="/ads/${ad.id}.html"/>"
									class="btn btn-primary" role="button"> View Details </a>
								<p></p>
								<a href="<spring:url value="/ad/edit/${ad.id}.html" />"
									class="btn btn-primary" role="button"> edit ad </a>
								<p></p>
								<a href="<spring:url value="/ad/remove/${ad.id}.html" />"
									class="btn btn-danger triggerRemove"> remove ad </a>


							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>


		<h4 id="section-3" class="section">Bookmarks</h4>
		<div id="bookmarkedAds" class="container">
			<div class="row" id="bookmarked">
			<c:forEach items="${user.bookmarkedAds}" var="bookmark">

					<div class="col-sm-6 col-md-4 col-lg-3">

						<c:forEach items="${user.bookmarkedAds}" var="bookmark">
							<div class="col-sm-6 col-md-4 user-bookmarks" >

								<div style="width: 75%; height: 75%; padding-bottom: 5%"
									id="thumbnail" class="thumbnail">

									<a
										href="<spring:url value="/ads/${bookmark.bookmarkedAd.id}.html"/>">
										<img
										src="data:image/jpeg;base64,${bookmark.bookmarkedAd.images[0].imageAsString}"
										class="img-responsive"></img>
									</a>

									<div class="caption">
										<h4>${bookmark.bookmarkedAd.title}</h4>
										<p>${bookmark.bookmarkedAd.description}</p>
										<p>City: ${bookmark.bookmarkedAd.city}</p>
										<p>
											Rent per month:
											<fmt:formatNumber
												value="${bookmark.bookmarkedAd.rentPerMonth}"
												type="currency" currencySymbol="CHF" />
										</p>
										<h4>${ad.title}</h4>
										<p>City: ${ad.city}</p>
										<p>
											Rent per month:
											<fmt:formatNumber value="${ad.rentPerMonth}" type="currency"
												currencySymbol="CHF" />
										</p>


										<a
											href="<spring:url value="/ads/${bookmark.bookmarkedAd.id}.html"/>"
											class="btn btn-primary" role="button"> View Details </a>
										<p></p>
										<a
											href="<spring:url value="/ad/removeBookmarkAd/${bookmark.bookmarkedAd.id}.html" />"
											class="btn btn-danger triggerRemove"> Unbookmark Ad </a>

									</div>
								</div>
								</div>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
			</div>
			<!-- 
		<h2 id="section-4" class="section">Messages received</h2>
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>From</th>
					<th>Regarding Ad</th>
					<th>Details</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userm.messages}" var="message">
					<tr>
						<td id="message_${message.id}"><a> <c:out
									value="${message.sender.name}" />
						</a></td>
						<td><a
							href="<spring:url value="/ads/${message.messageAd.id}.html" />">
								<c:out value="${message.messageAd.title}" />
						</a></td>
						<td><a
							href="<spring:url value="/showmessage/${message.id}.html" />">
								Show Message </a></td>
						<td><a
							href="<spring:url value="/message/remove/${message.id}.html" />"
							class="btn btn-danger triggerRemove"> Delete Message </a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h2>Messages sent</h2>
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>To</th>
					<th>Regarding Ad</th>
					<th>Details</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userm.sentMessages}" var="message">
					<tr>
						<td id="message_${message.id}"><a> <c:out
									value="${message.recipient.name}" />
						</a></td>
						<td><a
							href="<spring:url value="/ads/${message.messageAd.id}.html" />">
								<c:out value="${message.messageAd.title}" />
						</a></td>
						<td><a
							href="<spring:url value="/showmessage/${message.id}.html" />">
								Show Message </a></td>
					<td><a
							href="<spring:url value="/message/remove/${message.id}.html" />"
							class="btn btn-danger triggerRemove"> Delete Message </a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		-->

			<h4 id="section-5" class="section">Subscribed alerts</h4>
			<table class="">
				<tbody id="alert">
					<c:forEach items="${user.alerts}" var="alert">
						<tr>
							<td id="alert_${alert.id}"><a href="javascript:{}"
								onclick="document.getElementById('showAlertAdsForm').submit(); return false;">
									<c:out value="${alert}" />
							</a> <form:form id="showAlertAdsForm" style="display: none;"
									action="searchAds.html" class="form-inline" role="form"
									method="post">
									<input type="text" class="form-control" name="searchTextZip"
										id="searchTextZip" value="${alert.zip}">
									<input type="text" class="form-control" name="searchTextCity"
										id="searchTextCity" value="${alert.city}">
									<input type="number" class="form-control"
										name="searchTextMinPrice" id="searchTextMinPrice" step="100"
										value="${alert.rentPerMonthMin}">
									<input type="number" class="form-control"
										name="searchTextMaxPrice" id="searchTextMaxPrice" step="100"
										value="${alert.rentPerMonthMax}">
									<input type="number" class="form-control"
										name="searchTextNbrRoomMatesMin"
										id="searchTextNbrRoomMatesMin"
										value="${alert.nbrRoomsMatesMin}">
									<input type="number" class="form-control"
										name="searchTextNbrRoomMatesMax"
										id="searchTextNbrRoomMatesMax"
										value="${alert.nbrRoomsMatesMax}">
									<input type="number" class="form-control"
										name="searchTextNbrRoomsMin" id="searchTextNbrRoomsMin"
										value="${alert.nbrRoomsMin}">
									<input type="number" class="form-control"
										name="searchTextNbrRoomsMax" id="searchTextNbrRoomsMax"
										value="${alert.nbrRoomsMax}">
								</form:form></td>
							<td><a
								href="<spring:url value="/alert/remove/${alert.id}.html" />"
								class="btn btn-danger triggerRemove alert-remove"> remove
									alert </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 	
			<h4 id="section-2" class="section">Visitors</h4>
		
			<div id="Layout" class="container">
		            <div class="row">
		            
		
					<c:forEach items="${user.ads}" var="ad">
						<c:forEach items="${ad.appointments}" var ="appointment">		
						
						<div class="caption" >
							<h4>
							<p>Ad: ${ad.title}</p> 
							<p>Date: ${appointment.appointDate.appointDate} </p> 
							<p>Begin: ${appointment.appointDate.startTime} </p>
							<p>End: ${appointment.appointDate.endTime} </p>
							</h4>
							
							
							<c:forEach items="${appointment.visitors}" var="visitor">
						<!--  	<a href="<spring:url value="/ads/${ad.id}.html"/>"> </a> -->
			<a href="<spring:url value="/users/${visitor.id}.html" />"> <c:out
					value="${visitor.name}" />
			</a>
			</c:forEach>
		</div>
		</c:forEach>

		<div class="col-md-6">
			<form action="compileCandidats.html">
				<h5>Compile a list of the most promising candidates:</h5>

				<c:forEach items="${user.ads}" var="ad">
					<div class="col-sm-6 col-md-4 col-lg-3">
						<c:forEach items="${ad.appointments}" var="appointment">
							<c:forEach items="${appointment.visitors}" var="visitor">
								<input type="checkbox" name="visitors" value="${visitor.name}"> ${visitor.name}<br>
							</c:forEach>
						</c:forEach>
					</div>
				</c:forEach>
				<input type="submit" class="btn btn-primary" value="Save" />
			</form>
		</div>
		</div>
		
	
		</div>
</div>
		</c:forEach>
	</div>
</div>




<a href="<spring:url value="/account/remove/${user.id}.html" />"
	class="btn btn-danger btn-large pull-right triggerRemove"> Delete
	my account </a>
-->


<form:form method="post" modelAttribute="ad"
	cssClass="form-horizontal adForm" enctype="multipart/form-data">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">New Ad</h4>
				</div>
				<div id="myModalContent" class="modal-body">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Title:</label>
						<div class="col-sm-10">
							<form:input path="title" cssClass="form-control" />
							<form:errors path="title" />
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Description:</label>
						<div class="col-sm-10">
							<form:input path="description" cssClass="form-control" />
							<form:errors path="description" />
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Street:</label>
						<div class="col-sm-10">
							<form:input path="street" cssClass="form-control" />
							<form:errors path="street" />
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">ZIP:</label>
						<div class="col-sm-10">
							<form:input path="zip" cssClass="form-control" />
							<form:errors path="zip" />
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">City:</label>
						<div class="col-sm-10">
							<form:input path="city" cssClass="form-control" />
							<form:errors path="city" />
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Number of
							rooms:</label>
						<div class="col-sm-10">
							<form:select path="nbrRooms" cssClass="formControl"
								style="width:100%;" tabindex="6">
								<option value="1">1</option>
								<option value="1.5">1.5</option>
								<option value="2">2</option>
								<option value="2.5">2.5</option>
								<option value="3">3</option>
								<option value="3.5">3.5</option>
								<option value="4">4</option>
								<option value="4.5">4.5</option>
								<option value="5">5</option>
								<option value="5.5">5.5</option>
								<option value="6">6</option>
								<option value="6.5">6.5</option>
								<option value="7">7</option>
								<option value="7.5">7.5</option>
								<option value="8">8</option>
								<option value="8.5">8.5</option>
								<option value="9">9</option>
								<option value="9.5">9.5</option>
								<option value="10">10</option>
								<option value="10.5">10.5</option>
								<option value="11">11</option>
								<option value="11.5">11.5</option>
								<option value="12">12</option>
								<option value="12.5">12.5</option>
							</form:select>
							<form:errors path="nbrRooms" />
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Number of
							room mates:</label>
						<div class="col-sm-10">
							<form:select path="nbrRooms" cssClass="formControl"
								style="width:100%;" tabindex="6">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
							</form:select>
							<form:errors path="nbrRooms" />
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Available
							from:</label>
						<div class="col-sm-10">
							<form:input type="date" path="availableFrom"
								cssClass="form-control" />
							<form:errors path="availableFrom" />
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Rent per
							month:</label>
						<div class="col-sm-10">
							<div class="input-group">
								<span class="input-group-addon">CHF</span>
								<form:input path="rentPerMonth" cssClass="form-control" />
								<form:errors path="rentPerMonth" />
								<span class="input-group-addon">.00</span>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Pets
							allowed:</label>
						<div class="col-sm-10">
							<form:checkbox path="petsAllowed" cssClass="form-control"
								style="border-style:none;" />
							<form:errors path="petsAllowed" />
						</div>
					</div>

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Smoking
							allowed:</label>
						<div class="col-sm-10">
							<form:checkbox path="smokingAllowed" cssClass="form-control"
								style="border-style:none;" />
							<form:errors path="smokingAllowed" />
						</div>
					</div>

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Instuments
							allowed:</label>
						<div class="col-sm-10">
							<form:checkbox path="instrumentsAllowed" cssClass="form-control"
								style="border-style:none;" />
							<form:errors path="instrumentsAllowed" />
						</div>
					</div>

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">We are
							looking for:</label>
						<div class="col-sm-10">
							<form:input path="weAreLookingFor" cssClass="form-control" />
							<form:errors path="weAreLookingFor" />
						</div>
					</div>

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Additional
							Information:</label>
						<div class="col-sm-10">
							<form:input path="additionalInformation" cssClass="form-control" />
							<form:errors path="additionalInformation" />
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">RoomMates:</label>
						<div class="col-sm-10">
							<form:select class="form-control" path="roomMates">
								<form:options items="${users}" var="users" itemValue="id"
									itemLabel="name" />
							</form:select>
						</div>
					</div>
					<div class="form-group" id="firstDatePicker">
						<label for="name" class="col-sm-2 control-label">Appointment:</label>
						<div class="col-sm-10">
							<a id="datetimepicker1" class="input-append"> <input
								data-format="dd-MM-yyyy" type="date" name="appointments"></input>
							</a> <a id="datetimepicker2" class="input-append"> <input
								type="time" name="appointments"></input>
							</a> <a id="datetimepicker3" class="input-append"> <input
								type="time" name="appointments"></input>
							</a>
							<button id="addAppointment" type="button"
								class="btn btn-default btn-sm right-block">
								<span class="glyphicon glyphicon-plus"></span>
							</button>
						</div>
						<label for="name" class="col-sm-2 control-label">Number of
							Visitors:</label>
						<div class="col-sm-10">
							<input name="appointments" class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Image:</label>
						<div class="col-sm-10">
							<input type="file" name="image[]" class="form-control" />
							<button id="addFile" type="button"
								class="btn btn-default btn-sm right-block">
								<span class="glyphicon glyphicon-plus"></span>
							</button>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<br />
<br />



<script>
	$(document).ready(
			function() {
				$(".triggerRemove").click(
						function(e) {
							e.preventDefault();
							$("#modalRemove .removeBtn").attr("href",
									$(this).attr("href"));
							$("#modalRemove").modal();
						});

				$(".adForm").validate(
						{
							rules : {
								name : {
									required : true,
									minlength : 1
								},
								title : {
									required : true,
									title : true
								}
							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							}
						});

				if ($.trim($("#bookmarked").html()) == '') {
					$('#bookmarked').html("No bookmarks");
				}
				if ($.trim($("#alert").html()) == '') {
					$('#alert').html("No alerts");
				}
				if ($.trim($("#createdAds").html()) == '') {
					$('#createdAds').html("No created ads");
				}
			});
</script>


<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Remove ad</h4>
			</div>
			<div class="modal-body">Really remove?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger removeBtn">Remove</a>
			</div>
		</div>
	</div>
</div>




<!-- adding more fileinputs -->
<script>
	$(document)
			.ready(
					function() {
						//add more file components if Add is clicked
						$('#addFile')
								.click(
										function() {
											var fileIndex = $('#fileTable tr')
													.children().length - 1;
											$('#myModalContent')
													.append(
															'<div class="form-group">'
																	+ '<label for="name" class="col-sm-2 control-label">Image:</label>'
																	+ '<div class="col-sm-10">'
																	+ '<input type="file" name="image[]" cssClass="form-control" />'
																	+ '</div>');
										});

					});
</script>

<!-- adding more appointments -->
<script>
	$(document)
			.ready(
					function() {
						//add more file components if Add is clicked
						$('#addAppointment')
								.click(
										function() {
											$('#firstDatePicker')
													.append(
															'<label for="name" class="col-sm-2 control-label">Appointment:</label>'
																	+ '<div class="col-sm-10">'
																	+ '<a id="datetimepicker1" class="input-append">'
																	+ '<input data-format="dd-MM-yyyy"'+
					'type="date" name="appointments"></input> '
																	+ '</a>'
																	+ '<a id="datetimepicker2" class="input-append">'
																	+ '	<input type="time"'+
					'		name="appointments"></input> '
																	+ '</a>'
																	+ '<a id="datetimepicker3" class="input-append">'
																	+ '	<input type="time"'+
					'		name="appointments"></input> '
																	+ '</a>'
																	+ '</div>'
																	+ '<label for="name" class="col-sm-2 control-label">Number'
																	+ '	of Visitors:</label>'
																	+ '<div class="col-sm-10">'
																	+ '	<input name="appointments" class="form-control" />'
																	+ '</div>'
																	+ '</div>'
																	+ '</div>'

													);
										});

					});
</script>

<script>
	function showAlertedAds(data1, data2) {
		$.ajax({
			url : "searchAds.html",
			type : "post",
			data : json / array / whatever,

			success : function() { // trigger when request was successfull
				window.location.href = 'somewhere'
			}
		});
	}
</script>


