package com.studyCycle.StudyCycle.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class DonateHistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency= "INR";
    private LocalDateTime timestamp;
    private String status;
    private String razorpay_orderId;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_prod_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "transactions"})
    private Donate donate;
    private Double totalprice;

    private Double platformfees;

    private  Double delivery;
    public DonateHistry(){}
    public DonateHistry(Long id, String currency, LocalDateTime timestamp, String status, String razorpay_orderId, User buyer,  Donate donate, Double totalprice, Double platformfees, Double delivery) {
        this.id = id;
        this.currency = currency;
        this.timestamp = timestamp;
        this.status = status;
        this.razorpay_orderId = razorpay_orderId;
        this.buyer = buyer;
      //  this.user = user;
        this.donate = donate;
        this.totalprice = totalprice;
        this.platformfees = platformfees;
        this.delivery = delivery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRazorpay_orderId() {
        return razorpay_orderId;
    }

    public void setRazorpay_orderId(String razorpay_orderId) {
        this.razorpay_orderId = razorpay_orderId;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }



    public Donate getDonate() {
        return donate;
    }

    public void setDonate(Donate donate) {
        this.donate = donate;
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
}
