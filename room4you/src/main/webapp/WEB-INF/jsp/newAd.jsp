<!DOCTYPE HTML>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<head>
	
	<link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
     href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

	<style type="text/css"> 
		<!--
			#form-title{
			font-size: 18px;
			}
			.mystyle input[type="text"] {
			   height: 30px;
			   font-size: 14px;
			   line-height: 18px;
			}
		-->	
	</style>
		
</head>

<body>

	<h1 align="center">Place new Advertisement</h1>
	<hr>

	<form:form method="post" modelAttribute="ad">
		<div class="col-md-3" id="form-title">
			Title:
		</div>
		<div class="col-md-6 mystyle" style="height:">
			<form:input path="title" cssClass="form-control" placeholder="Enter title"/>
			<form:errors path="title" />
		</div>
		
		<br><br><br>
		
		<div class="col-md-3" id="form-title">
			Description:
		</div>
		<div class="col-md-6 mystyle">
			<form:input path="description" cssClass="form-control" placeholder="Enter description"/>
			<form:errors path="description" />
		</div>
		
		<br><br><br>
		
		<div class="col-md-3" id="form-title">
			Street:
		</div>
		<div class="col-md-6 mystyle">
			<form:input path="street" cssClass="form-control" placeholder="Enter street"/>
			<form:errors path="street" />
		</div>
		
		<br><br><br>
		
		<div class="col-md-3" id="form-title">
			ZIP:
		</div>
		<div class="col-md-6 mystyle">
			<form:input path="zip" cssClass="form-control" placeholder="Enter ZIP"/>
			<form:errors path="zip" />
		</div>
		
		<br><br><br>
	
		<div class="col-md-3" id="form-title">
			City:
		</div>
		<div class="col-md-6 mystyle">
			<form:input path="city" cssClass="form-control" placeholder="Enter city"/>
			<form:errors path="city" />
		</div>
		
		<br><br><br>
		
		<div class="col-md-3" id="form-title">
			Number of rooms:
		</div>
		<div class="col-md-6">
			<form:select path="nbrRooms" cssClass="formControl" style="width:100%;"
				tabindex="6">
				<option value="1">1</option>
				<option value="1.5">1.5</option>
				<option value="2">2</option>
				<option value="2.5">2.5</option>
				<option value="3">3</option>
				<option value="3.5">3.5</option>
			</form:select>
		</div>
		
		<br><br><br>
		
		<div class="col-md-3" id="form-title">
			Number of roommates:
		</div>
		<div class="col-md-6">
			<form:select path="nbrRoomsMates" cssClass="formControl" style="width:100%;"
				tabindex="6">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
			</form:select>
		</div>
		
		<br><br><br>
		
		<div class="col-md-3" id="form-title">
			Available From:
		</div>
		<div class="col-md-6 mystyle">
			<div id="datetimepicker4" class="input-append date">
		      <input data-format="yyyy-MM-dd" type="text"></input>
		      <span class="add-on" style="height:30px">
		        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
		      </span>
		    </div>
		</div>
		
		<br><br><br>
		
		<div class="col-md-3" id="form-title">
			Rent per month:
		</div>
		<div class="col-md-6 mystyle">
			<div class="input-group">
				<span class="input-group-addon">CHF</span>
 					<form:input path="rentPerMonth" cssClass="form-control" />
					<form:errors path="rentPerMonth" />
  				<span class="input-group-addon">.00</span>		
  			</div>
		</div>
		
		<br><br><br>
		
		<div class="col-md-3" id="form-title">
			Pets allowed:
		</div>
		<div class="col-md-1">
			<form:checkbox path="petsAllowed" cssClass="form-control" style="border-style:none;"/>
			<form:errors path="petsAllowed" />
		</div>
		
		<br><br><br>
		
		<div class="col-md-3" id="form-title">
			Smoking allowed:
		</div>
		<div class="col-md-1">
			<form:checkbox path="smokingAllowed" cssClass="form-control" style="border-style:none;"/>
			<form:errors path="smokingAllowed" />
		</div>
		
		<br><br><br>
		
		<div class="col-md-3" id="form-title">
			Instruments allowed:
		</div>
		<div class="col-md-1">
			<form:checkbox path="instrumentsAllowed" cssClass="form-control" style="border-style:none;"/>
			<form:errors path="instrumentsAllowed" />
		</div>
		
		<br><br><br>	
			
		<div class="col-md-3" id="form-title">
			Additional Information:
		</div>
		<div class="col-md-6">
			<form:textarea path="additionalInformation"  style="height:80px" cssClass="form-control" placeholder="Enter additional information"/>
			<form:errors path="additionalInformation" />
		</div>
		
		<br><br><br><br><br>
		
		<div class="col-md-3" id="form-title">
			Images:
		</div>
		<div class="col-md-4">
			
		</div>
			
		<br><br><br>
		
        <input type="submit" class="btn btn-primary" value="Save" />
        
	</form:form>
	
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
    
       <script type="text/javascript"
     src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
    </script> 
    <script type="text/javascript"
     src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
    </script>
    <script type="text/javascript"
     src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
    </script>
    <script type="text/javascript"
     src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
    </script>
    <script type="text/javascript">
      $('#datetimepicker4').datetimepicker({
        pickTime: false
      });
    </script>
    
</body>    

