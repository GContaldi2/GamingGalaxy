package cn.gaminggalaxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.gaminggalaxy.connection.DbCon;
import cn.gaminggalaxy.dao.UserDao;
import cn.gaminggalaxy.model.User;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/change-password")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("datiUtente.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()) {
	        // Retrieve the user ID from the user input
	        String productIdString = request.getParameter("user-id");
	        int productId = Integer.parseInt(productIdString);

	        // Retrieve the current and new password from the user input
	        String currentPassword = request.getParameter("password-attuale");
	        String newPassword = request.getParameter("modifica-password");

	        UserDao userDao = new UserDao(DbCon.getConnection());
	        
	        // Retrieve the current password from the database using the user ID
	        String passwordInDatabase = userDao.getPassword(productId);

	        // Check if the current password entered by the user matches the one in the database
	        if (currentPassword.equals(passwordInDatabase)) {
	            // If they match, update the password in the database with the new value
	            User user = new User(productId, newPassword, "unused");
	            boolean success = userDao.changePassword(user, request);

	            if (success) {
	                out.print("Aggiornamento completato");
	                response.sendRedirect("datiUtente.jsp");
	            } else {
	                out.print("Aggiornamento fallito");
	            }
	        } else {
	            // If they don't match, display an error message to the user
	            out.print("La password attuale non è corretta");
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        //e.printStackTrace();
	    }
	}
   


}
