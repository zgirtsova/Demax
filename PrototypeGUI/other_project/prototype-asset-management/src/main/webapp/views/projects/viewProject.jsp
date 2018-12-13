<%@page import="orm.dto.ViewProjectDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/fragments/head.html"%>
<body>
	<%@ include file="/fragments/navbar.html" %>
	<%ViewProjectDto projectDto = (ViewProjectDto) request.getAttribute("projectDto"); %>
	<div>
		<h4>Prototype>Projects>View project</h4>
		<hr>
		<div style="display:block;width:100%;">
			<form action="/projects/view" method="POST" onsubmit="return validateForm()">
				<div style="display:inline-block;width:50%"> 
					<h3>Project</h3>
				</div>
				<div style="display:inline-block">
					<button type="reset">CANCEL</button>
					<button type="submit">EDIT &amp; SAVE</button>
				</div>
				<div style="display:block">
					<h3><%=projectDto.getProjectName() %></h3>
					<br>
					<p>Please review or edit the project details.</p>
					<hr>
					<div style="display:inline-block;width:50%">
						<input type="hidden" name="id" id="id" value="<%=projectDto.getId() %>" required maxlength="50">
						<label for="projectName">Project name</label>
						<br>
						<input type="text" name="projectName" id="projectName" value="<%=projectDto.getProjectName() %>" required maxlength="50">
						<br>
						<label for="companyName">Company name</label>
						<br>
						<input type="text" name="companyName" id="companyName" value="<%=projectDto.getCompanyName() %>" required maxlength="50">
						<br>
						<label for="projectManager">Project manager</label>
						<br>
						<input type="text" name="projectManager" id="projectManager" value="<%=projectDto.getProjectManager() %>" required maxlength="50">
						<br>
						<label for="status">Status</label>
						<br>
					    <select name="status" id="status">
							
							<%if(projectDto.getStatus().equals("New")){%>
								<option value="New" selected>New</option>
							<%}else{%>
								<option value="New">New</option>
							<%}
							if(projectDto.getStatus().equals("In progress")){%>
								<option value="In progress" selected>In progress</option>
							<%}else{%>
								<option value="In progress">In progress</option>
							<%}
							if(projectDto.getStatus().equals("Finished")){%>
								<option value="Finished" selected>Finished</option>
							<%}else{%>
								<option value="Finished">Finished</option>
								<%}%>
						</select>
					</div>
					<div style="display:inline-block;width:45%;position:relative;bottom:163px;">
						<p>Attached products (<%=projectDto.getProducts().size() %>)</p>
						<div>
							<table style="width:100%">
								<tr>
									<th style="width:15%">#</th>
									<th style="width:15%">Product name</th>
									<th style="width:15%">SN</th>
									<th style="width:15%">Qty</th>
									<th style="width:15%">Picture</th>
									<th style="width:15%">Status</th>
									<th>*</th>
								</tr>
								<%for(int i = 0; i < projectDto.getProducts().size(); i++) {%>
									<tr>
										<td><%=projectDto.getProducts().get(i).getId() %></td>
										<td><%=projectDto.getProducts().get(i).getName() %></td>
										<td><%=projectDto.getProducts().get(i).getSerialNumber() %></td>
										<td><%=projectDto.getProducts().get(i).getQuantity() %></td>
										<td><img src="<%="data:image/*;base64," + projectDto.getProducts().get(i).getPicture() %>" width="50"></td>
										<td><%=projectDto.getProducts().get(i).getStatus() %></td>
										<td><a href="/products/view?id=<%=projectDto.getProducts().get(i).getId() %>">view</a></td>
									</tr>
								<%} %>
							</table>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>