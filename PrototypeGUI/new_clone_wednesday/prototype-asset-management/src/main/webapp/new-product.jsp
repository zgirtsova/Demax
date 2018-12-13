<%@page import="java.util.List" %>
<%@page import="dtos.projects.ProjectNameDTO" %>

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
				<a>Prototypes</a>
				<i></i>
				<a>Products</a>
				<i></i>
				<a>New product</a>
			</div>
			<form method="post" enctype='multipart/form-data'>
				<div class="heading">
					<div class="pageTtl">
						<i class="products"></i>
						<p>Product</p>
					</div>
					<div class="action">
						<a class="blueBtn"><button type="submit">Add & Save</button></a>
						<a class="grayDarkBtn m-r-5" href="products">Cancel & Close</a>
					</div>
				</div>
				<div class="formCont">
					<div class="headerInfo product">
						<i></i>
						<div class="text">
							<h3>New product</h3>
							<h4>Please type product details</h4>
						</div>
					</div>
					<div class="formGroup">
						<div class="generalInfo">
							<div class="inputWrp">
								<div class="title">
									<i class="product"></i><span>Product name</span>
								</div>
								<input type="text" name="product-name"/>
								<span class="error">Incorrect value</span>
							</div>
							<div class="inputWrp">
								<div class="title">
									<i class="serialNum"></i><span>Serial number</span>
								</div>
								<input type="text" name="serial-number"/>
								<span class="error">Incorrect value</span>
							</div>
							<div class="inputWrp">
								<div class="title">
									<i class="quantity"></i><span>Quantity</span>
								</div>
								<input type="text" name="quantity"/>
								<span class="error">Incorrect value</span>
							</div>
							<div class="inputWrp">
								<div class="title">
									<i class="project"></i><span>Project</span>
								</div>
								<div class="dropdown">
									<% List<ProjectNameDTO> projectsNames = (List<ProjectNameDTO>) request.getAttribute("projectsNames"); %>
									<select name="project-id">
										<option>Choose project</option>
										<% for(int i = 0; i < projectsNames.size(); i++) { %>
											<option value='<%= projectsNames.get(i).getId() %>'><%=  projectsNames.get(i).getProjectName() %></option>
										<% } %>
									</select>
								</div>
							</div>
						</div>
						<div class="addDescription">
							<div class="header">
								<i class="description"></i>
								<p>Description</p>
							</div>
							<textarea rows="8" cols="110" name="description">

							</textarea>
						</div>
						<div class="attachedProducts">
							<div class="header">
								<div class="picture">
									<i class="picture"></i>
									<p>Picture</p>
								</div>
								<a class="grayDarkBtn"><input class="grayDarkBtn" type="file" name="picture" accept="image/*">Upload</a>
							</div>
							<div class="list">
								<p>NO PICTURES</p>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>