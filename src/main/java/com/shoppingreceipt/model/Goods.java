package com.shoppingreceipt.model;

public class Goods {
	
    private String name;
    private double price;
    private int quantity;

    // 必須有無參數建構子和 getter/setter
    public Goods() {}

    public Goods(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    
    
}
