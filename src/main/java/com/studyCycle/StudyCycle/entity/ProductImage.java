package com.studyCycle.StudyCycle.entity;

import javax.persistence.*;

@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prod_image;
    @ManyToOne
    private Product product;

    public ProductImage() {
    }

    public ProductImage(String prodImage, Product savedProduct) {
        this.prod_image=prodImage;
        this.product=savedProduct;
    }
}
