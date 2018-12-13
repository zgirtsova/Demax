<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<body>
	<h2>Login</h2>
	<form name="login" method="post" action="/login">
		<div>
			<label for="email">Email</label> <input type="text" name="email" value="" />
		</div>
		<div>
			<label for="password">Password</label> <input type="password" name="password" value="" />
		</div>
		<div>
			<button type="submit">Sign in</button>
		</div>
	</form>
</body>
</html>