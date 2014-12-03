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
			<li><a href="#section-7">My Appointments</a></li>
		</ul>
	</div>
	
<%-- 	<a href="<spring:url value="/account/remove/${user.id}.html" />" --%>
<!-- 	class="btn btn-danger btn-large pull-right triggerRemove"> Delete -->
<!-- 	my account </a> -->

	

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


