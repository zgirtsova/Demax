<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/fragments/head.html"%>
<body>
	<%@ include file="/fragments/navbar.html" %>
	<div>
		<h4>Prototype>Projects>New project</h4>
		<hr>
		<div style="display:block;width:100%;">
			<form action="/projects/new" method="POST">
				<div style="display:inline-block;width:50%"> 
					<h3>Project</h3>
				</div>
				<div style="display:inline-block">
					<button type="reset">CANCEL</button>
					<button type="submit">ADD &amp; SAVE</button>
				</div>
				<div style="display:block">
					<h3>New Project</h3>
					<br>
					<p>Please type in the project details.</p>
					<hr>
					<div style="display:inline-block;width:50%">
						<label for="projectName">Project name</label>
						<br>
						<input type="text" name="projectName" id="projectName" required maxlength="50">
						<br>
						<label for="companyName">Company name</label>
						<br>
						<input type="text" name="companyName" id="companyName" required maxlength="50">
						<br>
						<label for="projectManager">Project manager</label>
						<br>
						<input type="text" name="projectManager" id="projectManager" required maxlength="50">
						<br>
						<label for="status">Status</label>
						<br>
					    <select name="status" id="status">
					    	<option value="New">New</option>
						    <option value="In progress">In progress</option>
						    <option value="Finished">Finished</option>
					    </select>
					</div>
					<div style="display:inline-block">
						<p>Attached products (0)</p>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>