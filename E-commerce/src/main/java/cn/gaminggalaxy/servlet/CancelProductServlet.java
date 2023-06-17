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

/**
 * Servlet implementation class CancelProductServlet
 */
@WebServlet("/cancel-product")
public class CancelProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if(id != null) {
				ProductDao productDao = new ProductDao(DbCon.getConnection());
				productDao.cancelProduct(Integer.parseInt(id));
			}
			response.sendRedirect("administrator.jsp");
		} catch (ClassNotFoundException|SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
	

}
