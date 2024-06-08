package com.studyCycle.StudyCycle.Payload;

import com.studyCycle.StudyCycle.entity.Category;
import com.studyCycle.StudyCycle.entity.Donate;
import com.studyCycle.StudyCycle.entity.Rent;
import com.studyCycle.StudyCycle.entity.Sell;

import java.util.List;


public class SearchResponse {
    public List<Rent> rentProd;

    public List<Sell> sellProd;

    public List<Donate> donationProd;


    public SearchResponse(List<Rent> rentProd, List<Sell> sellProd, List<Donate> donationProd) {
        this.rentProd = rentProd;
        this.sellProd = sellProd;
        this.donationProd = donationProd;
    }
}
