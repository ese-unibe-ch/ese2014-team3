<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>


<form:form commandName="alert" class="form-horizontal" role="form" method="post" >

	<c:if test="${param.success eq true}">
		<div class="alert alert-success">Alert subscription successfull!</div>
	</c:if>


	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">ZIP:</label>
		<div class="col-sm-9">
			<form:input path="zip" cssClass="form-control alert-input" />
			<form:errors path="zip" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">City:</label>
		<div class="col-sm-9">
			<form:input path="city" cssClass="form-control alert-input" />
			<form:errors path="city" />
		</div>
	</div>

  	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">Rent per month from</label>
		<div class="col-sm-9">
			<form:input path="rentPerMonthMin" cssClass="form-control alert-input" />
			<form:errors path="rentPerMonthMin" />
		</div>
	</div>
	
	 <div class="form-group">
		<label for="name" class="col-sm-3 control-label">Rent per month to</label>
		<div class="col-sm-9">
			<form:input path="rentPerMonthMax" cssClass="form-control alert-input" />
			<form:errors path="rentPerMonthMax" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">Number of room mates from</label>
		<div class="col-sm-9">
			<form:input path="nbrRoomsMatesMin" cssClass="form-control alert-input" />
			<form:errors path="nbrRoomsMatesMin" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">Number of room mates to</label>
		<div class="col-sm-9">
			<form:input path="nbrRoomsMatesMax" cssClass="form-control alert-input" />
			<form:errors path="nbrRoomsMatesMax" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">Number of rooms from</label>
		<div class="col-sm-9">
			<form:input path="nbrRoomsMin" cssClass="form-control alert-input" />
			<form:errors path="nbrRoomsMin" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="name" class="col-sm-3 control-label">Number of rooms to</label>
		<div class="col-sm-9">
			<form:input path="nbrRoomsMax" cssClass="form-control alert-input" />
			<form:errors path="nbrRoomsMax" />
		</div>
	</div>
  
  <button  type="submit" class="btn btn-default align-center">Subscribe</button> 
</form:form>


