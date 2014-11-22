<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>


<div class="jumbotron">
  ${application.sender.name}
  ${application.recipient.name}
  ${application.title}
  ${application.appointDate}
  ${application.message}
</div>

	
