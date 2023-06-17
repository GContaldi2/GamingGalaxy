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
			// Recupera l'ID dell'utente dall'input dell'utente
			String productIdString = request.getParameter("user-id");
			int productId = Integer.parseInt(productIdString);

			String password = request.getParameter("modifica-password");

			User user = new User(productId, password,"unused");
			try {
			    UserDao userDao = new UserDao(DbCon.getConnection());
			    boolean success = userDao.changePassword(user, request);
			  
				

                if (success) {
                    out.print("Aggiornamento completato");
                    response.sendRedirect("datiUtente.jsp");
                } else {
                    out.print("Aggiornamento fallito");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
			
	   }
    }


}
