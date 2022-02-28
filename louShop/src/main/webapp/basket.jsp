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
<%@include file="includes/head.jsp" %>
</head>

<body>
	<%@include file="includes/navbar.jsp" %>
	
	
	<%@include file="includes/foot.jsp" %>
</body>
</html>