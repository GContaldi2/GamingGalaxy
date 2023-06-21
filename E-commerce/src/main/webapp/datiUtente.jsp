<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="cn.gaminggalaxy.model.*"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth == null) {
    response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>User Data</title>

</head>
<body>
<%@include file="/includes/navbar.jsp"%>
<div class="container">
    <div class="card-header my-3">DATI UTENTE</div>
    <table class="table table-borderless">
        <tr>
            <td><strong>Nome:</strong> <%= auth.getName() %></td>
            <td> </td>
            <td></td>
        </tr>
        <tr>
            <td><strong>Cognome:</strong> <%= auth.getCognome() %></td>
            <td></td>
            <td></td>
        </tr>
        
        
        
        <tr>
            <td><strong>Email:</strong> <%= auth.getEmail() %></td>
            <td>
            	<form action="change-email" method="post">
		    		<input type="hidden" name="user-id" value="<%=auth.getId()%>">
		    		<div class="form-group">
						<label><strong>Nuova Email:</strong></label>
							<input type="text" class="form-control" name="modifica-email" placeholder="nuova email..." >
					</div>
					<div >
							<button type="submit" class="btn btn-primary">Modifica </button>
					</div>
            
            
            	</form>
            
            
            </td>
            <td></td>
        </tr>
        <tr>
           <td><strong>Password:</strong> ********</td>
            
			
			<td>
				<form action="change-password" method="post">
					<input type="hidden" name="user-id" value="<%=auth.getId()%>">
					
            
		    		<div class="form-group">
						<label><strong>Password Attuale:</strong></label>
							<input type="password" class="form-control" name="password-attuale" placeholder="password..." required>
					</div>
			     
		    		
		    		<div class="form-group">
						<label><strong>Nuova Password:</strong></label>
							<input type="password" class="form-control" name="modifica-password" placeholder="nuova password..." >
					</div>
					<div >
							<button type="submit" class="btn btn-primary">Modifica </button>
					</div>
            
            
            	</form>
            
            
            
            
            </td>
        </tr>
        <tr>
            <td><strong>Indirizzo:</strong> <%= auth.getAddress() %></td>
            <td>
            	<form action="change-indirizzo" method="post">
		    		<input type="hidden" name="user-id" value="<%=auth.getId()%>">
		    		<div class="form-group">
						<label><strong>Nuovo Indirizzo:</strong></label>
							<input type="text" class="form-control" name="modifica-indirizzo" placeholder="via street n.X..." >
					</div>
					<div >
							<button type="submit" class="btn btn-primary">Modifica </button>
					</div>
						
			</form>
           </td>
           <td>
            
           </td>
        </tr>
        <tr>
            <td><strong>Citta':</strong> <%= auth.getCity() %></td>
            <td>
            	<form action="change-citta" method="post">
		    		<input type="hidden" name="user-id" value="<%=auth.getId()%>">
		    		<div class="form-group">
						<label><strong>Nuova Citta':</strong></label>
							<input type="text" class="form-control" name="modifica-citta" placeholder="citta'..." >
					</div>
					<div >
							<button type="submit" class="btn btn-primary">Modifica </button>
					</div>
            
            
            	</form>
            </td>
            <td></td>
        </tr>
        <tr>
            <td><strong>CAP:</strong> <%= auth.getZipCode() %></td>
            <td>
            	<form action="change-cap" method="post">
		    		<input type="hidden" name="user-id" value="<%=auth.getId()%>">
		    		<div class="form-group">
						<label><strong>Nuovo CAP:</strong></label>
							<input type="text" class="form-control" name="modifica-cap" placeholder="CAP..." >
					</div>
					<div >
							<button type="submit" class="btn btn-primary">Modifica </button>
					</div>
            
            
            	</form>
            
            
            
            </td>
            <td></td>
        </tr>
        <tr>
            <td><strong>Provincia:</strong> <%= auth.getProvincia() %></td>
            <td>
            	<form action="change-provincia" method="post">
		    		<input type="hidden" name="user-id" value="<%=auth.getId()%>">
		    		<div class="form-group">
						<label><strong>Nuova Provincia:</strong></label>
							<input type="text" class="form-control" name="modifica-provincia" placeholder="Provincia..." >
					</div>
					<div >
							<button type="submit" class="btn btn-primary">Modifica </button>
					</div>
            
            
            	</form>
            
            
            
            
            </td>
            <td></td>
        </tr>
        <tr>
            <td><strong>Numero Carta:</strong> <%= maskCreditCard(auth.getCartNumber()) %></td><!-- Credit card number partially masked -->
            <td>
            	<form action="change-carta" method="post">
		    		<input type="hidden" name="user-id" value="<%=auth.getId()%>">
		    		<div class="form-group">
						<label><strong>Nuovo Numero Carta:</strong></label>
							<input type="text" class="form-control" name="modifica-carta" placeholder="XXXX-XXXX-XXXX-XXXXX..." >
					</div>
					<div >
							<button type="submit" class="btn btn-primary">Modifica </button>
					</div>
            
            
            	</form>
            
            
            
            </td>
            <td></td>
        </tr>
 		<tr> 
 		    <td><strong>Scadenza:</strong> <%= auth.getMese() %></td>   
 		    <td>
 		    	<form action="change-mese" method="post">
		    		<input type="hidden" name="user-id" value="<%=auth.getId()%>">
		    		<div class="form-group">
						<label><strong>Nuova Scadenza:</strong></label>
							<input type="text" class="form-control" name="modifica-mese" placeholder="XX/XXXX..." >
					</div>
					<div >
							<button type="submit" class="btn btn-primary">Modifica </button>
					</div>
            
            
            	</form>
 		    
 		    </td>
 		    <td></td>
 		</tr> 
 		 <tr>
            <td><strong>CVV:</strong> ***</td> <!-- Password masked -->
            <td>
            	<form action="change-cvv" method="post">
		    		<input type="hidden" name="user-id" value="<%=auth.getId()%>">
		    		<div class="form-group">
						<label><strong>Nuovo CVV:</strong></label>
							<input type="text" class="form-control" name="modifica-cvv" placeholder="xxx..." >
					</div>
					<div >
							<button type="submit" class="btn btn-primary">Modifica </button>
					</div>
            
            
            	</form>
            
            
            </td>
            <td></td>
        </tr>
    </table>
</div>
<%@include file="/includes/footer.jsp"%>


</body>
</html>

<%!
    // Method to mask credit card number partially
    private String maskCreditCard(String CartNumber) {
        if (CartNumber.length() >= 16) {
            String lastFourDigits = CartNumber.substring(CartNumber.length() - 2);
            return "xxxx-xxxx-xxxx-xx" + lastFourDigits;
        }
        return CartNumber;
    }



%>