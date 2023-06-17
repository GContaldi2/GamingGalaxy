<%@page import="java.text.DecimalFormat"%>
<%@page import="cn.gaminggalaxy.connection.DbCon"%>
<%@page import="cn.gaminggalaxy.dao.*"%>
<%@page import="cn.gaminggalaxy.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
	DecimalFormat dcf = new DecimalFormat("#.##");
	request.setAttribute("dcf", dcf);
	User auth = (User) request.getSession().getAttribute("auth");
	List<Order> orders = null;
	if (auth != null) {
	    request.setAttribute("person", auth);
	    OrderDao orderDao  = new OrderDao(DbCon.getConnection());
		orders = orderDao.userOrders(auth.getId());
	}else{
		response.sendRedirect("login.jsp");
	}
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
	if (cart_list != null) {
		request.setAttribute("cart_list", cart_list);
	}
	
	%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>E-Commerce Cart</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	<div class="container">
		<div class="card-header my-3">Tutti gli ordini</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Data</th>
					<th scope="col">Nome Prodotto</th>
					<th scope="col">Quantita'</th>
					<th scope="col">Prezzo</th>
					<th scope="col">Cancella</th>
				</tr>
			</thead>
			<tbody>
			<%
			if (orders != null) {
    			for (Order o : orders) {
    	
%>
        <tr>
            <td><%= o.getDate() %></td>
            <td><%= o.getName() %></td>
            <td><%= o.getQunatity()  %></td>
            <td><%= dcf.format(o.getOriginalPrice()) %></td>
            <td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%= o.getOrderId() %>">Cancella Ordine</a></td>
        </tr>
<%
    }
}
%>
			
			</tbody>
		</table>
	</div>
	<%@include file="/includes/footer.jsp"%>
</body>
</html>