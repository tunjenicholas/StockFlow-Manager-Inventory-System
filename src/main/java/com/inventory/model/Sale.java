package com.inventory.model;

import java.util.Date;

public class Sale {

    private int saleId;
    private int productId;
    private int quantity;
    private double totalPrice;
    private Date saleDate;

    public Sale() {}

    public Sale(int productId, int quantity, double totalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getSaleId() {
        return saleId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Date getSaleDate() {
        return saleDate;
    }
}