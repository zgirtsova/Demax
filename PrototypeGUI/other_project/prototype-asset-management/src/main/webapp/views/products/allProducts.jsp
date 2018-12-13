<%@page import="orm.dto.ProjectIdNameDto"%>
<%@page import="orm.dto.ProductOnePictureWithDescriptionDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/fragments/head.html"%>
<body>
<style>
	a.picWrp{
		float:left;
		width:90px;
		height:65px;
		border:1px solid #d5dce6;
		position:relative;
		border-radius: 4px;
		-webkit-border-radius: 4px;
		-moz-border-radius: 4px;
		
		display: -webkit-flex;
		display: -ms-flexbox;
		display: flex;
		
		justify-content:center;
		-moz-justify-content:center;
		-webkit-justify-content:center;
		
		background:white;
	}

	a.picWrp img{
		width: auto;
		height: auto;
		max-height: 100%;
		max-width: 100%;
	}

	a.picWrp i{
		position:absolute;
		bottom:-4px;
		right:-4px;
		width:25px;
		height:25px;
		background:#5d6371;
		border:2px solid white;
		text-align:center;
		color:white;
		padding-top:4px;
		border-radius: 50%;
		-webkit-border-radius: 50%;
		-moz-border-radius: 50%;
		font-style: normal;
		font-weight: 500;
	}

	a.picWrp:hover var.zoom{
		position:absolute;
		top:10px;
		left:20px;
		width:45px;
		height:45px;
		background:rgba(0, 0, 0, 0.5);
		border-radius: 50%;
		-webkit-border-radius: 50%;
		-moz-border-radius: 50%;
		z-index:2;
	}

	a.picWrp:active var.zoom{
		background:rgba(0, 0, 0, 0.8);
	}

	a.picWrp var.zoom span{
		display:none;
	}
</style>

	<%List<ProductOnePictureWithDescriptionDto> productOnePictureWithDescriptionDto = (List<ProductOnePictureWithDescriptionDto>) request.getAttribute("productDtos");
		Integer n =	((Integer)request.getAttribute("numOfPages"))+1;
		List<ProjectIdNameDto> projectNames = (List<ProjectIdNameDto>)request.getAttribute("projectIdsAndNames");
	 %>
	<%@ include file="/fragments/navbar.html" %>
	<div>
		<h4>Prototype>Products</h4>
		<hr>
		<h3 style="display:inline-block;width:80%">Products(<%=request.getAttribute("productCount") %>)</h3>
		<a href="/products/new">Add Product</a>
		<div>
			<form style="display:inline-block;"  method="GET" action="/products/all">
				<h4 style="display:inline-block">Filters</h4>
				<label for="dateFrom">Date from</label>
				<%if(request.getParameter("dateFrom")!=null){%>
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
				<select name="projectId" id="projectId">
					<%if(request.getParameter("projectId") == null) {%>
						<option value="0" selected>All</option>
						<%for(int i = 0; i < projectNames.size(); i++) {%>
							<option value="<%=projectNames.get(i).getId() %>"><%=projectNames.get(i).getName() %></option>
						<%} %>
					<%} else { %>
						<option value="0">All</option>
						<%for(int i = 0; i < projectNames.size(); i++) {%>
							<%if(projectNames.get(i).getId() == Integer.parseInt(request.getParameter("projectId"))) {%>
								<option value="<%=projectNames.get(i).getId() %>" selected><%=projectNames.get(i).getName() %></option>
							<%} else { %>
								<option value="<%=projectNames.get(i).getId() %>"><%=projectNames.get(i).getName() %></option>
							<%} %>
						<%} %>
					<%} %>
				</select>
				<button type="submit">Apply</button>
			</form>
			<button style="display:inline-block;" onclick="window.location.href='/products/all?dateFrom=null&dateTo=null&status=null&page=1'">Clear filters</button>
		</div>
		<div>
			<form method="POST" action="/products/all">
				<table style="width:100%">
					<tr>
						<th><input type="checkbox" onClick="toggle(this)"></th>
						<th>#</th>
						<th>Created</th>
						<th>Product name</th>
						<th>SN</th>
						<th>Quantity</th>
						<th>Project</th>
						<th>Picture</th>
						<th>Status</th>
						<th>*</th>
					</tr>
					<%for(ProductOnePictureWithDescriptionDto p : productOnePictureWithDescriptionDto) {%>
						<tr>
							<td><input type="checkbox" name="boxes" onClick="showAction()" value="<%=p.getId() %>"></td>
							<td><%=p.getId() %></td>
							<td><%=p.getCreatedOn() %></td>
							<td><%=p.getProductName() %></td>
							<td><%=p.getSerialNumber() %></td>
							<td><%=p.getQuantity() %></td>
							<td><%=p.getProjectName() %></td>
							<td>
								<a class="picWrp">
									<img src="<%="data:image/png;base64,"+p.getPicture()%>"/>
									
								</a>
							</td>
							<td><%=p.getStatus() %></td>
							<td><a href="/products/view?id=<%=p.getId() %>">view</a></td>
						</tr>
					<%} %>
				</table>
				<div id="actionTab" style="display:none">
					<h4 style="display:inline-block">Action</h4>
					<select name="action" id="action">
						<option value="Change to New">Change status to New</option>
						<option value="Change to In Progress">Change status to In Progress</option>
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