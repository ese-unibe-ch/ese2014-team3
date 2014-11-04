<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1>My account</h1>

<c:if test="${param.success eq true}">
	<div class="alert alert-success">Message sent!</div>
</c:if>

<!-- Button trigger modal -->
<button class="btn btn-primary btn-lg pull-right" data-toggle="modal" data-target="#myModal">
  Place new ad
</button>

<h2>Currently placed ads</h2>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Title</th>
			<th>Description</th>
			<th>Delete</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${user.ads}" var="ad">
			<tr>
				<td id="ad_${ad.id}">
					<a href="<spring:url value="/ads/${ad.id}.html" />">
						<c:out value="${ad.title}" />
					</a>
				</td>
				<td>
					<a href="<spring:url value="/ads/${ad.id}.html" />">
					<c:out value="${ad.description}" /></a>
				</td>
				<td>
					<a href="<spring:url value="/ad/remove/${ad.id}.html" />" class="btn btn-danger triggerRemove">
						remove ad
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<h2>User details</h2>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>User Name</th>
			<th>User Email</th>
		</tr>
	</thead>
	<tbody>
			<tr>
				<td>
					${user.name}
				</td>
				<td>
					${user.email}
				</td>
			</tr>
	</tbody>
</table>


<h2>Currently bookmarked ads</h2>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Title</th>
			<th>Description</th>
			<th>Unbookmark Ad</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${user.bookmarkedAds}" var="ad">
			<tr>
				<td id="ad_${ad.id}">
					<a href="<spring:url value="/ads/${ad.id}.html" />">
						<c:out value="${ad.title}" />
					</a>
				</td>
				<td>
					<a><c:out value="${ad.description}" /></a>
				</td>
				<td>
					<a href="<spring:url value="/ad/unBookmarkAd/${ad.id}.html" />" class="btn btn-danger triggerRemove">
						Unbookmark Ad
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<h2>Messages</h2>
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
				<td id="message_${message.id}">
					<a>
						<c:out value="${message.sender.name}" />
					</a>
				</td>
				<td>
					<a href="<spring:url value="/ads/${message.messageAd.id}.html" />">
						<c:out value="${message.messageAd.title}" />
					</a>
				</td>
				<td>
					<a href="<spring:url value="/showmessage/${message.id}.html" />">
						Show Message
					</a>
				</td>
				<td>
					<a href="<spring:url value="/reply/${message.id}.html" />">
						Reply
					</a>
				</td>
				<td>
					<a href="<spring:url value="/message/remove/${message.id}.html" />" class="btn btn-danger triggerRemove">
						Delete Message
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<h2>Currently subscribed alerts</h2>
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Title</th>
			<th>Delete</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${user.alerts}" var="alert">
			<tr>
				<td id="alert_${alert.id}">
					<a>
						<c:out value="${alert}" />
					</a>
				</td>
				<td>
					<a href="<spring:url value="/alert/remove/${alert.id}.html" />" class="btn btn-danger triggerRemove">
						remove alert
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<a href="<spring:url value="/account/remove/${user.id}.html" />" class="btn btn-danger btn-large pull-right triggerRemove">
	Delete my account
</a>

<form:form method="post" modelAttribute="ad" cssClass="form-horizontal adForm" enctype="multipart/form-data">
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div  class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
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
			<label for="name" class="col-sm-2 control-label">Number of rooms:</label>
			<div class="col-sm-10">
				<form:input type="number" path="nbrRooms" cssClass="form-control" />
				<form:errors path="nbrRooms" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Number of room mates:</label>
			<div class="col-sm-10">
				<form:input type="number" path="nbrRooms" cssClass="form-control" />
				<form:errors path="nbrRooms" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Available from:</label>
			<div class="col-sm-10">
				<form:input type="date" path="availableFrom" cssClass="form-control" />
				<form:errors path="availableFrom" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Rent per month:</label>
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
			<label for="name" class="col-sm-2 control-label">Additional Information:</label>
			<div class="col-sm-10">
				<form:input path="additionalInformation" cssClass="form-control" />
				<form:errors path="additionalInformation" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">RoomMates:</label>
			<div class="col-sm-10">
				  <form:select class="form-control" path="roomMates">         
                    <form:options items="${users}" var="users" itemValue="id" itemLabel="name"/>
                  </form:select>
			</div>
		</div>
		<div class="form-group">
				<label for="name" class="col-sm-2 control-label">Image:</label>
				<div class="col-sm-10">
					<input type="file" name="image[]" cssClass="form-control" />
						<button id="addFile" type="button" class="btn btn-default btn-sm right-block">
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

<br /><br />

<script type="text/javascript">
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
