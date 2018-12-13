<%@page import="orm.dto.ProjectDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/fragments/head.html"%>
<body>
	<%@ include file="/fragments/navbar.html" %>
	<%List<ProjectDto> projectDtos = (List<ProjectDto>) request.getAttribute("projectDtos"); %>
	<%Integer n = ((Integer)request.getAttribute("numOfPages"))+1; %>
	<div>
		<h4>Prototype>Projects</h4>
		<hr>
		<h3 style="display:inline-block;width:80%">Projects(<%=request.getAttribute("projectsCount") %>)</h3>
		<a href="/projects/new">Add Project</a>
		<div>
			<form style="display:inline-block;"  method="GET" action="/projects/all">
				<h4 style="display:inline-block">Filters</h4>
				<label for="dateFrom">Date from</label>
				<%if(request.getParameter("dateFrom")!=null) { %>
					<input type="date" name="dateFrom" id="dateFrom" value="<%=request.getParameter("dateFrom")%>">
				<%}else{%>
					<input type="date" name="dateFrom" id="dateFrom">
				<%}%>
				<label for="dateTo">Date to</label>
				
				<%if(request.getParameter("dateTo")!=null){%>
					<input type="date" name="dateTo" id="dateTo" value="<%=request.getParameter("dateTo")%>">
				<%}else{%>
					<input type="date" name="dateTo" id="dateTo">
				<%}%>
				<select name="status" id="status">
					
					<%if(request.getParameter("status")==null){%>
						<option value="All">All</option>
						<option value="New">New</option>
						<option value="In Progress">In Progress</option>
						<option value="Finished">Finished</option>
					<%}else{%>
						<option value="All">All</option>
						<%if(request.getParameter("status").equals("New")){%>
							<option value="New" selected>New</option>
						<%}else{%>
							<option value="New">New</option>
						<%}
						if(request.getParameter("status").equals("In Progress")){%>
							<option value="In Progress" selected>In Progress</option>
						<%}else{%>
							<option value="In Progress">In Progress</option>
						<%}
						if(request.getParameter("status").equals("Finished")){%>
							<option value="Finished" selected>Finished</option>
						<%}else{%>
							<option value="Finished">Finished</option>
						<%}
					}%>
				</select>
				<select name="page" id="page">
					<%
						for(int i=1;i<n;i++){
					%>
						
						<%if(request.getParameter("page")!=null && !request.getParameter("page").equals("null") && Integer.parseInt(request.getParameter("page"))==i){%>
							<option selected value="<%=i%>"> <%=i%></option>
						<%}else{%>
							<option value="<%=i%>"> <%=i%></option>
						<%}%>
					<%}%>
				</select>
				<label for="numPerPage">Results per page</label>
				<select name="numPerPage" id="numPerPage">
					<%if(request.getParameter("numPerPage")==null){%>
					<option value="5">5</option>
					<option value="20">20</option>
					<option value="50">50</option>
					<%}else{%>
						<%if(request.getParameter("numPerPage").equals("5")){%>
							<option value="5" selected>5</option>
						<%}else{%>
							<option value="5">5</option>
						<%}
						if(request.getParameter("numPerPage").equals("20")){%>
							<option value="20" selected>20</option>
						<%}else{%>
							<option value="20">20</option>
						<%}
						if(request.getParameter("numPerPage").equals("50")){%>
							<option value="50" selected>50</option>
						<%}else{%>
							<option value="50">50</option>
						<%}
					}%>
				</select>
				<button type="submit">Apply</button>
				
				
			</form>
			<button style="display:inline-block;" onclick="window.location.href='/projects/all?dateFrom=null&dateTo=null&status=null&page=1'">Clear filters</button>
		
		</div>
		<div>
			<form method="POST" action="/projects/all">
				<table style="width:100%">
					<tr>
						<th><input type="checkbox" onClick="toggle(this)"></th>
						<th>#</th>
						<th>Created</th>
						<th>Project name</th>
						<th>Company name</th>
						<th>Project manager</th>
						<th>Products</th>
						<th>Status</th>
						<th>*</th>
					</tr>
					<%for(int i = 0; i < projectDtos.size(); i++) {%>
						<tr>
							<td><input type="checkbox" name="boxes" onClick="showAction()" value="<%=projectDtos.get(i).getId() %>"></td>
							<td><%=projectDtos.get(i).getId() %></td>
							<td><%=projectDtos.get(i).getCreatedOn() %></td>
							<td><%=projectDtos.get(i).getProjectName() %></td>
							<td><%=projectDtos.get(i).getCompanyName() %></td>
							<td><%=projectDtos.get(i).getProjectManager() %></td>
							<td><%=projectDtos.get(i).getProductsCount() %></td>
							<td><%=projectDtos.get(i).getStatus() %></td>
							<td><a href="/projects/view?id=<%=projectDtos.get(i).getId() %>">view</a></td>
						</tr>
					<%} %>
				</table>
				<div id="actionTab" style="display:none">
					<h4 style="display:inline-block">Action</h4>
					<select name="action" id="action">
						<option value="Change to New">Change status to New</option>
						<option value="Change to In progress">Change status to In progress</option>
						<option value="Change to Finished">Change status to Finished</option>
						<option value="Delete">Delete</option>
					</select>
					<button type="submit">Apply</button>
				</div>
			</form>
		</div>
	</div>
<script>
function toggle(source) {
	checkboxes = document.getElementsByName('boxes');
	for(var i=0, n=checkboxes.length;i<n;i++) {
	    checkboxes[i].checked = source.checked;
	}
	showAction();
}
function showAction(){
	checkboxes = document.getElementsByName('boxes');
	actionTab = document.getElementById('actionTab');
	for(var i=0, n=checkboxes.length;i<n;i++) {
	    if(checkboxes[i].checked){
	    	actionTab.style.display = "block";
	    	break;
	    }else{
	    	actionTab.style.display = "none";
	    }
	}
}
</script>
</body>
</html>