<%@page import="java.util.*" %>
<%@page import="cn.gaminggalaxy.model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <% 
   User auth= (User) request.getSession().getAttribute("auth"); 
   if(auth!=null){
	   response.sendRedirect("index.jsp");
   }
   
   
   ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list");
   if(cart_list !=null){
	   request.setAttribute("cart_list", cart_list);
   }
   
   %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart Register</title>
<%@include file="includes/head.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container">
	<div class="card w-50 mx-auto my-5">
	<div class="card-header text-center">DATI DI ACCESSO</div>
	<div class="card-body">
	<form action="user-register" method="post">
		<div class="form-group">
		<label>Email</label>
		<input type="email" class="form-control" name="register-email" placeholder="email" required>
		</div>
	
		<div class="form-group">
		<label>Password</label>
		<input type="password" class="form-control" name="register-password" placeholder="*******" required>
		</div>
		
		<div class="card-header text-center">DATI DI SPEDIZIONE</div>
		
		<div class="form-group">
		<label>Inserire Nome</label>
		<input type="text" class="form-control" name="information-nome" placeholder="Inserire nome" required>
		</div>
		
		<div class="form-group">
		<label>Inserire Cognome</label>
		<input type="text" class="form-control" name="information-cognome" placeholder="Inserire cognome" required>
		</div>
		
		<div class="form-group">
		<label>Indirizzo di spedizione</label>
		<input type="text" class="form-control" name="information-address" placeholder="Inserire l'indirizzo" required>
		</div>
		
		<div class="form-group">
		<label>Citta'</label>
		<input type="text" class="form-control" name="information-city" placeholder="Inserire citta'" required>
		</div>
		
	
		<div class="form-group">
		<label>Provincia</label>
		<input type="text" class="form-control" name="information-provincia" placeholder="Inserire la provincia" required>
		</div>
		
		<div class="form-group">
		<label>CAP</label>
		<input type="text" class="form-control" name="information-cap" placeholder="Inserire il cap" required>
		</div>
		
		<div class="card-header text-center">DATI DI PAGAMENTO</div>
		
		<div class="form-group">
		<label>Numero Carta</label>
		<input type="text" class="form-control" name="information-carta" placeholder="XXXX-XXXX-XXXX-XXXX" required>
		</div>
		
		<div class="form-group">
		<label>Validita' della carta</label>
		<input type="text" class="form-control" name="information-mese" placeholder="mm/YYYY" required>
		</div>
		
		<div class="form-group">
		<label>CVV</label>
		<input type="text" class="form-control" name="information-cvv" placeholder="xxx" required>
		</div>
	
	
	
		<div class="text-center">
		<button type="submit" class="btn btn-primary">Registrati </button>
		</div>

	</form>
	</div>
	</div>
	</div>




<%@include file="includes/footer.jsp" %>
</body>
</html>