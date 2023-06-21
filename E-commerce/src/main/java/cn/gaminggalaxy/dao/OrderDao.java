package cn.gaminggalaxy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import cn.gaminggalaxy.model.*;
import cn.gaminggalaxy.dao.*;

public class OrderDao {
	
	private Connection con;

	private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    

	public OrderDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            query = "insert into orders (p_id, u_id, o_quantity, o_date, originalPrice) values(?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getId());
            pst.setInt(2, model.getUid());
            pst.setInt(3, model.getQunatity());
            pst.setString(4, model.getDate());
            pst.setDouble(5, model.getOriginalPrice());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
        	//e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return result;
    }
	

    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
            query = "select * from orders where u_id=? order by orders.o_id desc";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int pId = rs.getInt("p_id");
                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("o_id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setOriginalPrice(rs.getDouble("originalPrice")*rs.getInt("o_quantity"));
                order.setQunatity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));
               
                
                list.add(order);
            }
        } catch (Exception e) {
        	//e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }
    
   
    
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try {
        	String query = "SELECT o.o_id, o.o_date, u.name, u.cognome, p.name, o.o_quantity, o.originalPrice, o.p_id FROM orders o JOIN users u ON o.u_id = u.id JOIN products p ON o.p_id = p.id";
            PreparedStatement pst = this.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int pId = rs.getInt("p_id");
                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("o_id"));
                order.setId(pId);
                order.setName(product.getName());
                order.setOriginalPrice(rs.getDouble("originalPrice")*rs.getInt("o_quantity"));
                order.setQunatity(rs.getInt("o_quantity"));
                order.setDate(rs.getString("o_date"));
                
                
                User user = new User();
                user.setName(rs.getString("name"));
                user.setCognome(rs.getString("cognome"));
                order.setUser(user);

                
                list.add(order);
            }
        } catch (Exception e) {
        	//e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cancelOrder(int id) {
        //boolean result = false;
        try {
            query = "delete from orders where o_id=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } catch (SQLException e) {
        	//e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
}