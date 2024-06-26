package com.studyCycle.StudyCycle.Payload;

public class DonationReceipt {
    double platformFee,deliveryCharge,total;
    public DonationReceipt() {
        this.deliveryCharge=20.0;
        this.platformFee=20.0;
        this.total=70.0;
    }
}
