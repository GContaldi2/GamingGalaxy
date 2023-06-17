<%@page import="java.text.DecimalFormat" %>
<%@page import="cn.gaminggalaxy.dao.ProductDao" %>
<%@page import="java.util.*" %>
<%@page import="cn.gaminggalaxy.connection.DbCon" %>
<%@page import="cn.gaminggalaxy.model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <% 
   
   DecimalFormat dcf=new DecimalFormat("#.##");
   request.setAttribute("dcf",dcf);
   User auth= (User) request.getSession().getAttribute("auth"); 
   if(auth!=null){
	   request.setAttribute("auth",auth);
   }
   
   
   ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list");
   List<Cart> cartProduct=null;
   if(cart_list !=null){
	   ProductDao pDao= new ProductDao(DbCon.getConnection());
	   cartProduct= pDao.getCartProduct(cart_list);
	   double total= pDao.getTotalCartPrice(cart_list);
	   request.setAttribute("cart_list", cart_list);
	   request.setAttribute("total",total);
	   
   }
   %>
<!DOCTYPE html>
<html>
<head>
<title>Cart Page</title>
<%@include file="includes/head.jsp" %>
<link rel="stylesheet" type="text/css"  href="styles/style.css" >

</head>
<body>
	<%@include file="includes/navbar.jsp" %>
	<div class="container">
	<div class="d-flex py-3"><h3>TOTALE: &euro; ${ (total>0)?dcf.format(total):0 }</h3><a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a> </div>
	<table class="table table-loght">
		<thead>
			<tr>
				<th scope="col"> Nome Prodotto</th>
				<th scope="col"> Prezzo</th>
				<th scope="col"> Compra Singolarmente</th>
				<th scope="col"> Cancella</th>
			</tr>
		</thead>
		
		<tbody>
		<% if(cart_list != null){
				for(Cart c:cartProduct){%>
					<tr>
					<td> <%= c.getName()%></td>
					<td> &euro;<%= dcf.format(c.getPrice())%> </td>
					<td> 
					<form action="order-now" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
						<div class="form-group d-flex justify-content-between">
							<a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%= c.getId()%>"><i class="fas fa-minus-square"></i></a>
							<input type="text" name="quantity" class="form-control" value="<%= c.getQuantity()%>" readonly>
							<a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%= c.getId()%>"><i class="fas fa-plus-square"></i></a>
						</div>
						<button type="submit" class="btn btn-primary btn-sm">Compra</button>
					</form>
					
					</td>
					<td><a class="btn btn-sm btn-danger" href="remove-from-cart?id=<%= c.getId()%>">Rimuovi</a> </td>
				</tr>
					
					
			<%	}
			
		}	
		%>
			<tr>
			
		
		
		</tbody>
	</table>
	
	</div>


	<%@include file="includes/footer.jsp" %>
</body>
</html>