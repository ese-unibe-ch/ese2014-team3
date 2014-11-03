<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<div class="jumbotron">
  ${message.sender.name}
  ${message.recipient.name}
  ${message.title}
  ${message.message}
</div>
