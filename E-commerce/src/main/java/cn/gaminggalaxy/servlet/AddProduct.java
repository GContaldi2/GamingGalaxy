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
import cn.gaminggalaxy.dao.UserDao;
import cn.gaminggalaxy.model.*;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/add-product")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("administrator.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	        try (PrintWriter out = response.getWriter()) {
	            String name = request.getParameter("add-nome");
	            String descr= request.getParameter("add-descr");
	            String priceString = request.getParameter("add-price");
	            double price = Double.parseDouble(priceString);
	            String image = request.getParameter("add-image");
	            
	            Product product = new Product(name, descr, price, image);

	            try {
	                ProductDao productDao = new ProductDao(DbCon.getConnection());
	                boolean success = productDao.insertProduct(product);

	                if (success) {
	                    out.print("Prodotto aggiunto con successo");
	                    response.sendRedirect("administrator.jsp");
	                } else {
	                    out.print("Si è verificato un errore");
	                }
	            } catch (ClassNotFoundException | SQLException e) {
	                //e.printStackTrace();
	            }

	        }
	}

}
