package com.studyCycle.StudyCycle.entity;

import javax.persistence.*;


@Entity
public class SellCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sellcartId;

    @OneToOne
    private Product product;

    @OneToOne
    private User email;
    public SellCart(){}
    public SellCart( Product product, User email) {

        this.product = product;
        this.email = email;
    }

    public Integer getSellcartId() {
        return sellcartId;
    }

    public void setSellcartId(Integer sellcartId) {
        this.sellcartId = sellcartId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return email;
    }

    public void setUser(User email) {
        this.email = email;
    }
}
