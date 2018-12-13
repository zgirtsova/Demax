<%@page import="java.util.List" %>
<%@page import="java.time.format.DateTimeFormatter" %>
<%@page import="dtos.dashboards.DashboardDTO" %>
<%@page import="dtos.actions.ActionDTO" %>

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
	<div class="windowPopWrp" style="display:none;">
		<div class="windowPop">
			<div class="windowPopHeader">
				<i></i>
				<div class="title">
					<p>Delete image/Log out</p>
					<p>Any kind of information</p>
				</div>
			</div>
			<div class="windowPopContents">
				<div class="centered">
					<h4>Are you sure?</h4>
					<span>Description of action</span>
				</div>
			</div>
			<div class="windowPopButtons">
				<a class="grayDarkBtn">Cancel</a>
				<a class="greenBtn">Confirm</a>
			</div>
		</div>
	</div>
	
	<%--  header  --%>
	<jsp:include page="./header.jsp" />
	<%-- /header  --%>
	
	<% DashboardDTO dashboardDTO = (DashboardDTO) request.getAttribute("dashboardDTO"); %>
	<div class="contentWrapper">
		<div class="content">
			<div class="breadcrumbs">
				<a>General</a>
			</div>
			<div class="heading">
				<div class="pageTtl">
					<i class="dashboard"></i>
					<p>Dashboard</p>
				</div>
			</div>
			<div class="leftCont">
				<div class="statistics">
					<a class="viewAll" href="projects?page=1&results=5">
						<span><%= dashboardDTO.getNewProjectsCount() %></span>
						<span>New projects</span>
						<i class="projects"></i>
						<span>view all</span>
					</a>
					<div class="finishedProjects"><span><%= dashboardDTO.getFinishedProjectsCount() %></span> finished projects</div>
					<a class="greenBtn" href="new-project">Add project</a>
				</div>
				<div class="statistics">
					<a class="viewAll" href="products?page=1&results=5">
						<span><%= dashboardDTO.getProductsInProgressCount() %></span>
						<span>Products in progress</span>
						<i class="products"></i>
						<span>view all</span>
					</a>
					<div class="finishedProjects"><span><%= dashboardDTO.getFinishedProductsCount() %></span> finished projects</div>
					<a class="blueBtn" href="new-product">Add product</a>
				</div>
			</div>
			<div class="rightCont">
				<div class="welcome">
					<i class="profilePic"></i>
					<p>Welcome <span><%= request.getSession().getAttribute("userNames")%></span></p>
				</div>
				<div class="activity">
					<p class="ttl">Activity</p>
					<div class="lastActions">Last <span>15</span> actions:</div>
					<div class="history">

						<% List<ActionDTO> lastActions = dashboardDTO.getLastFifteenActions(); %>
						<% for(int i = 0; i < lastActions.size(); i++) { %>
							<% 
								ActionDTO action = lastActions.get(i); 
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
								String[] dateTime = action.getOccuredOn().format(formatter).split(" ");
							%>
							<div class="row">
								<p class="date"><%= dateTime[0] %></p>
								<p class="hour"><%= dateTime[1] + "'" %></p>
								<div class="action">
									<i class="user"></i>
									<p><span><%= request.getSession().getAttribute("userNames")%></span> <%= action.getAction() %></p>
								</div>
							</div>
						<% } %>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>