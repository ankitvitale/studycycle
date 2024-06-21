package com.studyCycle.StudyCycle.entity;

import javax.persistence.*;

@Entity
public class RentCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rent_prod_id;

    @OneToOne
    private Product product;

    @OneToOne
    private User email;
    public RentCart(){}
    public RentCart( Product product, User email) {

        this.product = product;
        this.email = email;
    }

    public Long getRent_prod_id() {
        return rent_prod_id;
    }

    public void setRent_prod_id(Long rent_prod_id) {
        this.rent_prod_id = rent_prod_id;
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
