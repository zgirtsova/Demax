<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/fragments/head.html"%>
<body>
	<div class="container-fluid">
	    <main class="mt-3">
	        <h1 class="text-center font-weight-bold">Login</h1>
	        <hr class="hr-2">
	        <form class="w-40 mx-auto" action="/login" onsubmit="return validateForm()" method="POST">
	            <div class="form-group">
	                <label for="email">Email</label>
	                <input type="text" name="email" class="form-control" id="email" required/>
	            </div>
	            <div class="form-group">
	                <label for="password">Password</label>
	                <input type="password" name="password" class="form-control" id="password" required/>
	            </div>
	            <a href="/reset-password">Forgot Password?</a>
	            <hr class="hr-2">
	            <div class="form-check d-flex justify-content-center">
	                <div class="checkbox-holder">
	                    <input class="form-check-input" type="checkbox" id="rememberMe" name="rememberMe">
	                    <label class="form-check-label" for="rememberMe">
	                        Remember Me
	                    </label>
	                </div>
	            </div>
	            <hr class="hr-2">
	            <div class="button-holder d-flex justify-content-around">
	                <button type="submit" class="btn btn-secondary">Login</button>
	            </div>
	        </form>
	        <hr class="hr-2w-25">
	    </main>
	</div>
</body>
</html>