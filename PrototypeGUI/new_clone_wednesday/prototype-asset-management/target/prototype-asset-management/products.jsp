<%@page import="java.util.List" %>
<%@page import="dtos.products.ProductWithDescriptionDTO" %>
<%@ page import="dtos.projects.ProjectNameDTO" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN""http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Prototype</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

	<meta name="company" content="www.demax.bg" />
	<meta name="title" content="Prototype" />
	<meta name="description" content="Prototype" />
	<meta name="copyright" content="www.prototype.com" />
	<meta name="robots" content="index,follow" />
	<meta name="robots" content="all" />
	<meta name="googlebot" content="INDEX,FOLLOW" />
	<meta name="revisit-after" content="1 days" />
	<meta http-equiv="imagetoolbar" content="no" />
	<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1" />
	<meta name="format-detection" content="telephone=no" />

	<link rel="shortcut icon" href="./assets/icon/favicon.ico" type="image/x-icon" />
	<link rel="icon" href="./assets/icon/favicon.ico" type="image/x-icon" />

	<link href="./assets/css/style.css" rel="stylesheet" type="text/css" title="default" />
</head>
<body>
<!-- --------------------------------------------------------- -->
<div class="wrapper">
	<%--  header  --%>
	<jsp:include page="./header.jsp" />
	<%-- /header  --%>
	
	<div class="contentWrapper">
		<div class="content">
			<div class="breadcrumbs">
				<a>Prototype</a>
				<i></i>
				<a>Products</a>
			</div>
			<div class="heading">
				<div class="pageTtl">
					<i class="products"></i>
					<p>Products(<%= request.getAttribute("filteredProductsCount") %>)</p>
				</div>
				<div class="action">
					<a class="blueBtn" href="new-product">Add product</a>
					<div class="dropdown">
						<form action="products" method="post" name="results-per-page">
							<select name="results">
								<option value="5">Results per page:5</option>
								<option value="10">Results per page:10</option>
								<option value="20">Results per page:20</option>
							</select>
							<input type="hidden" value="<%= request.getParameter("page") %>" name="page">
							<input type="hidden" value="<%= request.getParameter("results") %>" name="results-hidden"
								   id="results-hidden">
							<button type="submit">Submit</button>
						</form>
					</div>
				</div>
			</div>
			<div class="tableWrp">
				<div class="filters">
					<%
						String url = "products?page=" +
								request.getParameter("page") +
								"&results=" +
								request.getParameter("results");
					%>
					<form name="filters" method="POST" action="<%= url + "&" %>">
						<p>Filters</p>
						<div class="dropdown date">
							<input
									type="date"
									name="date-from"
									value="<%= request.getParameter("date-from") %>"
									style="width: 100%;height: 100%;"
							/>
						</div>
						<div class="dropdown date">
							<input
									type="date"
									name="date-to"
									value="<%= request.getParameter("date-to") %>"
									style="width: 100%;height: 100%;"
							/>
						</div>
						<div class="dropdown status">
							<input type="hidden" id="status-value" value="<%= request.getParameter("status") %>">
							<select name="status" title="statuses">
								<option value="0">Status: All</option>
								<option value="1">New</option>
								<option value="2">In Progress</option>
								<option value="3">Finished</option>
							</select>
						</div>
						<div class="dropdown project">
							<input type="hidden" id="project-value" value="<%= request.getParameter("project") %>">
							<% List<ProjectNameDTO> projectsNames = (List<ProjectNameDTO>) request.getAttribute("projectsNames"); %>
							<select name="project-id" title="projects">
								<option value="0">Project: All</option>
								<% for(int i = 0; i < projectsNames.size(); i++) { %>
									<option value='<%= projectsNames.get(i).getId() %>'><%=  projectsNames.get(i).getProjectName() %></option>
								<% } %>
							</select>
						</div>
							<a class="blueDarkBtn" type="button">
								<button type="submit" name="apply-filters-button">
									Apply
								</button>
							</a>
						<div class="right">
							<a class="grayBtn" id="clear-filters"><span>Clear filters</span><i></i></a>
							<div class="pagination">
								<% int filteredProductsCount = (int) request.getAttribute("filteredProductsCount"); %>
								<% int resultPerPage = Integer.parseInt(request.getParameter("results")); %>
								<% int pages =  filteredProductsCount % resultPerPage == 0 ? filteredProductsCount / resultPerPage : filteredProductsCount / resultPerPage + 1; %>
								<% int currentPage = Integer.parseInt(request.getParameter("page")); %>
								<a class="arrowLeft disabled"><i></i></a>
								<form method="post">
									<div class="center">
										<% if (currentPage != 1 && currentPage != pages) { %>
										<a><%= currentPage - 1 %></a>
										<a><%= currentPage %></a>
										<a><%= currentPage + 1 %></a>
										<% } else if (currentPage == 1) { %>
										<a><%= currentPage %></a>
										<% if (currentPage+1 <= pages) { %>
										<a><%= currentPage + 1 %></a>
										<% } %>
										<% if (currentPage+2 <= pages) { %>
										<a><%= currentPage + 2 %></a>
										<% } %>
										<% } else { %>
										<% if (currentPage-2 >= 1) { %>
										<a><%= currentPage - 2 %></a>
										<% } %>
										<% if (currentPage-1 >= 1) { %>
										<a><%= currentPage - 1 %></a>
										<% } %>
										<a><%= currentPage %></a>
										<% } %>
									</div>
								</form>
								<a class="arrowRight disabled"><i></i></a>
							</div>
						</div>
					</form>
				</div>
				<div class="table">
					<div class="header">
						<div class="checkbox">
							<a class="checkbox" name="checkbox-main">
								<i></i>
							</a>
						</div>
						<span class="number">#</span>
						<a class="date"><i class="sort up"></i>Created</a>
						<span class="productName">Product name</span>
						<span class="sn">SN</span>
						<a class="quantity"><i class="sort up"></i>Qty</a>
						<span class="description">Description</span>
						<span class="project">Project</span>
						<span class="picture">Picture</span>
						<div class="status">Status</div>
						<div class="settings"><i></i></div>
					</div>
					<div class="content">
						<% List<ProductWithDescriptionDTO> products = (List<ProductWithDescriptionDTO>) request.getAttribute("products"); %>
						<% if(products != null) { %>
							<% for (int i = 0; i < products.size(); i++) { %>
							<% ProductWithDescriptionDTO product = products.get(i); %>
							<div class="row">
								<div class="checkbox">
									<a class="checkbox" name="checkbox-project" id="<%= product.getId() %>">
										<i></i>
									</a>
								</div>
								<span class="number"><%= product.getId() %></span>
								<span class="date"><%= product.getDateCreated().toLocalDate() %></span>
								<span class="productName"><%= product.getProductName() %></span>
								<span class="sn"><%= product.getSerialNumber() %></span>
								<span class="quantity"><%= product.getQuantity() %></span>
								<span class="description"><%= product.getDescription() %></span>
								<span class="project"><%= product.getProjectName() %></span>
								<div class="picture">
									<a class="picWrp">
										<img src="assets/img/example/wooden-frame.png"/>
										<i>7</i>
										<var class="zoom"><span></span></var>
									</a>
								</div>
								<div class="status"><span class="orange"><%= product.getStatus() %></span></div>
								<div class="settings"><a class="grayBtn" href="/product/<%=product.getId()%>"><i></i></a></div>
							</div>
							<% } %>
						<% } else { %>
						<div class="row">
							<span class="productName"> No products were found. </span>
						</div>
						<% } %>
					</div>
				</div>
				<div class="bottomActions">
					<form name="actions" method="POST" action="<%= url %>">
						<p>Actions</p>
						<div class="dropdown">
							<select name="action-index" title="actions">
								<option value="0">Select</option>
								<option value="1">Change status to New</option>
								<option value="2">Change status to In progress</option>
								<option value="3">Change status to Finished</option>
								<option value="4">Delete</option>
							</select>
						</div>
						<a class="blueDarkBtn" id="apply-actions-button">Apply</a>
						<div id="products-boxes"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	window.onload = () => {
		attachEvents();
		setStatus();
		setResultsPerPage();

		function attachEvents() {
			//clearFilters
			let clearFiltersButton = document.getElementById('clear-filters');
			clearFiltersButton.addEventListener('click', clearFilters);

			//selectProject
			let projectsCheckboxes = document.querySelectorAll('a[name="checkbox-project"]');
			projectsCheckboxes.forEach(value => value.addEventListener('click', selectProject));

			//selectAllProjects
			let mainCheckbox = document.querySelector('a[name="checkbox-main"]');
			mainCheckbox.addEventListener('click', () => selectAllProjects(projectsCheckboxes, mainCheckbox));

			//applyActionOnSelectedProjects
			let applyActionsButton = document.getElementById('apply-actions-button');
			applyActionsButton.addEventListener('click', applyActionOnSelectedProjects);
		}

		function clearFilters() {

			let dateFrom = document.querySelector('input[name="date-from"]');
			let dateTo = document.querySelector('input[name="date-to"]');
			let status = document.querySelector('select[name="status"]');
			let filtersForm = document.querySelector('form[name="filters"]');

			dateFrom.value = "";
			dateTo.value = "";
			status.value = "0";

			filtersForm.submit();
		}

		function selectProject() {
			let classList = this.classList;

			if (classList.contains("active")) {
				this.classList.remove("active");
			} else {
				this.classList.add("active");
			}
		}

		function selectAllProjects(allProjects, self) {
			let classes = self.classList;

			if (classes.contains("active")) {
				classes.remove("active");

				allProjects.forEach(checkbox => {
					checkbox.classList.remove("active");
				});
			} else {
				classes.add("active");

				allProjects.forEach(checkbox => {
					checkbox.classList.add("active");
				});
			}
		}

		function applyActionOnSelectedProjects() {
			let selectedProjects = document.querySelectorAll('a.checkbox.active');
			let projectBoxesList = document.getElementById('products-boxes');

			let selectedProjectsIds =
					Array.from(selectedProjects)
							.map(x => x.getAttribute('id'));

			selectedProjectsIds.forEach(projectId => {
				let node = document.createElement("input");

				node.setAttribute('name', 'product-id');
				node.setAttribute('type', 'text');
				node.setAttribute('value', projectId);

				projectBoxesList.appendChild(node);
			});

			let actionsForm = document.querySelector('form[name="actions"]');
			actionsForm.submit();
		}

		function setStatus() {
			let statusValue = document.getElementById("status-value").value;
			let statuses = document.querySelector('select[name="status"]');

			if (statusValue !== 'null') {
				statuses.value = statusValue;
			}
		}

		function setResultsPerPage() {
			let resultsValue = document.getElementById("results-hidden").value;
			let results = document.querySelector('select[name="results"]');

			if (resultsValue !== 'null') {
				results.value = resultsValue;
			}
		}
	};
</script>
</body>
</html>