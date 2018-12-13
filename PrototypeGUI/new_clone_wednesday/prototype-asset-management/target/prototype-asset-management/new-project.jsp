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
				<a>Projects</a>
				<i></i>
				<a>New project</a>
			</div>
			<form method="post">
				<div class="heading">
					<div class="pageTtl">
						<i class="projects"></i>
						<p>Project</p>
					</div>
					<div class="action">
						<a class="greenBtn"><button type="submit">Add & Save</button></a>
						<a class="grayDarkBtn m-r-5" href="projects?page=1&results=5">Cancel & Close</a>
					</div>
				</div>
				<div class="formCont">
					<div class="headerInfo project">
						<i></i>
						<div class="text">
							<h3>New project</h3>
							<h4>Please type project details</h4>
						</div>
					</div>
					<div class="formGroup">
						<div class="generalInfo">
							<div class="inputWrp">
								<div class="title">
									<i class="project"></i><span>Project name</span>
								</div>
								<input type="text" name="project-name"/>
								<span class="error">Incorrect value</span>
							</div>
							<div class="inputWrp">
								<div class="title">
									<i class="company"></i><span>Company name</span>
								</div>
								<input type="text" name="company-name">
								<span class="error">Incorrect value</span>
							</div>
							<div class="inputWrp">
								<div class="title">
									<i class="manager"></i><span>Project manager</span>
								</div>
								<input type="text" name="project-manager"/>
								<span class="error">Incorrect value</span>
							</div>
						</div>
						<div class="attachedProducts">
							<div class="header">
								<div class="count">
									<i class="product"></i>
									<p>Attached products <span>(0)</span></p>
								</div>
								<a class="grayDarkBtn disabled">View all</a>
							</div>
							<div class="list">
								<p>NO PRODUCTS</p>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>