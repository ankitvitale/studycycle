package com.studyCycle.StudyCycle.Payload;

import com.studyCycle.StudyCycle.entity.Category;
import com.studyCycle.StudyCycle.entity.Donate;
import com.studyCycle.StudyCycle.entity.Rent;
import com.studyCycle.StudyCycle.entity.Sell;

import java.util.List;


public class SearchResponse {
    public List<Rent> rentProd;
    public List<Sell> newSellProd;
    public List<Sell> oldSellProd;
    public List<Donate> donationProd;

    public SearchResponse(List<Rent> rentProd, List<Sell> newSellProd, List<Sell> oldSellProd, List<Donate> donationProd) {
        this.rentProd = rentProd;
        this.newSellProd = newSellProd;
        this.oldSellProd = oldSellProd;
        this.donationProd = donationProd;
    }

}
