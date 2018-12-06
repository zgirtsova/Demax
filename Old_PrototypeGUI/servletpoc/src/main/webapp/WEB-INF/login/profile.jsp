<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<body>
	<center>
		<h1>User Details</h1>
	</center>
	<table border="1" cellspacing=10 cellpadding=5>
		<tr>
			<th>Email</th>
			<th>Password</th>
		<tr>
			<td>${user.getEmail()}</td>
			<td>${user.getPassword()}</td>
		</tr>
	</table>
</body>
</html>