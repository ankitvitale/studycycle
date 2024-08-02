package com.studyCycle.StudyCycle.entity;

import javax.persistence.*;

@Entity
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key of Shopkeeper

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; // Reference to the User entity
    private String banner;

    public User getUser() {
        return user;
    }

//    public void setUser(String user) {
//        this.user = user;

    public void setUser(User user) {
        this.user = user;
    }
//    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Advertisement(User user, String banner) {
        this.user = user;
        this.banner = banner;
    }

    public Advertisement() {
    }

}
