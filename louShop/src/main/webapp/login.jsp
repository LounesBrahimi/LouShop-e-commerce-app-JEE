<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.User"%>
<% User auth = (User) request.getSession().getAttribute("auth"); 
   if (auth != null){
	   response.sendRedirect("index.jsp");
   }
%>
<!DOCTYPE html>
	<html>
		<head>
			<title>Login LouShop</title>
			<%@include file="includes/head.jsp" %>
		</head>
		<body>
			<%@include file="includes/navbar.jsp" %>
			<div class="container">
				<div class="card w-50 mx-auto my-5">
					<div class="card-header text-center text-primary">
						LouShop Login
					</div>
					<div class="card-body">
						<form action="login-user" method="post">
							<div class="form-group">
								<input type="email" class="form-control" name="login-email" placeholder="E-mail address" required>
							</div>
							<div class="form-group">
								<input type="password" class="form-control" name="login-password" placeholder="Password" required>
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-success">
									Log In
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<%@include file="includes/foot.jsp" %>
		</body>
	</html>