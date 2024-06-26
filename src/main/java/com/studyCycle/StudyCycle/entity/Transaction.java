package com.studyCycle.StudyCycle.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double orderAmount;
    private String currency= "INR";
    private LocalDateTime timestamp;
    private String status;
    private String razorpay_orderId;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_prod_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "transactions"})
    private Sell sell;

    private int orderquantity;

    private Double totalprice;

    private Double platformfees;

    private  Double delivery;

    private Double toseller;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    private String orderId;
    public Transaction() {
    }

    public Transaction(String status, User buyer, Sell sell, int orderquantity, Double totalprice, Double platformfees, Double delivery, Double toseller,String orderId) {
     //   this.orderAmount = orderAmount;
        this.status = status;
        this.buyer = buyer;
        this.sell = sell;
        this.orderquantity = orderquantity;
        this.totalprice = totalprice;
        this.platformfees = platformfees;
        this.delivery = delivery;
        this.toseller = toseller;
        this.orderId=orderId;
    }

    public Transaction(double amount, String currency, LocalDateTime timestamp, String status, Sell sell, int quantity) {
        this.orderAmount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
        this.status = status;
        this.sell = sell;
        this.orderquantity = quantity;
    }


    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public int getOrderquantity() {
        return orderquantity;
    }

    public void setOrderquantity(int orderquantity) {
        this.orderquantity = orderquantity;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Double getPlatformfees() {
        return platformfees;
    }

    public void setPlatformfees(Double platformfees) {
        this.platformfees = platformfees;
    }

    public Double getDelivery() {
        return delivery;
    }

    public void setDelivery(Double delivery) {
        this.delivery = delivery;
    }

    public Double getToseller() {
        return toseller;
    }

    public void setToseller(Double toseller) {
        this.toseller = toseller;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return orderAmount;
    }

    public void setAmount(double amount) {
        this.orderAmount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }

    public String getrazorpay_orderId() {
        return razorpay_orderId;
    }

    public void setrazorpay_orderId(String razorpay_orderId) {
        this.razorpay_orderId = razorpay_orderId;
    }
}
