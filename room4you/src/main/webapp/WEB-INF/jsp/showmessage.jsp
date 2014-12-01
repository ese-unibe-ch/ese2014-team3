<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>


<h3>Chat with ${recipient.name}</h3>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title"> 
	 Title: ${messageTitle}  
    </h3>
    </div>
		<c:forEach items="${messages}" var="message">
		  <div class="panel-body">
		  	<div 
		  	
		  	<c:choose>
			    <c:when test="${message.sender.name == recipient.name}">
			       class="alert alert-success"
			    </c:when>
			    <c:otherwise>
			        class="alert alert-info"
			    </c:otherwise>
			</c:choose>
		  	>
		  	
		  		[${message.sender.name} -  <fmt:formatDate value="${message.timestamp}" pattern="yyyy-MM-dd HH:mm"/>] :<br> ${message.message}
		  	</div>		     
		  </div>		
		</c:forEach>
		
		<div class="panel-footer">
			<form:form action="/room4you/reply/${messageId}.html" commandName="newmessage">
			       
						<input type="hidden" path="sender" value="${pageContext.request.userPrincipal.name}"/>
										
						<input type="hidden" path="sender" value="${recipient}"/>						
										
						<div class="form-group">
							<label for="message" class="col-sm-2 control-label">Message:</label>
							<div class="col-sm-10">
								<textarea name="message" path="message" id="txtArea" rows="10" cols="70"></textarea>
							</div>
						</div>
						<div class="form-group">
							<div class="pager">
								<input type="submit" value="Send" class="btn btn-lg btn-primary" />
							</div>
						</div>
						
						<div class="form-group">
							<div class="pager">
								<a class="btn btn-lg btn-primary" href="javascript:location.reload(true)">Receive</a>
							</div>
						</div>
			    
			</form:form>		
		
		</div>		
</div>





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

// $(document).ready(function(){
// 	  setInterval(function(){
// 		  if(document.getElementById("txtArea").value == ''){
// 			  window.location.reload(1);
// 		  }
// 	  },5000);
// 	});

</script>