<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN""http://www.w3.org/TR/html4/strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Prototype</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

   <meta charset="UTF-8">
	<meta name="company" content="www.demax.bg" />
	<meta name="title" content="Prototype" />
	<meta name="description" content="Prototype" />
	<meta name="copyright" content="www.prototype.com" />
	<meta name="robots" conten  t="index,follow" />
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
				<a>Settings</a>
			</div>
			<div class="heading">
				<div class="pageTtl">
					<i class="profile"></i>
					<p>Profile</p>
				</div>
			</div>
			<div class="leftCont">
				<div class="profileInfo">
					<div class="cont">
						<p>Name</p>
						<p><%= request.getAttribute("name") %></p>
					</div>
					<div class="cont">
						<p>Role</p>
						<p><%= request.getAttribute("role") %></p>
					</div>
				</div>
			</div>
			<div class="rightCont">
				<div class="welcome">
					<i class="profilePic"></i>
					<p>Welcome Jake</p>
				</div>
				<div class="activity">
					<p class="ttl">Change password</p>
					<div class="history lock">
						<div class="formGroup changePassword">
							<div class="generalInfo">
								<div class="inputWrp">
									<div class="title">
										<span>Old password</span>
									</div>
									<input type="text">
									<span class="error">Incorrect value</span>
								</div>
								<div class="inputWrp">
									<div class="title">
										<span>New password</span>
									</div>
									<input type="text">
									<span class="error">Incorrect value</span>
								</div>
								<div class="inputWrp">
									<div class="title">
										<span>Repeat new password</span>
									</div>
									<input type="text">
									<span class="error">Incorrect value</span>
								</div>
								<div class="btnsWrp">
									<a class="grayDarkBtn">Cancel</a>
									<a class="greenBtn">Save</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>