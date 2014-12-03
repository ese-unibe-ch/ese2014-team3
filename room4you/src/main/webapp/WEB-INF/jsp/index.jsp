<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>


<div id="carousel" class="carousel slide" data-ride="carousel" data-interval="5000">
 
  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
      <img src="http://i.imgur.com/DHsDaCq.gif" alt="logo room4you">
    </div>
    <div class="item">
      <img src="http://i.imgur.com/qlcAoMu.jpg" alt="Bern">
      <div class="carousel-caption">
          <h3>Bern</h3>
      </div>
    </div>
    <div class="item">
      <img src="http://i.imgur.com/rdvIhvn.jpg" alt="Basel">
      <div class="carousel-caption">
          <h3>Basel</h3>
      </div>
    </div>
    <div class="item">
      <img src="http://i.imgur.com/H1ncZ0n.jpg" alt="Genf">
      <div class="carousel-caption">
          <h3>Genf</h3>
      </div>
    </div>
    <div class="item">
      <img src="http://i.imgur.com/8jeMbry.jpg" alt="Luzern">
      <div class="carousel-caption">
          <h3>Luzern</h3>
      </div>
    </div>
  </div>
 
  <!-- Controls -->
  <a class="left carousel-control no-background" href="#carousel-example-generic" role="button" data-slide="prev">
  </a>
  <a class="right carousel-control no-background" href="#carousel-example-generic" role="button" data-slide="next">
  </a>
</div> <!-- Carousel -->

<div class="jumbotron home-container">
	<div class="left-area float-left">
		<p>Welcome to Room4You, the new place to find the best room for you!</p>
  		<p>You can freely browse available ads and look at their properties. To interact with the advertiser a free registration is necessary. However a room4you
 		account is created in no time.</p>
	</div>
  	<div class="right-area float-left">
  		<a class="btn btn-primary btn-lg btn-home" href="ads.html" role="button">Browse ads</a>
  		<security:authorize access="! isAuthenticated()">
  			<a class="btn btn-primary btn-lg btn-home" href="<spring:url value="/register.html" />">Register</a>
  		</security:authorize>
  	</div>
</div>
