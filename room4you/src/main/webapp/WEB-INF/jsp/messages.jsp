<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>


			<h2 id="section-4">Messages received</h2>
				<c:forEach items="${userm.messages}" var="message">
							<div class="panel 
										<c:choose>
										<c:when test="${message.unRead}">
									       panel-warning
									    </c:when>								    
									    <c:otherwise>
									        panel-default
									    </c:otherwise>	
									    </c:choose>">					
								  <div class="panel-heading" >
									   <a><span class="pull-left">
								        <c:choose>
									    <c:when test="${message.unRead}">
									       <strong><c:out value="${message.sender.name}" /></strong>
									    </c:when>								    
									    <c:otherwise>
									        <c:out value="${message.sender.name}" />
									    </c:otherwise>
									  </c:choose>		</span>
								        
								        <span class="text-muted pull-right"><a class="pull-right" href="<spring:url value="/showmessage/${message.id}.html" />">
										<c:choose>
										    <c:when test="${message.unRead}">
										       <strong><fmt:formatDate value="${message.timestamp}" pattern="yyyy-MM-dd HH:mm"/> </strong>
										    </c:when>								    
										    <c:otherwise>
										       <fmt:formatDate value="${message.timestamp}" pattern="yyyy-MM-dd HH:mm"/>
										    </c:otherwise>
										</c:choose>								
									  </a></span></a>
									  <br><br>
									  <div class="btn-group pull-right">
										   <a href="<spring:url value="/reply/${message.id}.html" />" class="btn btn-default btn-sm">
												Reply 
										   </a>
									       <a href="<spring:url value="/message/remove/${message.id}.html" />"
												class="btn btn-danger btn-sm triggerRemove"> 
												Delete Message 
										   </a>	
								     </div>		
      							     <br>						  								  									  
									  
									  <a href="<spring:url value="/ads/${message.messageAd.id}.html" />">
											<c:choose>
											    <c:when test="${message.unRead}">
											       <strong><c:out value="${message.messageAd.title}" /></strong>
											    </c:when>								    
											    <c:otherwise>
											        <c:out value="${message.messageAd.title}" />
											    </c:otherwise>
											</c:choose>								
									  </a>
									  </div>
									  <div class="panel-body">					  
											<a href="<spring:url value="/showmessage/${message.id}.html"/>">
												<c:out value="${message.message}" />							
											</a>						  							  
									  </div>
							</div>
				</c:forEach>

		
		<h2>Messages sent</h2>
					<c:forEach items="${userm.sentMessages}" var="message">
							<div class="panel 
										<c:choose>
										<c:when test="${message.unRead}">
									       panel-warning
									    </c:when>								    
									    <c:otherwise>
									        panel-default
									    </c:otherwise>	
									    </c:choose>">					
								  <div class="panel-heading" >
									   <a><span class="pull-left">
								        <c:choose>
									    <c:when test="${message.unRead}">
									       <strong><c:out value="To: ${message.sender.name}" /></strong>
									    </c:when>								    
									    <c:otherwise>
									        To: <c:out value="${message.sender.name}" />
									    </c:otherwise>
									  </c:choose>		</span>
								        
								        <span class="text-muted pull-right"><a class="pull-right" href="<spring:url value="/showmessage/${message.id}.html" />">
										<c:choose>
										    <c:when test="${message.unRead}">
										       <strong><fmt:formatDate value="${message.timestamp}" pattern="yyyy-MM-dd HH:mm"/> </strong>
										    </c:when>								    
										    <c:otherwise>
										       <fmt:formatDate value="${message.timestamp}" pattern="yyyy-MM-dd HH:mm"/>
										    </c:otherwise>
										</c:choose>								
									  </a></span></a>
									  <br><br>
									  <div class="btn-group pull-right">
									       <a href="<spring:url value="/message/remove/${message.id}.html" />"
												class="btn btn-danger btn-sm triggerRemove"> 
												Delete Message 
										   </a>	
								     </div>		
      							     <br>						  								  									  
									  
									  <a href="<spring:url value="/ads/${message.messageAd.id}.html" />">
											<c:choose>
											    <c:when test="${message.unRead}">
											       <strong><c:out value="${message.messageAd.title}" /></strong>
											    </c:when>								    
											    <c:otherwise>
											        <c:out value="${message.messageAd.title}" />
											    </c:otherwise>
											</c:choose>								
									  </a>
									  </div>
									  <div class="panel-body">					  
											<a href="<spring:url value="/showmessage/${message.id}.html"/>">
												<c:out value="${message.message}" />							
											</a>						  							  
									  </div>
							</div>
				</c:forEach>
		
	

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