<%@page import="java.util.*" %>
<%@page import="cn.gaminggalaxy.dao.ProductDao" %>
<%@page import="cn.gaminggalaxy.connection.DbCon" %>
<%@page import="cn.gaminggalaxy.model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    
<% 
User auth = (User) request.getSession().getAttribute("auth"); 
if (auth != null) {
    request.setAttribute("auth", auth);
}

ProductDao pd = new ProductDao(DbCon.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
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
        <div class="card-header my-3">TUTTI I PRODOTTI</div>
        <div class="row">
            <%
            if (!products.isEmpty()) {
                for (Product p : products) {
                    %>
                   <div class="col-md-3 my-3">
    					<div class="card product-card">
        					<img class="card-img-top" src="images/products/<%=p.getImage()%>" alt="Card image cap">
       						 <div class="card-body">
           						 <h5 class="card-title"><%=p.getName()%></h5>
           						 <h6 class="price">Prezzo: &euro;<%=p.getPrice()%></h6>
            					 <p class="descr"> <%=p.getDescr()%></p>
            					<div class="mt-3 d-flex flex-column justify-content-between">
                					<a href="add-to-cart?id=<%=p.getId()%>" class="btn btn-dark mb-2 " >Carrello</a>
                					<a href="order-now?quantity=1&id=<%=p.getId()%>" class="btn btn-primary mt-2">Compra Ora</a>
            					</div>
       						</div>
    					</div>
				</div>
                <%
                }
            } 
                %>
                        
        </div>
    </div>

  

    <%@include file="includes/footer.jsp" %>
</body>
</html>
