<%@page import="dao.DaoFactory"%>
<%@page import="beans.User"%>
<%@page import="beans.Basket"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User auth = (User) request.getSession().getAttribute("auth"); 
   if (auth != null){
	   request.setAttribute("auth", auth);
   }
   ArrayList<Basket> basketList = (ArrayList<Basket>) session.getAttribute("basket-list");
   List<Basket> basketProducts = null;
   if (basketList != null) {
		basketProducts = DaoFactory.getInstance().getProductDao().listBasketProducts(basketList);
		request.setAttribute("basket_list", basketList);
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
					<%
						if(basketList != null){
							System.out.println("##pas null le catalog dans session##");
							for(Basket basket : basketProducts){%>
								<tr>
								<td><%= basket.getName() %></td>
								<td><%= basket.getCategory() %></td>
								<td><%= basket.getPrice() %>€</td>
								<td>
									<form action="" method="post" class="form-inline">
										<input type="hidden" name="id" value="<%=basket.getId()%>" class="form-input">
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
						<%	}
						} else {
							System.out.println("##null le catalog dans session##");
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	
	<%@include file="includes/foot.jsp" %>
</body>
</html>