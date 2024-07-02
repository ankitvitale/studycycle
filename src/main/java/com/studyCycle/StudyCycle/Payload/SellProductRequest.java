package com.studyCycle.StudyCycle.Payload;

public class SellProductRequest {
    public ProductRequest product;
    public Double cost;
    public  String type;
    public int Quantity;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public ProductRequest getProduct() {
        return product;
    }

    public void setProduct(ProductRequest product) {
        this.product = product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public SellProductRequest(ProductRequest product, Double cost, String type, int quantity) {
        this.product = product;
        this.cost = cost;
        this.type = type;
        Quantity = quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
