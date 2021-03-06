<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="../layout/taglib.jsp"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%@ taglib prefix="fn" 
           uri="http://java.sun.com/jsp/jstl/functions" %>

<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >

<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">
	
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2-bootstrap.min.css">
	
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.css">
	

<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/select2/3.5.2/select2.min.js"></script>


<script type="text/javascript" 
		src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	
	
	
<%-- <script src="${pageContext.request.contextPath}/resources/js/style.css"></script>	 --%>
	

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
</head>
<body>

<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>


<tilesx:useAttribute name="current"/>


<div class="container">

  <!-- Static navbar -->
      <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<spring:url value="/" />">Room4You</a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="${current == 'index' ? 'active' : ''}"><a href='<spring:url value="/" />'>Home</a></li>
              <security:authorize access="hasRole('ROLE_ADMIN')">
              	<li class="${current == 'users' ? 'active' : ''}"><a href="<spring:url value="/users.html" />">Users</a></li>
              </security:authorize>
              <li class="${current == 'ads' ? 'active' : ''}"><a href="<spring:url value="/ads.html" />">Ads</a></li>
              <security:authorize access="! isAuthenticated()">
	              <li class="${current == 'login' ? 'active' : ''}"><a href="<spring:url value="/login.html" />">Login</a></li>
	              <li class="${current == 'register' ? 'active' : ''}"><a href="<spring:url value="/register.html" />">Register</a></li>
              </security:authorize>
              <security:authorize access="isAuthenticated()">              
              	<li class="${current == 'alert' ? 'active' : ''}"><a href="<spring:url value="/alert.html" />">Subscribe alerts</a></li>
              	<li class="${current == 'messages' ? 'active' : ''}"><a href="<spring:url value="/messages.html" />">Messages <span id="nbrMsgs" class="badge"><c:out value="${name}" /></span></a></li>
              	<li class="${current == 'placeAd' ? 'active' : ''}"><a href="<spring:url value="/placeAd.html" />">My ads</a></li>                  	
              	<li class="${current == 'account' ? 'active' : ''}"><a href="<spring:url value="/account.html" />">My account</a></li>  
              	<li><a href="<spring:url value="/logout" />">Logout</a></li>           	
              </security:authorize>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </div>


	<tiles:insertAttribute name="body" />
	<br>
	<br>
	
	
	
	<center>
		<tiles:insertAttribute name="footer" />
	</center>

<script>
   $(document).ready(
            function() {
                setInterval(function() {	
                    $('#nbrMsgs').text(<c:out value="${name}" />);
                }, 1000);
            });
</script>

</div>

</body>
</html>