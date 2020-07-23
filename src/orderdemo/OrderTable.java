/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderdemo;

/**
 *
 * @author alexv
 */
public class OrderTable {
	int orderNumber;
	String orderDate,orderStatus,paymentMethod;
	double	paymentAmount;

	public OrderTable(int orderNumber, String orderDate, String orderStatus, String paymentMethod, double paymentAmount) {
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.paymentMethod = paymentMethod;
		this.paymentAmount = paymentAmount;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	




	
}
