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
public class OrderedFood {
	String foodid,name;
	String unit_price;
	int quantity;

	public OrderedFood(String foodid, String name, String unit_price, int quantity) {
		this.foodid = foodid;
		this.name = name;
		this.unit_price = unit_price;
		this.quantity = quantity;
	}

	public String getFoodid() {
		return foodid;
	}

	public void setFoodid(String foodid) {
		this.foodid = foodid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
