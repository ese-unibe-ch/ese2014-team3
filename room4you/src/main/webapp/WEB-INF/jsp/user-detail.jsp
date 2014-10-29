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
