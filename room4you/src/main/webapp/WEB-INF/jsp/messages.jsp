<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>


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


<form:form commandName="newmessage" cssClass="form-horizontal registrationForm">

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
			<form:input path="title" cssClass="form-control" width="100px"/>
		</div>
	</div>
	<div class="form-group">
		<label for="message" class="col-sm-2 control-label">Message:</label>
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