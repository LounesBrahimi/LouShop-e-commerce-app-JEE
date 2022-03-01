<%@page import="dao.DaoFactory"%>
<%@page import="beans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User auth = (User) request.getSession().getAttribute("auth"); 
   if (auth != null){
	   request.setAttribute("auth", auth);
   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Basket</title>
<style type="text/css">
.table tbody td{
vertical-align: middle;
}
.btn-incre, .btn-decre{
box-shadow: none;
font-size: 20px;
}
</style>
<%@include file="includes/head.jsp" %>
</head>

<body>
	<%@include file="includes/navbar.jsp" %>
	
	<div class="container my-3">
		<div class="d-flex py-3">
			<h3>
				Total Price 0€
			</h3>
			<a class="mx-3 btn btn-primary" href="#">
				Ckeck
			</a>
			<table class="table table-light">
				<thead>
					<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy</th>
					<th scope="col">Cancel</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Collier en diamant</td>
						<td>Colliers</td>
						<td>1000€</td>
						<td>
							<form action="" method="post" class="form-inline">
								<input type="hidden" name="id" value="1" class="form-input">
									<div class="form-group d-flex justify-content-between">
										<a class="btn bnt-sm btn-incre" href="#"><i class="fas fa-plus-square"></i></a> 
										<input type="text" name="quantity" class="form-control"  value="1" readonly> 
										<a class="btn btn-sm btn-decre" href="#"><i class="fas fa-minus-square"></i></a>
									</div>
								<button type="submit" class="btn btn-primary btn-sm">Buy</button>
							</form>
						</td>
						<td><a href="" class="btn btn-sm btn-danger">Remove</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<%@include file="includes/foot.jsp" %>
</body>
</html>