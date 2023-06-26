<%@page import="java.util.*" %>
<%@page import="cn.gaminggalaxy.dao.ProductDao" %>
<%@page import="cn.gaminggalaxy.connection.DbCon" %>
<%@page import="cn.gaminggalaxy.model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   <% 
   User auth= (User) request.getSession().getAttribute("auth"); 
   if(auth!=null){
	   request.setAttribute("auth",auth);
   }
   
   ProductDao pd=new ProductDao(DbCon.getConnection());
   List<Product> products=pd.getAllProducts();
   
   ArrayList<Cart> cart_list=(ArrayList<Cart>) session.getAttribute("cart-list");
   if(cart_list !=null){
	   request.setAttribute("cart_list", cart_list);
   }
   
   
   %>
<!DOCTYPE html>
<html>
<head>
<title>Welcome to GamingGalaxy</title>
<%@include file="includes/head.jsp" %>
</head>
<body>
	<%@include file="includes/navbar.jsp" %>
	<div class="container">
	<div class="card-header my-3">Bentornato Amministratore</div>
	<div class="row">
		<%
			if( !products.isEmpty()){
				for(Product p:products){
					 
				%>
					<div class="col-md-3 my-3">
					<div class="card w-100" style="width: 18rem;">
		  				<img class="card-img-top" src="images/products/<%=p.getImage()%>" alt="Card image cap">
		  				<div class="card-body">
		    				<h5 class="card-title"><%=p.getName()%></h5>
		    				<h6 class="price">Price: &euro;<%=p.getPrice()%></h6>
		    				<form action="change-price" method="post">
		    				<input type="hidden" name="product-id" value="<%=p.getId()%>">
		    				<div class="form-group">
							<label>Nuovo Prezzo:</label>
							<input type="text" class="form-control" name="modifica-prezzo" placeholder="nuovo prezzo..." >
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-primary">Modifica </button>
							</div>
							<div> </div>
							</form>
		    				<p class="descr">Descrizione: <%=p.getDescr()%></p>
		    				<div class="mt-3 d-flex justify-content-between">
		    					<a class="btn btn-sm btn-danger" href="cancel-product?id=<%=p.getId()%>">Cancella</a>
		    				</div>
		  				</div>
					</div>
			
			
				</div>
				<%
				}
			}
   
   		%>
		
	</div>
	<div class="card w-50 mx-auto my-5">
	<div class="card-header text-center">AGGIUNGI UN PRODOTTO</div>
	<div class="card-body">
	<form action="add-product" method="post">
		<div class="form-group">
		<label>Nome Prodotto</label>
		<input type="text" class="form-control" name="add-nome" placeholder="nome prodotto" required>
		</div>
	
		<div class="form-group">
		<label>Descrizione</label>
		<input type="text" class="form-control" name="add-descr" placeholder="descrizione" required>
		</div>
		
		<div class="form-group">
		<label>Prezzo</label>
		<input type="text" class="form-control" name="add-price" placeholder="Inserire prezzo" required>
		</div>
		
		<div class="form-group">
		<label>Nome Immagine</label>
		<input type="text" class="form-control" name="add-image" placeholder="Inserire nome immagine" required>
		</div>
		
		<div class="text-center">
		<button type="submit" class="btn btn-primary">Aggiungi Prodotto </button>
		</div>

	</form>
	</div>
	</div>
	
	</div>


	<%@include file="includes/footer.jsp" %>
</body>
</html>