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
			<div class="heading">
				<div class="pageTtl">
					<i class="products"></i>
					<p>Product</p>
				</div>
				<div class="action">
					<a class="blueBtn">Edit & Save</a>
					<a class="grayDarkBtn m-r-5" href="products">Cancel & Close</a>
				</div>
			</div>
			<div class="formCont">
				<div class="headerInfo product">
					<i></i>
					<div class="text">
						<h3>Wooden frame</h3>
						<h4>Please review or edit the product details</h4>
					</div>
				</div>
				<div class="formGroup">
					<div class="generalInfo">
						<div class="inputWrp">
							<div class="title">
								<i class="product"></i><span>Product name</span>
							</div>
							<input type="text" value="Wooden frame"/>
							<span class="error">Incorrect value</span>
						</div>
						<div class="inputWrp">
							<div class="title">
								<i class="serialNum"></i><span>Serial number</span>
							</div>
							<input type="text" value="001W"/>
							<span class="error">Incorrect value</span>
						</div>
						<div class="inputWrp error">
							<div class="title">
								<i class="quantity"></i><span>Quantity</span>
							</div>
							<input type="text" value="0"/>
							<span class="error">Incorrect value</span>
						</div>
						<div class="inputWrp">
							<div class="title">
								<i class="project"></i><span>Project</span>
							</div>
							<div class="dropdown">
								<select>
									<option>Offset printing table</option>
									<option>Weather baloon drone</option>
									<option>Coffee cup maker</option>
								</select>
							</div>
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
					<div class="addDescription">
						<div class="header">
							<i class="description"></i>
							<p>Description</p>
						</div>
						<textarea rows="8" cols="110">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed ado eiusmod tempor incididunt ut labore etpiscing elit, sed ado eiusmod temp.
						</textarea>
					</div>
					<div class="attachedProducts">
						<div class="header">
							<div class="picture">
								<i class="picture"></i>
								<p>Picture</p>
							</div>
							<a class="grayDarkBtn">Upload</a>
						</div>
						<div class="list">
							<div class="tableWrp">
								<div class="table inner">
									<div class="header">
										<span class="thumb">Thumb</span>
										<span class="picture">Picture</span>
										<span class="fileName">File name</span>
										<span class="fileSize">File size</span>
										<span class="download">Download</span>
										<div class="delete">Delete</div>
									</div>
									<div class="content">
										<div class="row">
											<span class="thumb">
												<input type="radio" value="1"/>
											</span>
											<div class="picture">
												<a class="picWrp">
													<img src="assets/img/example/aluminium-arm2.png"/>
													<var class="zoom"><span></span></var>
												</a>
											</div>
											<span class="fileName">Aluminium-arm-1.png</span>
											<span class="fileSize">2.7MB</span>
											<span class="download">
												<a class="greenBtn"><i></i></a>
											</span>
											<div class="delete">
												<a class="grayDarkBtn"><i></i></a>
											</div>
										</div>
										<div class="row">
											<span class="thumb">
												<input type="radio" value="2"/>
											</span>
											<div class="picture">
												<a class="picWrp">
													<img src="assets/img/example/aluminium-arm.png"/>
													<var class="zoom"><span></span></var>
												</a>
											</div>
											<span class="fileName">Aluminium-arm-final2.png</span>
											<span class="fileSize">12MB</span>
											<span class="download">
												<a class="greenBtn"><i></i></a>
											</span>
											<div class="delete">
												<a class="grayDarkBtn"><i></i></a>
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
</div>
</body>
</html>