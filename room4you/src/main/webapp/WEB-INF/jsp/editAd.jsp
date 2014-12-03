<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>


<form:form method="post" modelAttribute="ad" cssClass="form-horizontal adForm" enctype="multipart/form-data">
	<h4>Edit Ad</h4>
	<div id="editForm">
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
				<form:input path="street" cssClass="form-control"/>
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
				<form:select path="nbrRooms" cssClass="formControl" style="width:100%;"
					tabindex="6">
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
			<label for="name" class="col-sm-2 control-label">Number of room mates:</label>
			<div class="col-sm-10">
				<form:select path="nbrRooms" cssClass="formControl" style="width:100%;"
					tabindex="6">
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
			<label for="name" class="col-sm-2 control-label">Pets allowed:</label>
			<div class="col-sm-10">
				<form:checkbox path="petsAllowed" cssClass="form-control" style="border-style:none;"/>
				<form:errors path="petsAllowed" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Smoking allowed:</label>
			<div class="col-sm-10">
				<form:checkbox path="smokingAllowed" cssClass="form-control" style="border-style:none;"/>
				<form:errors path="smokingAllowed" />
			</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Instruments allowed:</label>
			<div class="col-sm-10">
				<form:checkbox path="instrumentsAllowed" cssClass="form-control" style="border-style:none;"/>
				<form:errors path="instrumentsAllowed" />
			</div>
		</div>
		
		<div class="form-group">
						<label for="name" class="col-sm-2 control-label">We are looking for:</label>
						<div class="col-sm-10">
							<form:input path="weAreLookingFor"
								cssClass="form-control" />
							<form:errors path="weAreLookingFor" />
						</div>
		</div>

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Additional Information:</label>
			<div class="col-sm-10">
				<form:input path="additionalInformation" cssClass="form-control" />
				<form:errors path="additionalInformation" />
			</div>
		</div>
	<!--  	<c:forEach items="${appointments}" var="appointment">
		<div class="form-group" id="firstDatePicker">
						<label for="name" class="col-sm-2 control-label">Appointment:</label>
						<div class="col-sm-10">
						<p>Date: <a id="datetimepicker1" class="input-append"><input
								data-format="dd-MM-yyyy" type="date" name="appointDate"></input>
								
							</a></p><p>Begin: <a id="datetimepicker2" class="input-append"> <input
								type="time" name="startTime"></input>
								
							</a></p><p>End:  <a id="datetimepicker3" class="input-append"> <input
								type="time" name="endTime"></input>
							</a></p>
							<p></p>
						</div>
						<label for="name" class="col-sm-2 control-label">Number of
							Visitors:</label>
						<div class="col-sm-10">
							<input name="appointments" class="form-control" />
						</div>
						<a href="<spring:url value="/ad/${ad.id}/deleteAppointment/${appointment.id}.html" />"
									class="btn btn-danger triggerRemove"> Delete Appointment </a>
					</div>
		</c:forEach>
					<button id="addAppointment" type="button"
								class="btn btn-default btn-sm right-block">
								<span class="glyphicon glyphicon-plus"></span>
					</button>
		-->
		
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Image:</label>
			<div class="col-sm-10">
				<input type="file" name="image[]" cssClass="form-control" />
				<button class="addFile" type="button" class="btn btn-default btn-sm right-block">
				 	<span class="glyphicon glyphicon-plus"></span>
				</button>	
			</div>
		</div>
	</div>
	<div class="form-group">
		<div class="pager">
			<input type="submit" value="Save" class="btn btn-lg btn-primary" />
		</div>
	  </div>   
</form:form>


<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Remove Appointment</h4>
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
$(document).ready(function() {
    //add more file components if Add is clicked
    $('.addFile').click(function() {
        var fileIndex = $('#fileTable tr').children().length - 1;
        $('#editForm').append(
        		'<div class="form-group">'+
				'<label for="name" class="col-sm-2 control-label">Image:</label>'+
				'<div class="col-sm-10">'+
					'<input type="file" name="image[]" cssClass="form-control" />'+
				'</div>');
    });
     
});
</script>