package com.studyCycle.StudyCycle.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class RentHistory {
    public RentHistory() {
    }


    public Long getRent_order_id() {
        return rent_order_id;
    }

    public void setRent_order_id(Long rent_order_id) {
        this.rent_order_id = rent_order_id;
    }

    public String getRazorpay_orderId() {
        return razorpay_orderId;
    }

    public void setRazorpay_orderId(String razorpay_orderId) {
        this.razorpay_orderId = razorpay_orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getRent_days() {
        return rent_days;
    }

    public void setRent_days(int rent_days) {
        this.rent_days = rent_days;
    }

    public Double getRent_cost() {
        return rent_cost;
    }

    public void setRent_cost(Double rent_cost) {
        this.rent_cost = rent_cost;
    }

    public Double getReturn_amt() {
        return return_amt;
    }

    public void setReturn_amt(Double return_amt) {
        this.return_amt = return_amt;
    }

    public LocalDateTime getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDateTime return_date) {
        this.return_date = return_date;
    }

    public Double getFinal_return() {
        return final_return;
    }

    public void setFinal_return(Double final_return) {
        this.final_return = final_return;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rent_order_id;

    private String razorpay_orderId;
 //   private Double transaction;
    private String status;
    private LocalDateTime timestamp;

    private int rent_days;

    private Double rent_cost;

    private Double return_amt;

    private LocalDateTime return_date;

    private Double final_return;

    private Double total;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_prod_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "transactions"})
    private Rent rent;
    private Double platformfees;
    private  Double delivery;




    public RentHistory(String status, int rent_days, Double rent_cost, Double return_amt, LocalDateTime return_date, Double final_return,Double total, Rent rent, Double platformfees, Double delivery) {
      //  this.razorpay_orderId = razorpay_orderId;
        this.status = status;
      //  this.timestamp = timestamp;
        this.rent_days = rent_days;
        this.rent_cost = rent_cost;
        this.return_amt = return_amt;
        this.return_date = return_date;
        this.final_return = final_return;
        this.total=total;
        this.rent = rent;
        this.platformfees = platformfees;
        this.delivery = delivery;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
