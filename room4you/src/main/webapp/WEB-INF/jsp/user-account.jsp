<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<c:if test="${param.success eq true}">
	<div class="alert alert-success">Message sent!</div>
</c:if>



<div class="container">

	<h2>${user.name}</h2>

	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse user-nav" id="navbarCollapse">
		<ul class="nav navbar-nav">
			<li><a href="#section-5">Alerts</a></li>
			<c:if test="${not empty user.ads}">	<li><a href="#section-6">Visitors</a></li></c:if>
			<li><a href="#section-7">My Appointments</a></li>
		</ul>
	</div>

	<div class="scroll-area" data-spy="scroll" data-target="navbarCollapse"
		data-offset="0">



				
	
			<h4 id="section-5" class="section">Subscribed alerts</h4>
			<a href="#" class="scrollup float-right">top</a>
			<table>
				<tbody id="alerts">
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
			<!-- 	-->
			
			<c:if test="${not empty user.ads}">
			<h4 id="section-6" class="section">Visitors</h4>
			<a href="#" class="scrollup float-right">top</a>
			<div id="Layout" class="container">
		            <div class="row">
		            <div class="caption" >
					<c:forEach items="${user.ads}" var="ad">
					<form:form method="post" modelAttribute="favCandidates" action="compileCandidates.html">
						<c:choose>
						<c:when test="${empty ad.appointments}">
						<!--    <h4>Ad: ${ad.title}</h4> -->
						<!--    <p>No appointments scheduled for this appointment </p>	-->						
						</c:when>
						<c:otherwise>
						<form:hidden path="ad" value="${ad.id}"></form:hidden>
						<h4>${ad.title}</h4>
							<c:forEach items="${ad.appointments}" var ="appointment">	
								<h5>Appointment</h5>
								<div class="row">
									<div class="col-md-1">
										<label>Begin:</label>
									</div>
									<div class="col-md-1.5">
										<label>${appointment.appointDate.startTime}</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1">
										<label>Date:</label>
									</div>
									<div class="col-md-1.5">
										<label>${appointment.appointDate.appointDate}</label>
									</div>
								</div>
								<div class="row">
									<div class="col-md-1">
										<label>End:</label>
									</div>
									<div class="col-md-1.5">
										<label>${appointment.appointDate.endTime}</label>
									</div>
								</div>
								<p></p>
										<label>Visitors:</label>
										<c:choose>
											<c:when test="${not empty appointment.visitors}">
											<c:forEach items="${appointment.visitors}" var="visitor">
						 					<a href="<spring:url value="/ads/${ad.id}.html"/>"> </a> 
											<li><form:checkbox path="visitors" value="${visitor.name}"></form:checkbox><a href="<spring:url value="/users/${visitor.id}.html" />"> 
													<c:out value="${visitor.name}" />
												</a></li>
											</c:forEach>
											</c:when>
											<c:otherwise>
											<c:out value="No visitors at the moment" />
											</c:otherwise>
											</c:choose>
						</c:forEach>
					<!--  	<div class="col-md-6"> -->
					<!--  		<h5>Compile a list of the most promising candidates:</h5> -->
					<!--  			<c:forEach items="${user.ads}" var="ad"> -->
					<!--  				<div class="col-sm-6 col-md-4 col-lg-3">  -->
					<!--					<c:forEach items="${ad.appointments}" var="appointment"> -->
					<!-- 						<c:forEach items="${appointment.visitors}" var="visitor"> -->
					<!--  							<form:checkbox path="visitors" value="${visitor.name}"></form:checkbox> ${visitor.name}<br> -->
					<!--						</c:forEach> -->
					<!--					</c:forEach> -->
					<!--				</div>  -->
					<!--  			</c:forEach> -->
			 		<!--  	</div>	-->
			 		<!--    	<input type="submit" class="btn btn-primary" value="Compile" /> -->
			 			</c:otherwise>
			 			</c:choose>
			 				<c:if test="${not empty ad.appointments}">
			 			<!--  		<input type="submit" class="btn btn-primary" value="Compile" /> -->
			 				</c:if>
			 			</form:form>
			 			</c:forEach>
			 			</div> 
		</div>
		</div>
	</c:if>
	
	<h4 id="section-7" class="section">My Appointments</h4>
			<a href="#" class="scrollup float-right">top</a>
			<div id="Layout" class="container">
		            <div class="row" id="appointments">
					<c:forEach items="${user.appointments}" var="appointment">
						<div class="caption" >
							<h4>
								<a href="<spring:url value="/ads/${appointment.appointmentAd.id}.html" />"> 
								<c:out value="${appointment.appointmentAd.title}" /></a>
							</h4>
							<div class="row">
									<div class="col-md-1">
										<label>Date:</label>
									</div>
									<div class="col-md-1.5">
										<label>${appointment.appointDate.appointDate}</label>
									</div>
								</div><div class="row">
									<div class="col-md-1">
										<label>Begin:</label>
									</div>
									<div class="col-md-1.5">
										<label>${appointment.appointDate.startTime}</label>
									</div>
								</div>	
							<div class="row">
									<div class="col-md-1">
										<label>Begin:</label>
									</div>
									<div class="col-md-1.5">
										<label>${appointment.appointDate.endTime}</label>
									</div>
								</div>
						</div>		
					</c:forEach>
					</div>
			</div>
		</div>
	</div>
	


<!-- <a href="<spring:url value="/account/remove/${user.id}.html" />"
	class="btn btn-danger btn-large pull-right triggerRemove"> Delete
	my account </a> -->


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
						<label for="name" class="col-sm-2 control-label">Instruments
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
						<p>Date: <a id="datetimepicker1" class="input-append"><input
								data-format="dd-MM-yyyy" type="date" name="appointments"></input>
								
							</a></p><p>Begin: <a id="datetimepicker2" class="input-append"> <input
								type="time" name="appointments"></input>
								
							</a></p><p>End:  <a id="datetimepicker3" class="input-append"> <input
								type="time" name="appointments"></input>
							</a></p>
							
						</div>
						<label for="name" class="col-sm-2 control-label">Number of
							Visitors:</label>
						<div class="col-sm-10">
							<input name="appointments" class="form-control" />
						</div>
							<button id="addAppointment" type="button"
								class="btn btn-default btn-sm right-block">
								<span class="glyphicon glyphicon-plus"></span>
							</button>
							<p></p>
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
				if ($.trim($("#alerts").html()) == '') {
					$('#alerts').html("No alerts");
				}
				if ($.trim($("#createdAds").html()) == '') {
					$('#createdAds').html("No ads");
				}
				if ($.trim($("#messages").html()) == '') {
					$('#messages').html("No messages");
				}
				if ($.trim($("#appointments").html()) == '') {
					$('#appointments').html("No appointments");
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


