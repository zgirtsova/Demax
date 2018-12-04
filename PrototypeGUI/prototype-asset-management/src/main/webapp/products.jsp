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
					<p>Products(65)</p>
				</div>
				<div class="action">
					<a class="blueBtn" href="new-product">Add product</a>
					<div class="dropdown">
						<select>
							<option>Results per page:5</option>
							<option>Results per page:10</option>
						</select>
					</div>
				</div>
			</div>
			<div class="tableWrp">
				<div class="filters">
					<p>Filters</p>
					<div class="dropdown date">
						<select>
							<option>Date from</option>
						</select>
					</div>
					<div class="dropdown date">
						<select>
							<option>Date to</option>
						</select>
					</div>
					<div class="dropdown status">
						<select>
							<option>Status:All</option>
						</select>
					</div>
					<div class="dropdown status">
						<select>
							<option>Project:All</option>
						</select>
					</div>
					<a class="blueDarkBtn">Apply</a>
					<div class="right">
						<a class="grayBtn"><span>Clear filters</span><i></i></a>
						<div class="pagination">
							<a class="arrowLeft"><i></i></a>
							<div class="center">
								<a class="active">1</a>
								<a>2</a>
								<a>3</a>
							</div>
							<a class="arrowRight disabled"><i></i></a>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="header">
						<div class="checkbox"><a class="checkbox active"><i></i></a></div>
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
						<div class="row">
							<div class="checkbox"><a class="checkbox active"><i></i></a></div>
							<span class="number">4</span>
							<span class="date">19.12.2018</span>
							<span class="productName">Wooden frame</span>
							<span class="sn">001W</span>
							<span class="quantity">2</span>
							<span class="description">
							Lorem ipsum dolor sit amet, consectetur adipiscing elitt, sed do eiusmod tempor incididunt ut labore et
							</span>
							<span class="project">Offset printing table</span>
							<div class="picture">
								<a class="picWrp">
									<img src="assets/img/example/wooden-frame.png"/>
									<i>7</i>
									<var class="zoom"><span></span></var>
								</a>
							</div>
							<div class="status"><span class="orange">in progress</span></div>
							<div class="settings"><a class="grayBtn" href="edit-product"><i></i></a></div>
						</div>
						<div class="row">
							<div class="checkbox"><a class="checkbox active"><i></i></a></div>
							<span class="number">3</span>
							<span class="date">19.12.2018</span>
							<span class="productName">Aluminium arm</span>
							<span class="sn">A002</span>
							<span class="quantity">6</span>
							<span class="description">
							Lorem ipsum dolor sit amet, consectetur adipiscing elitt, sed do eiusmod tempor incididunt ut labore et
							</span>
							<span class="project">Weather baloon drone</span>
							<div class="picture">
								<a class="picWrp">
									<img src="assets/img/example/aluminium-arm.png"/>
									<i>3</i>
									<var class="zoom"><span></span></var>
								</a>
							</div>
							<div class="status"><span class="orange">in progress</span></div>
							<div class="settings"><a class="grayBtn" href="edit-product"><i></i></a></div>
						</div>
						<div class="row">
							<div class="checkbox"><a class="checkbox active"><i></i></a></div>
							<span class="number">2</span>
							<span class="date">17.12.2018</span>
							<span class="productName">Brushless motor</span>
							<span class="sn">5324</span>
							<span class="quantity">5</span>
							<span class="description">
							Lorem ipsum dolor sit amet, consectetur adipiscing elitt, sed do eiusmod tempor incididunt ut labore et
							</span>
							<span class="project">Coffee cup maker</span>
							<div class="picture">
								<a class="picWrp">
									<img src="assets/img/example/brushless-motor.png"/>
									<i>2</i>
									<var class="zoom"><span></span></var>
								</a>
							</div>
							<div class="status"><span class="gray">New</span></div>
							<div class="settings"><a class="grayBtn" href="edit-product"><i></i></a></div>
						</div>
						<div class="row">
							<div class="checkbox"><a class="checkbox active"><i></i></a></div>
							<span class="number">1</span>
							<span class="date">16.12.2018</span>
							<span class="productName">3D Printer</span>
							<span class="sn">3453</span>
							<span class="quantity">1</span>
							<span class="description">
							Lorem ipsum dolor sit amet, consectetur adipiscing elitt, sed do eiusmod tempor incididunt ut labore et
							</span>
							<span class="project">iPhone case printer</span>
							<div class="picture">
								<a class="picWrp">
									<img src="assets/img/example/brushless-motor.png"/>
									<i>12</i>
									<var class="zoom"><span></span></var>
								</a>
							</div>
							<div class="status"><span class="green">Finished</span></div>
							<div class="settings"><a class="grayBtn" href="edit-product"><i></i></a></div>
						</div>
					</div>
				</div>
				<div class="actionTtl">
					<i></i>
					<p>With 4 project selected:</p>
				</div>
				<div class="bottomActions">
					<p>Actions</p>
					<div class="dropdown">
						<select>
							<option>Select</option>
							<option>Change status to New</option>
							<option>Change status to In progress</option>
							<option>Change status to Finished</option>
							<option>Delete</option>
						</select>
					</div>
					<a class="blueDarkBtn">Apply</a>
				</div>
			</div>
			
		</div>
	</div>
</div>
</body>
</html>