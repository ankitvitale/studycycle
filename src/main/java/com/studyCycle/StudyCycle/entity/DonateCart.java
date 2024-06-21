package com.studyCycle.StudyCycle.entity;

import javax.persistence.*;

@Entity
public class DonateCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donation_prod_id;
    @OneToOne
    private Product product;

    @OneToOne
    private User email;
    public DonateCart(){}
    public DonateCart( Product product, User email) {

        this.product = product;
        this.email = email;
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getDonation_prod_id() {
        return donation_prod_id;
    }

    public void setDonation_prod_id(Long donation_prod_id) {
        this.donation_prod_id = donation_prod_id;
    }

    public User getUser() {
        return email;
    }

    public void setUser(User email) {
        this.email = email;
    }
}
