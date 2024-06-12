package com.studyCycle.StudyCycle.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Shopkeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key of Shopkeeper

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; // Reference to the User entity
    private Date login_date;
    private Date due_date;

    public Date getLogin_date() {
        return login_date;
    }

    public void setLogin_date(Date login_date) {
        this.login_date = login_date;
    }

    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    public Shopkeeper() {
    }
    public Shopkeeper(User user, Date login_date, Date due_date) {
        this.user = user;
        this.login_date = login_date;
        this.due_date = due_date;
    }
}
