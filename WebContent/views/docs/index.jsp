<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>

<jsp:include page="../shared/header.jsp" />
<style>
	.btn{
		padding : 0 !important;
		display : inline !important;
	}
</style>
<div role="main" class="container" style="margin-top: 100px;">
	<h3>
		<a class="btn float-right" href="${pageContext.request.contextPath}/docs?action=add">
			<i class="fa fa-2x fa-plus-square"></i>
		</a>
		Documents : ${title}
	</h3>
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">#</th>
				<th scope="col">Name</th>
				<th scope="col">Path</th>
				<th scope="col">Type</th>
				<th scope="col">Owner (User)</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${items}" var="item">
				<tr>
					<th scope="row">${item.id}</th>
					<td>${item.name}</td>
					<td>${item.path}</td>
					<td>${item.type}</td>
					<td>${item.user}</td>
					<td>					
						<a class="btn float-right" href="${pageContext.request.contextPath}/docs?action=update&id=${item.id}">
							<i class="fa fa-pencil" aria-hidden="true"></i>
						</a>
						<a class="btn" href="${pageContext.request.contextPath}/docs?action=delete&id=${item.id}">
							<i class="fa fa-trash" aria-hidden="true"></i>
						</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>
<jsp:include page="../shared/footer.jsp" />   