<%@page import="beans.Basket"%>
<%@page import="java.util.ArrayList"%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  		<div class="container">
    		<a class="navbar-brand" href="index.jsp">LouShop</a>
    		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      			<span class="navbar-toggler-icon"></span>
    		</button>
    		<div class="collapse navbar-collapse" id="navbarSupportedContent">
      			<ul class="navbar-nav ml-auto">
        			<li class="nav-item">
          				<a class="nav-link" aria-current="page" href="index.jsp">Home</a>
        			</li>
        			<li class="nav-item">
          				<a class="nav-link" href="basket.jsp">Basket<% if(session.getAttribute("basket-list")!=null && ((ArrayList<Basket>) session.getAttribute("basket-list")).size()>0){%><span class="badge bg-danger"><%= ((ArrayList<Basket>) session.getAttribute("basket-list")).size()%></span><%}%></a>
        			</li>
        			<% if (auth != null) {%>
        			<li class="nav-item">
          				<a class="nav-link" href="orders.jsp">Orders</a>
        			</li>
        			<li class="nav-item">
          				<a class="nav-link" href="logout">SignOut</a>
        			</li>
        			<% } else { %>
        			<li class="nav-item">
          				<a class="nav-link" href="login.jsp">LogIn</a>
        			</li>
        			<% } %>
      			</ul>
    		</div>
  		</div>
	</nav>