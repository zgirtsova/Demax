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
			<div class="heading">
				<div class="pageTtl">
					<i class="projects"></i>
					<p>Project</p>
				</div>
				<div class="action">
					<a class="greenBtn">Edit & Save</a>
					<a class="grayDarkBtn m-r-5" href="projects">Cancel & Close</a>
				</div>
			</div>
			<div class="formCont">
				<div class="headerInfo project">
					<i></i>
					<div class="text">
						<h3>Offset printing table</h3>
						<h4>Please review or edit the project details</h4>
					</div>
				</div>
				<div class="formGroup">
					<div class="generalInfo">
						<div class="inputWrp">
							<div class="title">
								<i class="project"></i><span>Project name</span>
							</div>
							<input type="text" value="Offset printing table"/>
							<span class="error">Incorrect value</span>
						</div>
						<div class="inputWrp">
							<div class="title">
								<i class="company"></i><span>Company name</span>
							</div>
							<input type="text" value="Demax DPI"/>
							<span class="error">Incorrect value</span>
						</div>
						<div class="inputWrp">
							<div class="title">
								<i class="manager"></i><span>Project manager</span>
							</div>
							<input type="text" value="Ivan Todorov"/>
							<span class="error">Incorrect value</span>
						</div>
						<div class="inputWrp">
							<div class="title">
								<i class="status"></i><span>Status</span>
							</div>
							<div class="dropdown">
								<select>
									<option>New</option>
									<option>In progress</option>
									<option>Finished</option>
								</select>
							</div>
						</div>
					</div>
					<div class="attachedProducts">
						<div class="header">
							<div class="count">
								<i class="product"></i>
								<p>Attached products <span>(17)</span></p>
							</div>
							<a class="grayDarkBtn">View all</a>
						</div>
						<div class="list">
							<div class="tableWrp">
								<div class="table inner">
									<div class="header">
										<span class="number">#</span>
										<span class="productName">Product name</span>
										<span class="sn">SN</span>
										<span class="quantity">Qty</span>
										<span class="picture">Picture</span>
										<div class="status">Status</div>
										<div class="settings"><i></i></div>
									</div>
									<div class="content">
										<div class="row">
											<span class="number">17</span>
											<span class="productName">Wooden frame</span>
											<span class="sn">001W</span>
											<span class="quantity">2</span>
											<div class="picture">
												<a class="picWrp">
													<img src="assets/img/example/wooden-frame.png"/>
													<i>7</i>
													<var class="zoom"><span></span></var>
												</a>
											</div>
											<div class="status"><span class="orange">in progress</span></div>
											<div class="settings"><a class="grayBtn"><i></i></a></div>
										</div>
										<div class="row">
											<span class="number">16</span>
											<span class="productName">Aluminium arm</span>
											<span class="sn">A002</span>
											<span class="quantity">6</span>
											<div class="picture">
												<a class="picWrp">
													<img src="assets/img/example/aluminium-arm.png"/>
													<i>3</i>
													<var class="zoom"><span></span></var>
												</a>
											</div>
											<div class="status"><span class="orange">in progress</span></div>
											<div class="settings"><a class="grayBtn"><i></i></a></div>
										</div>
										<div class="row">
											<span class="number">15</span>
											<span class="productName">Brushless motor</span>
											<span class="sn">5345</span>
											<span class="quantity">4</span>
											<div class="picture">
												<a class="picWrp">
													<img src="assets/img/example/brushless-motor.png"/>
													<i>2</i>
													<var class="zoom"><span></span></var>
												</a>
											</div>
											<div class="status"><span class="orange">in progress</span></div>
											<div class="settings"><a class="grayBtn"><i></i></a></div>
										</div>
										<div class="row">
											<span class="number">14</span>
											<span class="productName">Brushless motor</span>
											<span class="sn">5677</span>
											<span class="quantity">4</span>
											<div class="picture">
												<a class="picWrp">
													<img src="assets/img/example/brushless-motor.png"/>
													<i>12</i>
													<var class="zoom"><span></span></var>
												</a>
											</div>
											<div class="status"><span class="orange">in progress</span></div>
											<div class="settings"><a class="grayBtn"><i></i></a></div>
										</div>
									</div>
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