<%@page import="java.util.List"%>
<%@page import="dao.DaoFactory"%>
<%@page import="beans.User"%>
<%@page import="beans.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% User auth = (User) request.getSession().getAttribute("auth"); 
   List<Order> orders = null;
   if (auth != null){
	   request.setAttribute("auth", auth);
	   orders = DaoFactory.getInstance().getOrderDao().userOrders(auth.getId());
   } else {
	   response.sendRedirect("login.jsp");
   }
%>
<!DOCTYPE html>
<html>
<head>
<title>Orders</title>
<%@include file="includes/head.jsp" %>
</head>

<body>
	<%@include file="includes/navbar.jsp" %>

	<div class="container">
		<div class="card-header my-3">All Orders</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Quantity</th>
					<th scope="col">Price</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(orders != null){
						for(Order order:orders){%>
							<tr>
								<td><%=order.getDate() %></td>
								<td><%=order.getName() %></td>
								<td><%=order.getCategory() %></td>
								<td><%=order.getQuantity() %></td>
								<td><%=""+order.getPrice()%></td>
							</tr>
						<%}
					}
				%>
			</tbody>
		</table>
	</div>




	<%@include file="includes/foot.jsp" %>
</body>
</html>