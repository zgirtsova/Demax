<%@page import="orm.dto.ViewProductDto"%>
<%@page import="orm.dto.PictureDetailedDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/fragments/head.html"%>
<body>
	<%@ include file="/fragments/navbar.html" %>
	<%ViewProductDto productDto = (ViewProductDto) request.getAttribute("productDto"); %>
	<div>
		<h4>Prototype>products>View product</h4>
		<hr>
		<div style="display:block;width:100%;">
			<form action="/products/view?id=<%=productDto.getId()%>" method="POST" enctype="multipart/form-data" onsubmit="return validateForm()">
				<div style="display:inline-block;width:50%"> 
					<h3>product</h3>
				</div>
				<div style="display:inline-block">
					<button type="reset">CANCEL</button>
					<button type="submit">EDIT & SAVE</button>
				</div>
					<h3><%=productDto.getProductName() %></h3>
					
					<br>
					<p>Please type in the product details.</p>
					<hr>
					<div style="display:inline-block;width:50%">
						<label for="productName">Product name</label>
						<br>
						<input type="text" name="productName" id="ProductName" value="<%=productDto.getProductName() 	%>" maxlength="50"  required>
						<br>
						<label for="serialNumber">Serial Number</label>
						<br>
						<input type="text" name="serialNumber" id="serialNumber" value="<%=productDto.getSerialNumber()%>" maxlength="4"  required>
						<br>
						<label for="quantity">Quantity</label>
						<br>
						<input type="number" name="quantity" id="quantity" value="<%=productDto.getQuantity()%>" min="0" required>
						<br>
						<label for="status">Status</label>
						<br>
						<select name="status" id="status">
							<%if(productDto.getStatus()=="New"){%>
								<option value="New" selected>New</option>
							<%}else{%>
								<option value="New">New</option>
							<%}
							if(productDto.getStatus()=="In Progress"){%>
								<option value="In Progress" selected>In Progress</option>
							<%}else{%>
								<option value="In Progress">In Progress</option>
							<%}
							if(productDto.getStatus()=="Finished"){%>
								<option value="Finished" selected>Finished</option>
							<%}else{%>
								<option value="Finished">Finished</option>
								<%}%>
						</select>
					</div>
			</form>
		</div>
		<div style="display:inline-block;background-color:#A8A8A8;">
			<form action="/products/view?id=<%=productDto.getId()%>" method="POST" enctype="multipart/form-data">
				<label for="image">Image</label><br/>
				<input type="file" name="image" id="image" accept="image/*" required>
				<button type="submit">Upload</button>
			</form>
			
			<table border-width="0px">

				
				<%
					List<PictureDetailedDto> pics = productDto.getPictures();
					for(PictureDetailedDto p : pics){
						%>
							<tr>

								<td>
									<div width="50%" style="display:inline-block;">
										<img width="100px" height="auto" src="data:image/*;base64,<%=p.getPicture()%>"/>
									</div>
								</td>
								<td>
									<div width = "10%">
										<form action="/products/view?id=<%=productDto.getId()%>" method="POST" enctype="multipart/form-data">
											<input type="text" name="deletePicture" value="<%=p.getId()%>" hidden>
											<button name="deletePicture" value="<%=p.getId()%>" type="submit">Delete</button>
										</form>
									</div>
								</td>
							</tr>
						<%
					}
				%>
				
			</table>
		</div>
		</div>
</body>
</html>