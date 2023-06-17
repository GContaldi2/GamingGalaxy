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
 * Servlet implementation class ChangeMeseServlet
 */
@WebServlet("/change-mese")
public class ChangeMeseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeMeseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()) {
			// Recupera l'ID dell'utente dall'input dell'utente
			String productIdString = request.getParameter("user-id");
			int productId= Integer.parseInt(productIdString);

			String mese = request.getParameter("modifica-mese");

			User user = new User(productId,mese,0,0,0,0,0,0);
			try {
			    UserDao userDao = new UserDao(DbCon.getConnection());
			    boolean success = userDao.changeMese(user, request);
			  
				

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

