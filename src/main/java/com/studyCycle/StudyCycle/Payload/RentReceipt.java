package com.studyCycle.StudyCycle.Payload;

import java.util.List;

public class RentReceipt {

    public Long rent_order_id;

    public Double totalDeposit;

    public Double platformFee;

    public Double deliveryCharge;

    public Double total;

    public RentReceipt(Long order_prod, Double totalDeposit, Double platformFee, Double deliveryCharge, Double total) {
        this.rent_order_id = order_prod;
        this.totalDeposit = totalDeposit;
        this.platformFee = platformFee;
        this.deliveryCharge = deliveryCharge;
        this.total = total;
    }
}