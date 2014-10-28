<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<form:form commandName="message" cssClass="form-horizontal registrationForm">

	<c:if test="${param.success eq true}">
		<div class="alert alert-success">Message sent!</div>
	</c:if>

	<div class="form-group">
		<label for="sender" class="col-sm-2 control-label">From:</label>
		<div class="col-sm-10">
			<form:input path="sender" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="recipient" class="col-sm-2 control-label">To:</label>
		<div class="col-sm-10">
			<form:input path="recipient" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="title" class="col-sm-2 control-label">Title:</label>
		<div class="col-sm-10">
			<form:password path="title" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="message" class="col-sm-2 control-label">Message:</label>
		<div class="col-sm-10">
			<form:input path="message" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="pager">
			<input type="submit" value="Save" class="btn btn-lg btn-primary" />
		</div>
	</div>
</form:form>