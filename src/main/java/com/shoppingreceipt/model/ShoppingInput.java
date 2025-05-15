package com.shoppingreceipt.model;

import java.util.List;

public class ShoppingInput {
	
    private String location;
    private List<Goods> goods;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Goods> getGoods() {
		return goods;
	}
	public void setItems(List<Goods> goods) {
		this.goods = goods;
	}

   
}
