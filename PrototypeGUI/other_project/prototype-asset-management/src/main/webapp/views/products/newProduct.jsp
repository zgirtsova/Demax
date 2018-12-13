<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/fragments/head.html"%>
	<body>
		<%@ include file="/fragments/navbar.html" %>
		<%List<String> names = (List<String>)request.getAttribute("names"); %>
		<div>
			<h4>Prototype>Products>New product</h4>
			<hr>
			<div style="display:block;width:100%;">
				<form name="productData" action="/products/new" method="POST" onsubmit="return validateForm()" enctype="multipart/form-data">
					<div style="display:inline-block;width:50%"> 
						<h3>Products</h3>
					</div>
					<div style="display:inline-block">
						<button type="reset">CANCEL</button>
						<button type="submit">ADD &amp; SAVE</button>
					</div>
					<div style="display:block">
						<h3>New Product</h3>
						<br>
						<p>Please type in the product details.</p>
						<hr>
						<div style="display:inline-block;width:50%">
							<label for="productName">Product name</label>
							<br>
							<input type="text" name="productName" id="productName" required maxlength="50">
							<br>
							<label for="serialNumber">Serial number</label>
							<br>
							<input type="text" name="serialNumber" id="serialNumber" required maxlength="4"> 
							<br>
							<label for="quantity">Quantity</label>
							<br>
							<input type="number" name="quantity" id="quantity" required min="0">
							<br>
							<label for="projects">Project</label>
							<br>						
							<select id="projects" name="projects">
								<%for(int i = 0; i < names.size(); i++) {%>
									<option value="<%=names.get(i) %>"><%=names.get(i) %></option>
								<%} %>
							</select>
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
							<label for="description">Description</label>
							<br>
							<textarea name="description" id="description" cols="80" rows="14"></textarea>
							<br>
							<label for="image">Image</label><br/>
							<input type="file" name="image" id="image" accept="image/*" required>
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>