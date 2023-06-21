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
import cn.gaminggalaxy.model.*;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/user-register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("register-email");
            String password = request.getParameter("register-password");
            String name= request.getParameter("information-nome");
            String cognome = request.getParameter("information-cognome");
            String address  = request.getParameter("information-address");
            String city = request.getParameter("information-city");
            String provincia = request.getParameter("information-provincia");
            String zipCode  = request.getParameter("information-cap");
            String cartNumber = request.getParameter("information-carta");
            String mese= request.getParameter("information-mese");
            String cvv= request.getParameter("information-cvv");


            User user = new User(email, password, name, cognome, address, city, provincia, zipCode, cartNumber,mese,cvv);

            try {
                UserDao userDao = new UserDao(DbCon.getConnection());
                boolean success = userDao.registerUser(user);

                if (success) {
                    out.print("User registered successfully");
                    response.sendRedirect("login.jsp");
                } else {
                    out.print("Registration failed");
                }
            } catch (ClassNotFoundException | SQLException e) {
                //e.printStackTrace();
            }

        }
    }

}