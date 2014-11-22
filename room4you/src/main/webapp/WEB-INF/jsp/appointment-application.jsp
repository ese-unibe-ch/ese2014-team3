<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>


<form:form commandName="application" cssClass="form-horizontal registrationForm">

	<div class="form-group">
		<label for="sender" class="col-sm-2 control-label">From:</label>
		<div class="col-sm-10">
			<form:label path="sender" cssClass="label label-default">${sender.name}</form:label>
		</div>
	</div>
	<div class="form-group">
		<label for="recipient" class="col-sm-2 control-label">To:</label>
		<div class="col-sm-10">
			<form:label path="recipient" cssClass="label label-default">${recipient.name}</form:label>
		</div>
	</div>
	<div class="form-group">
		<label for="title" class="col-sm-2 control-label">Title:</label>
		<div class="col-sm-10">
			<form:label path="title" cssClass="form-control" width="100px">Appointment Application for Ad: ${ad.title}</form:label>
		</div>
	</div>
	
	<div class="form-group">
		<label for="title" class="col-sm-2 control-label">Appointment:</label>
		<div class="col-sm-10">
			<label cssClass="form-control" width="100px"><p>Date: ${appointment.appointDate.appointDate}</p> <p>Begin: ${appointment.appointDate.startTime}</p> <p>Ende: ${appointment.appointDate.endTime}<p></label>
		</div>
	</div>
	
	
	<div class="form-group">
		<label for="message" class="col-sm-2 control-label">About Me:</label>
		<div class="col-sm-10">
			<textarea name="message" path="message" id="txtArea" rows="10" cols="70"></textarea>
		</div>
	</div>
	<div class="form-group">
		<div class="pager">
			<input type="submit" value="Save" class="btn btn-lg btn-primary" />
		</div>
	</div>
</form:form>
