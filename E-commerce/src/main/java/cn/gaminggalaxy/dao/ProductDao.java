package cn.gaminggalaxy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import cn.gaminggalaxy.model.*;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	
	public Product getProductById(int productId) {
	    Product product = null;
	    try {
	        String query = "SELECT * FROM products WHERE id=?";
	        PreparedStatement pst = this.con.prepareStatement(query);
	        pst.setInt(1, productId);
	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String descr = rs.getString("descr");
	            double price = rs.getDouble("price");
	            String image = rs.getString("image");
	            
	            product = new Product(id, name, descr, price, image);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return product;
	}
	
	
	
	public List<Product> getAllProducts(){
		List<Product> products=new ArrayList<Product>();
		
		try {
			query="select * from products";
			pst=this.con.prepareStatement(query);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				Product row=new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setDescr(rs.getString("descr"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return products;
		
	}
	public List<Cart> getCartProduct(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query="select * from products where id=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs= pst.executeQuery();
					while(rs.next()) {
						Cart row=new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
						
					}
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
			}
		
		return products;
	}
	
	 public Product getSingleProduct(int id) {
		 Product row = null;
	        try {
	            query = "select * from products where id=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new Product();
	                row.setId(rs.getInt("id"));
	                row.setName(rs.getString("name"));
	                row.setPrice(rs.getDouble("price"));
	                row.setImage(rs.getString("image"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return row;
	    }
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum=0;
		
		try {
			if(cartList.size()>0) {
				for(Cart item:cartList) {
					query="select price from products where id=?";
					pst=this.con.prepareStatement(query);
					pst.setInt(1,  item.getId());
					rs=pst.executeQuery();
					
					while(rs.next()) {
						sum+=rs.getDouble("price")*item.getQuantity();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return sum;
	}
	
	
	public boolean insertProduct(Product model) {
        boolean result = false;
        try {
            query = "insert into products (id, name, descr, price, image) values(?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getId());
            pst.setString(2, model.getName());
            pst.setString(3, model.getDescr());
            pst.setDouble(4, model.getPrice());
            pst.setString(5, model.getImage());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
	
	
	public boolean changePrice(Product model) {
		try {
	        String query = "UPDATE products SET price=? WHERE id=? ";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setDouble(1, model.getPrice());
	        pst.setInt(2, model.getId());
	       
	        
	        int rowsAffected = pst.executeUpdate();
	        
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}
	
	
	public void cancelProduct(int id) {
        //boolean result = false;
        try {
            query = "delete from products where id=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
}
