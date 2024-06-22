package com.studyCycle.StudyCycle.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.annotation.Priority;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Sell {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sell_prod_id", nullable = false)
    private Long sell_id;
    @OneToOne
    private Product product;
    @ManyToOne
    private User user;
    private Double cost;
    private int Quantity;
    private String type;
    @OneToMany(mappedBy = "sell", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    public Sell() {
    }

//    public Sell(Product product, User user, Double cost, int quantity,String type) {
//        this.product = product;
//        this.user = user;
//        this.cost = cost;
//        this.quantity=quantity;
//        this.type = type;
//    }


    public Sell( Product product, User user, Double cost, int quantity, String type) {

        this.product = product;
        this.user = user;
        this.cost = cost;
        Quantity = quantity;
        this.type = type;

    }

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
