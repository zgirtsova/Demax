<%@page import="orm.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/fragments/head.html"%>
<body>
	<%@ include file="/fragments/navbar.html" %>
	<% UserDto userDto = (UserDto) request.getAttribute("userDto"); %>
	<div>
		<h4>Settings</h4>
		<hr>
		<h3>Profile</h3>
		<hr>
		<div style="display:inline-block;width:50%;">
			<h4>Name</h4>
			<br>
			<h4>
				<%=userDto.getFirstName() %>
				<%=userDto.getLastName() %>
			</h4>
			<br>
			<h4>Role</h4>
			<br>
			<h4><%=userDto.getRole() %></h4>
		</div>
		<div style="display:inline-block;">
			<h2>Welcome <%=userDto.getFirstName() %></h2>
			<br>
			<h3>Change password</h3>
			<form class="w-40 mx-auto" action="/profile" method="POST" onsubmit="return validateForm()">
	            <div class="form-group">
	                <label for="oldPassword">Old password</label>
	                <input type="password" name="oldPassword" class="form-control" id="oldPassword" required maxlength="30"/>
	            </div>	            
				<div id="message" style="display:none;">
					 <p id="length" style="color:red">Minimum <b>8 characters</b></p>
					 <p id="match" style="color:red">Passwords <strong>must </strong>match</p>
				</div>
	            <div class="form-group">
	                <label for="newPassword">New password</label>
	                <input type="password" name="newPassword" class="form-control" id="newPassword" required maxlength="30"/>
	            </div>
	            <div class="form-group">
	                <label for="newPassword2">Repeat new password</label>
	                <input type="password" name="newPassword2" class="form-control" id="newPassword2" required maxlength="30"/>
	            </div>
	            <hr class="hr-2">
	            <div style="display:inline-block">
	                <button type="reset" class="btn btn-secondary">Cancel</button>
	            </div>
	            <div style="display:inline-block">
	                <button type="submit" class="btn btn-secondary">Save</button>
	            </div>
	        </form>
		</div>
	</div>
<script>
	var password = document.getElementById("newPassword");
	var password2 = document.getElementById("newPassword2");
	var length = document.getElementById("length");
	var match = document.getElementById("match");
	password.onfocus = function() {
		  document.getElementById("message").style.display = "block";
	}
	password.onblur = function() {
		  document.getElementById("message").style.display = "none";
	}
	password2.onfocus = function() {
		  document.getElementById("message").style.display = "block";
	}
	password2.onblur = function() {
		  document.getElementById("message").style.display = "none";
	}
	password.onkeyup = function() {
		  if(password.value.length >= 8) {
		    length.style.color = "green";
		  } else {
		    length.style.color = "red";
		  }
		  if(password.value === password2.value) {
			    match.style.color = "green";
		  } else {
			    match.style.color = "red";
		  }
	}
	password2.onkeyup = function() {
		  if(password.value === password2.value) {
		    match.style.color = "green";
		  } else {
		    match.style.color = "red";
		  }
	}
</script>
</body>
</html>