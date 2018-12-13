<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/fragments/head.html"%>
<body>
	<div class="container-fluid">
	    <main class="mt-3">
	        <h1 class="text-center font-weight-bold">Reset Password</h1>
	        <hr class="hr-2">
	        <form class="w-40 mx-auto" action="/reset-password" onsubmit="return validateForm()" method="POST">
	            <div class="form-group">
	                <label for="email">Email</label>
	                <input type="text" name="email" class="form-control" id="email" required/>
	            </div>
	            <hr class="hr-2">
	            <div class="button-holder d-flex justify-content-around">
	                <button type="submit" class="btn btn-secondary">Send</button>
	            </div>
	        </form>
	        <hr class="hr-2w-25">
	    </main>
	</div>
</body>
</html>