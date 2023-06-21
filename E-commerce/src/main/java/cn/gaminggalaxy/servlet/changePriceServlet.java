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
import cn.gaminggalaxy.dao.ProductDao;
import cn.gaminggalaxy.model.Product;

/**
 * Servlet implementation class changePriceServlet
 */
@WebServlet("/change-price")
public class changePriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("administrator.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try (PrintWriter out = response.getWriter()) {
			// Recupera l'ID del prodotto dall'input dell'utente
			String productIdString = request.getParameter("product-id");
			int productId = Integer.parseInt(productIdString);
			
			String priceString = request.getParameter("modifica-prezzo");
			Double price = null;
			if (priceString != null) {
			    price = Double.parseDouble(priceString);
			   
			}
			
			Product product = new Product(productId,price);
			try {
                ProductDao productDao = new ProductDao(DbCon.getConnection());
                boolean success = productDao.changePrice(product);
				

                if (success) {
                    out.print("Aggiornamento completato");
                    response.sendRedirect("administrator.jsp");
                } else {
                    out.print("Aggiornamento fallito");
                }
            } catch (ClassNotFoundException | SQLException e) {
                //e.printStackTrace();
            }
			
			
		}
	}

}
