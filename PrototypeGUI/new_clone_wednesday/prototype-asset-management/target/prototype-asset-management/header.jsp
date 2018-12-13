<div class="headerWrapper">
	<div class="header">
		<a class="logo" href="index"></a>
		<div class="mainMenu">
			<div class="group">
				<p>General</p>
				<a class="dashboard"  href="index">
					<i></i>
					<span>Dashboard</span>
				</a>
			</div>
			<div class="group">
				<p>Prototype</p>
				<a class="projects active" href="projects?page=1&results=5">
					<i></i>
					<span>Projects</span>
					<var>2</var>
				</a>
				<a class="products" href="products?page=1&results=5">
					<i></i>
					<span>Products</span>
					<var>3</var>
				</a>
			</div>
			<div class="group">
				<p>Settings</p>
				<a class="profile"  href="profile">
					<i></i>
					<span>Profile</span>
				</a>
			</div>
			<div class="logoutGroup">
				<div class="user">
					<p><%=request.getSession().getAttribute("userNames")%></p>
					<a class="logout" href="login">
						<i></i>
						<p>logout</p>
					</a>
				</div>
				<i class="profilePic"></i>
			</div>
		</div>
		<div class="searchWrp">
			<div class="search">
				<i class="searchIcon"></i>
				<input value="Search"/>
				<a class="clear"></a>
			</div>
			<div class="searchDropdown" style="display:none;">
				<div class="items">
					<a class="result">
						<i class="project"></i>
						<span class="type">Project name</span>
						<span class="item">Offset printing DE122</span>
					</a>
					<a class="result">
						<i class="company"></i>
						<span class="type">Company</span>
						<span class="item">Company SE123</span>
					</a>
					<a class="result">
						<i class="manager"></i>
						<span class="type">Project manager</span>
						<span class="item">John  Doe12</span>
					</a>
					<a class="result">
						<i class="product"></i>
						<span class="type">Product name</span>
						<span class="item">Wooden arm ze123</span>
					</a>
					<a class="result">
						<i class="serialNum"></i>
						<span class="type">Serial number</span>
						<span class="item">e123456</span>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>