<%@page import="orm.dto.ActionDto"%>
<%@page import="orm.dto.DashboardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/fragments/head.html"%>
<body>
	<% DashboardDto dashboardDto = (DashboardDto) request.getAttribute("dashboardDto"); %>
	<%@ include file="/fragments/navbar.html" %>
	<div>
		<h4>General</h4>
		<hr>
		<h3>Dashboard</h3>
		<hr>
		<div style="display:inline-block;width:25%;">
			<h2> <%=dashboardDto.getNewProjectsCount().toString() %></h2>
			<br>
			<p>New projects</p>
			<br>
			<a href="/projects/all">VIEW ALL</a>
			<br>
			<p><%=dashboardDto.getFinishedProjectsCount() %> finished projects</p>
			<br>
			<a href="/projects/new">Add project</a>
		</div>
		<div style="display:inline-block;width:25%;">
			<h2> <%=dashboardDto.getProductsInProgressCount().toString() %></h2>
			<br>
			<p>Products in progress</p>
			<br>
			<a href="/products/all?dateFrom=&dateTo=&status=All&page=1">VIEW ALL</a>
			<br>
			<p><%=dashboardDto.getFinishedProductsCount() %> finished products</p>
			<br>
			<a href="/products/new">Add product</a>
		</div>
		<div style="display:inline-block;">
			<h2>Welcome <%=dashboardDto.getLoggedInUserFirstName() %></h2>
			<br>
			<h3>Activity</h3>
			<p>Last 15 actions:</p>
			<div>
				<% for (ActionDto actionDto : dashboardDto.getLastFifteenActions()) {%>
					<p>
						<%=actionDto.getDate() %>
						<%=actionDto.getUser() %>
						<%=actionDto.getAction() %>
					</p>
				<%} %>
			</div>
		</div>
	</div>
</body>
</html>
