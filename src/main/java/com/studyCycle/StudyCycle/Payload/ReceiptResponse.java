package com.studyCycle.StudyCycle.Payload;

import java.util.List;

public class ReceiptResponse {

    public List<Sellinput> order_prod;

    public Double totalMrp;

    public Double platformFee;

    public Double deliveryCharge;

    public Double total;

    public String orderId;
}