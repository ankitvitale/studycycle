package com.studyCycle.StudyCycle.Payload;

public class SellProductRequest {
    public ProductRequest product;
    public Double cost;

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


}
