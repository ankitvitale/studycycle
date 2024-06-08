package com.studyCycle.StudyCycle.entity;

import javax.persistence.*;
@Entity
public class Donate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donation_prod_id;

    @ManyToOne
    @JoinColumn(name = "doner", referencedColumnName = "id")
    private User user;

    @OneToOne
    private Product product;

    public Donate() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Donate(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
