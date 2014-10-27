<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1><c:out value="${user.name}" /></h1>

<!-- Button trigger modal -->
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  New Ad
</button>


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
			<label for="name" class="col-sm-2 control-label">Number of Rooms:</label>
			<div class="col-sm-10">
				<form:input path="nbrRooms" cssClass="form-control" />
				<form:errors path="nbrRooms" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Available from:</label>
			<div class="col-sm-10">
				<form:input path="availableFrom" cssClass="form-control" />
				<form:errors path="availableFrom" />
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Rent per month (CHF):</label>
			<div class="col-sm-10">
				<form:input path="rentPerMonth" cssClass="form-control" />
				<form:errors path="rentPerMonth" />
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
			<div class="col-sm-10">

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
// 	$(".triggerAdImage").click(function(e) {
// 		e.preventDefault();
// 		$("#modalAddImage .adBtn").attr("href", $(this).attr("href"));
// 		$("#modalAddImage").modal();
// 	});
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


<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>ID</th>
			<th>User Name</th>
			<th>User Email</th>
		</tr>
	</thead>
	<tbody>
			<tr>
				<td>
					${user.id}
				</td>
				<td>
					${user.name}
				</td>
				<td>
					${user.email}
				</td>
			</tr>
	</tbody>
</table>

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
					<a><c:out value="${ad.description}" /></a>
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
