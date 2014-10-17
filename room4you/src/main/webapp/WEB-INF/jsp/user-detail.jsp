<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1><c:out value="${user.name}" /></h1>




<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>ID</th>
			<th>User Name</th>
			<th>User Email</th>
		</tr>
	</thead>
	<tbody>
			<tr>
				<td>
					${user.id}
				</td>
				<td>
					${user.name}
				</td>
				<td>
					${user.email}
				</td>
			</tr>
	</tbody>
</table>

<!-- Nav tabs -->
<ul class="nav nav-tabs">
	<c:forEach items="${user.ads}" var="ad">
	  <li><a href="#ad_${ad.id}" data-toggle="tab"><c:out value="${ad.title}" /></a></li>
	</c:forEach>
</ul>

<!-- Tab panes -->
<div class="tab-content">
<c:forEach items="${user.ads}" var="ad">
  <div class="tab-pane" id="ad_${ad.id}">
	<h1><c:out value="${ad.title}" /></h1>
	<p>
	<h2><c:out value="${ad.description}" /></h1>
	<p>
	
	<a href="<spring:url value="/ad/remove/${ad.id}.html" />" class="btn btn-danger triggerRemove">remove ad</a>

	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th>ID</th>
				<th>Image</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ads.images}" var="image">
				<tr>
					<td><c:out value="${image.id}" /></td>
					<td><c:out value="${image.image}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  </div>
</c:forEach>
</div>


<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Remove ad</h4>
      </div>
      <div class="modal-body">
        Really remove?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>