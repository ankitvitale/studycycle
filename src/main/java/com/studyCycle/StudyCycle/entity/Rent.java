package com.studyCycle.StudyCycle.entity;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rent_prod_id;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "tenant", referencedColumnName = "id")
    private User tenant;
    @OneToOne
    private Product product;
    private Double rent_per_day;
    private int availability;
    private Double deposit_money;
    private Double due_per_day;
    private Date product_adding;
    private Date product_renting;
    private String status;

    public Rent() {
    }

    public Rent(User user, Product product, Double rentPerDay, int availability, Double depositMoney, Double duePerDay, Date date, String available)  {
        this.owner = user;
        this.product = product;
        this.rent_per_day = rentPerDay;
        this.availability = availability;
        this.deposit_money = depositMoney;
        this.due_per_day = duePerDay;
        this.product_adding = date;
        this.status = available;
    }



    public Long getRent_prod_id() {
        return rent_prod_id;
    }

    public void setRent_prod_id(Long rent_prod_id) {
        this.rent_prod_id = rent_prod_id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
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

    public Date getProduct_adding() {
        return product_adding;
    }

    public void setProduct_adding(Date product_adding) {
        this.product_adding = product_adding;
    }

    public Date getProduct_renting() {
        return product_renting;
    }

    public void setProduct_renting(Date product_renting) {
        this.product_renting = product_renting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}