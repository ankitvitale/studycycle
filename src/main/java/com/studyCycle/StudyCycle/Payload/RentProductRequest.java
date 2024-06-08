package com.studyCycle.StudyCycle.Payload;

public class RentProductRequest {
   // public User owner;
    public ProductRequest product;
    public Double rent_per_day;
    public int availability;
    public Double deposit_money;
    public Double due_per_day;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String status;

    public ProductRequest getProduct() {
        return product;
    }

    public void setProduct(ProductRequest product) {
        this.product = product;
    }

    public Double getRent_per_day() {
        return rent_per_day;
    }

    public void setRent_per_day(Double rent_per_day) {
        this.rent_per_day = rent_per_day;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public Double getDeposit_money() {
        return deposit_money;
    }

    public void setDeposit_money(Double deposit_money) {
        this.deposit_money = deposit_money;
    }

    public Double getDue_per_day() {
        return due_per_day;
    }

    public void setDue_per_day(Double due_per_day) {
        this.due_per_day = due_per_day;
    }


   // private Date product_adding;
  //  private  Date product_renting;
   // private String status;
}
