package com.studyCycle.StudyCycle.Payload;

import com.studyCycle.StudyCycle.entity.DonateCart;
import com.studyCycle.StudyCycle.entity.RentCart;
import com.studyCycle.StudyCycle.entity.SellCart;

import java.util.List;

public class AllCartResponse {
    private List<SellCart> sellCarts;
    private List<DonateCart> donateCarts;
    private List<RentCart> rentCarts;

    public List<SellCart> getSellCarts() {
        return sellCarts;
    }

    public void setSellCarts(List<SellCart> sellCarts) {
        this.sellCarts = sellCarts;
    }

    public List<DonateCart> getDonateCarts() {
        return donateCarts;
    }

    public void setDonateCarts(List<DonateCart> donateCarts) {
        this.donateCarts = donateCarts;
    }

    public List<RentCart> getRentCarts() {
        return rentCarts;
    }

    public void setRentCarts(List<RentCart> rentCarts) {
        this.rentCarts = rentCarts;
    }
}
