<%@page import="java.text.DecimalFormat"%>
<%@page import="cn.gaminggalaxy.dao.*"%>
<%@page import="cn.gaminggalaxy.connection.DbCon"%>
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
	orders = orderDao.getAllOrders();
	 request.setAttribute("orders", orders);
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
		<div class="card-header my-3">TUTTI GLI ORDINI </div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Data</th>
					<th scope="col">Nome Cliente</th>
					<th scope="col">Cognome Cliente</th>
					<th scope="col">Nome Prodotto</th>
					<th scope="col">Quantita'</th>
					<th scope="col">Prezzo</th>
				</tr>
			</thead>
			<tbody>
			<%
				List<Order> ordersList = (List<Order>) request.getAttribute("orders");
				if (ordersList != null && !ordersList.isEmpty()) {
    				for (Order o : ordersList) {
       		 %>
        			<tr>
            			<td><%= o.getDate() %></td>
            			<td><%= o.getUser().getName() %></td>
           				 <td><%= o.getUser().getCognome() %></td>
            			<td><%= o.getName() %></td>
           				 <td><%= o.getQunatity() %></td>
            			<td><%= dcf.format(o.getOriginalPrice()) %></td>
       				 </tr>
       	 			<%
    				}
				} else {
   				 %>
   			 <tr>
        		<td colspan="6">Nessun ordine trovato.</td>
    		</tr>
   			 <%
			}
			%>
			</tbody>
		</table>
	</div>
	<%@include file="/includes/footer.jsp"%>
</body>
</html>