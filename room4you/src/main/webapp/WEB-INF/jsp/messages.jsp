<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<c:set var="name" value="${nbrUnreadMessages}" scope="session" />
	


<!-- Collect the nav links, forms, and other content for toggling -->
<ul class="nav nav-tabs">
 	 <li class="active"><a href="#section-1" data-toggle="tab">Inbox <span class="badge"><c:out value="${name}" /></span></a></li>
  	<li><a href="#section-2" data-toggle="tab">Sent items</a></li>
</ul>

<div class="tab-content">
  <div class="tab-pane active" id="section-1">  

			<h2 id="section-4">Inbox</h2>
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
									    	<strong>From:&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									       <strong><c:out value="${message.sender.name}" /></strong>
									    </c:when>								    
									    <c:otherwise>
									    	From: &nbsp;&nbsp;&nbsp;&nbsp;
									        <c:out value="${message.sender.name}" />
									    </c:otherwise>
									  </c:choose>		</span>
								        
								        <span class="text-muted pull-right"><a class="pull-right" href="<spring:url value="/showmessage/${message.id}.html" />">
										<c:choose>
										    <c:when test="${message.unRead}">
										    	<strong>Received:&nbsp;</strong>
										       <strong><fmt:formatDate value="${message.timestamp}" pattern="yyyy-MM-dd HH:mm"/> </strong>
										    </c:when>								    
										    <c:otherwise>
										    	Received:&nbsp;
										       <fmt:formatDate value="${message.timestamp}" pattern="yyyy-MM-dd HH:mm"/>
										    </c:otherwise>
										</c:choose>								
									  </a></span></a>
									  <br><br>
									  <div class="btn-group pull-right">
										   <a href="<spring:url value="/reply/${message.id}.html" />" class="btn btn-default btn-sm">
												Reply 
										   </a>
												<c:choose>
												    <c:when test="${message.unRead}">
												    	<a href="<spring:url value="/message/markAsRead/${message.id}.html" /> "class="btn btn-default btn-sm"> 
												    	Mark as read</a>	
												    </c:when>								    
												    <c:otherwise>
												    	<a href="<spring:url value="/message/markAsUnRead/${message.id}.html" /> "class="btn btn-default btn-sm"> 
												    	Mark as unread</a>	
												    </c:otherwise>
												</c:choose>	
									       <a href="<spring:url value="/message/remove/${message.id}.html" />"
												class="btn btn-danger btn-sm triggerRemove"> 
												Delete Message 
										   </a>	
								     </div>		
      							     <br>						  								  									  
									  
									  <a href="<spring:url value="/ads/${message.messageAd.id}.html" />">
											<c:choose>
											    <c:when test="${message.unRead}">
											    	<strong>Subject:&nbsp;&nbsp;&nbsp;</strong>
											       <strong><c:out value="${message.messageAd.title}" /></strong>
											    </c:when>								    
											    <c:otherwise>
											    	Subject:&nbsp;&nbsp;&nbsp;
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
	</div><!-- @end #section1 -->

	<div class="tab-pane" id="section-2">	
		<h2>Sent items</h2>
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
									    	<strong>To:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>
									       <strong><c:out value="${message.sender.name}" /></strong>
									    </c:when>								    
									    <c:otherwise>
									        To: <c:out value="${message.sender.name}" />
									    </c:otherwise>
									  </c:choose>		</span>
								        
								        <span class="text-muted pull-right"><a class="pull-right" href="<spring:url value="/showmessage/${message.id}.html" />">
										<c:choose>
										    <c:when test="${message.unRead}">
										    	<strong>Sent:&nbsp;</strong>
										       <strong><fmt:formatDate value="${message.timestamp}" pattern="yyyy-MM-dd HH:mm"/> </strong>
										    </c:when>								    
										    <c:otherwise>
										    	Sent:&nbsp;
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
											    	<strong>Subject: &nbsp;&nbsp;&nbsp;</strong>
											       <strong><c:out value="${message.messageAd.title}" /></strong>
											    </c:when>								    
											    <c:otherwise>
											    	Subject: &nbsp;&nbsp;&nbsp;
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
		</div><!-- @end #section2 -->
		
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
</script>


<script>
	$(document).ready(
			function() {
				$(".triggerRemove").click(
						function(e) {
							e.preventDefault();
							$("#modalRemove .removeBtn").attr("href",
									$(this).attr("href"));
							$("#modalRemove").modal();
						});				
			});
</script>

<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Remove ad</h4>
			</div>
			<div class="modal-body">Really remove?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				<a href="" class="btn btn-danger removeBtn">Remove</a>
			</div>
		</div>
	</div>
</div>