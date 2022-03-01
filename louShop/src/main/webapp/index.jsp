<%@page import="servlets.ProductServlet"%>
<%@page import="dao.DaoFactory"%>
<%@page import="beans.User"%>
<%@page import="beans.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User auth = (User) request.getSession().getAttribute("auth"); 
   if (auth != null){
	   request.setAttribute("auth", auth);
   }
   List<Product> products = DaoFactory.getInstance().getProductDao().listProducts();
%>

<!DOCTYPE html>
<html>
<head>
<title>LouShop</title>
<%@include file="includes/head.jsp" %>
</head>

<body>
	<%@include file="includes/navbar.jsp" %>

	<div class="container">
		<div class="card-header my-3">
			All Products
		</div>
		<div class="row">
			<% if (!products.isEmpty()){
				for(Product product : products){
			%>
				<div class="col-md-3">
					<div class="card w-100" style="width: 18rem;">
  						<img src="pictures/<%=product.getImage() %>" class="card-img-top" alt="...">
  							<div class="card-body">
    							<h5 class="card-title"><%= product.getName() %></h5>
    							<h6 class="price">Price <%= ""+product.getPrice() %>€</h6>
    							<h6 class="category">Category: <%= product.getCategory() %></h6>
    							<div class="mt-3 d-flex justify-content-between">
    								<a href="add-to-basket?id=<%=product.getId() %>" class="btn btn-dark">Add to Basket</a>
    								<a href="#" class="btn btn-primary">Buy</a>
    							</div>
  							</div>
					</div>
				</div>
			<%}
			}
			%>
		</div>
	</div>
	<%@include file="includes/foot.jsp" %>
</body>
</html>