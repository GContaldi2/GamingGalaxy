package cn.gaminggalaxy.model;

import cn.gaminggalaxy.model.*;

public class Order extends Product{
	private int orderId;
	private int uid;
	private int qunatity;
	private String date;
	private Double originalPrice;
	private User user;
	
	
	public  Order() {
	}
	
	  

	public void setUser(User user) {
	       this.user = user;
	}

	public User getUser() {
	      return this.user;
	}
	   
	
	public Order(int orderId, int uid, int qunatity, String date) {
		
		this.orderId = orderId;
		this.uid = uid;
		this.qunatity = qunatity;
		this.date = date;
	}

	public Order(int uid, int qunatity, String date) {
		super();
		this.uid = uid;
		this.qunatity = qunatity;
		this.date = date;
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQunatity() {
		return qunatity;
	}
	public void setQunatity(int qunatity) {
		this.qunatity = qunatity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}



	public Double getOriginalPrice() {
		return originalPrice;
	}



	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	
}
