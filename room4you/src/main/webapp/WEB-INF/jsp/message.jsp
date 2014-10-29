<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<form:form commandName="newmessage" cssClass="form-horizontal registrationForm">

	<c:if test="${param.success eq true}">
		<div class="alert alert-success">Message sent!</div>
	</c:if>

	<div class="form-group">
		<label for="sender" class="col-sm-2 control-label">From:</label>
		<div class="col-sm-10">
			<form:label path="sender" cssClass="label label-default">${principal}</form:label>
		</div>
	</div>
	<div class="form-group">
		<label for="recipient" class="col-sm-2 control-label">To:</label>
		<div class="col-sm-10">
			<form:label path="recipient" cssClass="label label-default">${ad.user.name}</form:label>
		</div>
	</div>
	<div class="form-group">
		<label for="title" class="col-sm-2 control-label">Title:</label>
		<div class="col-sm-10">
			<form:input path="title" cssClass="form-control" />
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

<script type="text/javascript">
$(document).ready(function() {
	
	$(".registrationForm").validate(
		{
			rules: {
				title: {
					required : true,
				},
				message: {
					required : true,
				},
			},
			highlight: function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
		}
	);
});
</script>