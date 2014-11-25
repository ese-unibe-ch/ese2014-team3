<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<style type="text/css">
.scroll-area {
	height: 600px;
	position: relative;
	overflow: auto;
}
</style>

<c:if test="${param.success eq true}">
	<div class="alert alert-success">Message sent!</div>
</c:if>

<h1>My account</h1>

<!-- Button trigger modal -->
<button class="btn btn-primary btn-lg pull-right" data-toggle="modal"
	data-target="#myModal">Place new ad</button>

<div class="container">
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#section-1">User details</a></li>
                <li><a href="#section-2">Placed ads</a></li>
                <li><a href="#section-3">Bookmarked ads</a></li>
             	<li><a href="#section-4">Messages</a></li>
                <li><a href="#section-5">Alerts</a></li>
                <li><a href="#section-6">Applications</a></li>
            </ul>
        </div>

    <div class="scroll-area" data-spy="scroll" data-target="navbarCollapse" data-offset="0">


		<h2 id="section-1">User details</h2>
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>User Name</th>
					<th>User Email</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${user.name}</td>
					<td>${user.email}</td>
				</tr>
			</tbody>
		</table>
		
		
		<h2 id="section-2">Currently placed ads</h2>
		
		<div id="Layout" class="container">
		            <div class="row">
		
		<c:forEach items="${user.ads}" var="ad">
				<div class="col-sm-6 col-md-4 col-lg-3">
					<div style="width: 75%; height: 75%; padding-bottom:5%" id="thumbnail" class="thumbnail" >
						
						<a href="<spring:url value="/ads/${ad.id}.html"/>"> <img src="data:image/jpeg;base64,${ad.images[0].imageAsString}" class="img-responsive"></img></a>
						
						<div class="caption" >
							<h4>${ad.title}</h4>
							<p>${ad.description}</p>
							<p>City: ${ad.city}</p>
							<p>Rent per month: <fmt:formatNumber value="${ad.rentPerMonth}" type="currency" currencySymbol="CHF"/></p>
							
							<a href="<spring:url value="/ads/${ad.id}.html"/>"  class="btn btn-primary" role="button">
							View Details
							</a>
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
		
		
		<h2 id="section-3">Currently bookmarked ads</h2>
		
		<div id="bookmarkedAds" class="container">
		            <div class="row">
		
		<c:forEach items="${user.bookmarkedAds}" var="ad">
				<div class="col-sm-6 col-md-4 col-lg-3">
					<div style="width: 75%; height: 75%; padding-bottom:5%" id="thumbnail" class="thumbnail" >
						
						<a href="<spring:url value="/ads/${ad.id}.html"/>"> <img src="data:image/jpeg;base64,${ad.images[0].imageAsString}" class="img-responsive"></img></a>
						
						<div class="caption" >
							<h4>${ad.title}</h4>
							<p>${ad.description}</p>
							<p>City: ${ad.city}</p>
							<p>Rent per month: <fmt:formatNumber value="${ad.rentPerMonth}" type="currency" currencySymbol="CHF"/></p>
							
							<a href="<spring:url value="/ads/${ad.id}.html"/>"  class="btn btn-primary" role="button">
							View Details
							</a>
							<p></p>
							<a
							href="<spring:url value="/ad/removeBookmarkAd/${ad.id}.html" />"
							class="btn btn-danger triggerRemove"> Unbookmark Ad </a>
							
						</div>
					</div>
					</div>
		</c:forEach>	
		</div>
		</div>
		
		<h2 id="section-4">Messages received</h2>
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>From</th>
					<th>Regarding Ad</th>
					<th>Details</th>
					<th>Reply</th>
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
						<td><a href="<spring:url value="/reply/${message.id}.html" />">
								Reply </a></td>
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
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		<h2 id="section-5">Currently subscribed alerts</h2>
		<table class="table table-bordered table-hover table-striped">
			<thead>
				<tr>
					<th>Criteria</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${user.alerts}" var="alert">
					<tr>
						<td id="alert_${alert.id}">
						<a href="javascript:{}" onclick="document.getElementById('showAlertAdsForm').submit(); return false;">
						 <c:out value="${alert}" />
						</a>
							<form:form id="showAlertAdsForm" style="display: none;" action="searchAds.html" class="form-inline"  role="form" method="post" >
						    <input type="text" class="form-control" name="searchTextZip" id="searchTextZip" value="${alert.zip}">
						    <input type="text" class="form-control" name="searchTextCity" id="searchTextCity" value="${alert.city}">
						    <input type="number" class="form-control" name="searchTextMinPrice" id="searchTextMinPrice" step="100" value="${alert.rentPerMonthMin}">
						    <input type="number" class="form-control" name="searchTextMaxPrice" id="searchTextMaxPrice" step="100" value="${alert.rentPerMonthMax}">
						    <input type="number" class="form-control" name="searchTextNbrRoomMatesMin" id="searchTextNbrRoomMatesMin" value="${alert.nbrRoomsMatesMin}">
						    <input type="number" class="form-control" name="searchTextNbrRoomMatesMax" id="searchTextNbrRoomMatesMax" value="${alert.nbrRoomsMatesMax}">
						    <input type="number" class="form-control" name="searchTextNbrRoomsMin" id="searchTextNbrRoomsMin" value="${alert.nbrRoomsMin}">
						    <input type="number" class="form-control" name="searchTextNbrRoomsMax" id="searchTextNbrRoomsMax" value="${alert.nbrRoomsMax}"> 
							</form:form>
										
						</td>
						<td><a
							href="<spring:url value="/alert/remove/${alert.id}.html" />"
							class="btn btn-danger triggerRemove"> remove alert </a></td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		
			
			<h2 id="section-6">Applications</h2>
				<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>From</th>
						<th>Regarding Ad</th>
						<th>Appointment</th>
						<th>Details</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${usera.receivedApplications}" var="application">
					<tr>
						<td id="application_${application.id}"><a> <c:out
									value="${application.sender.name}" />
						</a></td>
						<td><a
							href="<spring:url value="/ads/${application.applicationAd.id}.html" />">
								<c:out value="${application.applicationAd.title}" />
						</a></td>
						<td><a><c:out
									value="${application.appointment.appointDate}" /></a>
						<td><a
							href="<spring:url value="/showApplication/${application.id}.html" />">
								Show Application </a></td>
					</tr>
					</c:forEach>
				</tbody>
				</table>
		    </div>
</div>		
		       

		
<a href="<spring:url value="/account/remove/${user.id}.html" />"
	class="btn btn-danger btn-large pull-right triggerRemove">
	Delete my account </a> 
		
		
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
							<label for="name" class="col-sm-2 control-label">Number
								of rooms:</label>
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
							<label for="name" class="col-sm-2 control-label">Number
								of room mates:</label>
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
							<label for="name" class="col-sm-2 control-label">Rent
								per month:</label>
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
								<form:checkbox path="instrumentsAllowed"
									cssClass="form-control" style="border-style:none;" />
								<form:errors path="instrumentsAllowed" />
							</div>
						</div>

						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">Additional
								Information:</label>
							<div class="col-sm-10">
								<form:input path="additionalInformation"
									cssClass="form-control" />
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
							<a id="datetimepicker1" class="input-append">
								<input data-format="dd-MM-yyyy"
									type="date" name="appointments"></input> 
							</a>
							<a id="datetimepicker2" class="input-append">
								<input type="time"
									name="appointments"></input> 
							</a>
							<a id="datetimepicker3" class="input-append">
								<input type="time"
									name="appointments"></input> 
							</a>
							<button id="addAppointment" type="button"
								class="btn btn-default btn-sm right-block">
								<span class="glyphicon glyphicon-plus"></span>
							</button>
							</div>
							<label for="name" class="col-sm-2 control-label">Number
								of Visitors:</label>
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
					<button type="button" class="btn btn-default"
						data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
		</div>
	</form:form> 

<br />
<br /> 


				
<script>
$(document).ready(function() {
	$(".triggerRemove").click(function(e) {
		e.preventDefault();
		$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
		$("#modalRemove").modal();
	});

	$(".adForm").validate(
			{
				rules: {
					name: {
						required : true,
						minlength : 1
					},
					title: {
						required : true,
						title: true
					}
				},
				highlight: function(element) {
					$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
				},
				unhighlight: function(element) {
					$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
				}
			}
		);
});
</script>


<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Remove ad</h4>
      </div>
      <div class="modal-body">
        Really remove?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>




<!-- adding more fileinputs -->
<script>
$(document).ready(function() {
    //add more file components if Add is clicked
    $('#addFile').click(function() {
        var fileIndex = $('#fileTable tr').children().length - 1;
        $('#myModalContent').append(
        		'<div class="form-group">'+
				'<label for="name" class="col-sm-2 control-label">Image:</label>'+
				'<div class="col-sm-10">'+
					'<input type="file" name="image[]" cssClass="form-control" />'+
				'</div>');
    });
     
});
</script>

<!-- adding more appointments -->
<script>
$(document).ready(function() {
    //add more file components if Add is clicked
    $('#addAppointment').click(function() {
        $('#firstDatePicker').append(
				'<label for="name" class="col-sm-2 control-label">Appointment:</label>'+
				'<div class="col-sm-10">'+
				'<a id="datetimepicker1" class="input-append">'+
					'<input data-format="dd-MM-yyyy"'+
					'type="date" name="appointments"></input> '+
					'</a>'+
					'<a id="datetimepicker2" class="input-append">'+
					'	<input type="time"'+
					'		name="appointments"></input> '+
					'</a>'+
					'<a id="datetimepicker3" class="input-append">'+
					'	<input type="time"'+
					'		name="appointments"></input> '+
					'</a>'+
				'</div>'+
				'<label for="name" class="col-sm-2 control-label">Number'+
				'	of Visitors:</label>'+
				'<div class="col-sm-10">'+
				'	<input name="appointments" class="form-control" />'+
				'</div>'+
				'</div>'+
				'</div>'

				);
    });
     
});
</script>

<script>
function showAlertedAds(data1, data2)
{
$.ajax({
	  url: "searchAds.html",
	  type: "post",
	  data: json/array/whatever,

	  success: function(){ // trigger when request was successfull
	    window.location.href = 'somewhere'
	  }
	});
}
</script>


