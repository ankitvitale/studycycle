package com.studyCycle.StudyCycle.entity;

import javax.annotation.Priority;
import javax.persistence.*;

@Entity
public class Sell {
    public Sell() {
    }

    public Sell(Product product, User user, Double cost, String type) {
        this.product = product;
        this.user = user;
        this.cost = cost;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sell_prod_id", nullable = false)
    private Long sell_id;
    @OneToOne
    private Product product;
    @ManyToOne
    private User user;
    private Double cost;

    private String type;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSell_id(Long sell_id) {
        this.sell_id = sell_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getSell_id() {
        return sell_id;
    }
}
