package com.studyCycle.StudyCycle.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.studyCycle.StudyCycle.Repository.DonateHistryRepository;
import com.studyCycle.StudyCycle.Repository.DonationRepository;
import com.studyCycle.StudyCycle.Repository.TransactionRepository;
import com.studyCycle.StudyCycle.Repository.UserRepository;
import com.studyCycle.StudyCycle.entity.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    @Autowired
    private RazorpayClient razorpayClient;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DonationRepository donationRepository;



    private static final double PLATFORM_FEES = 30.0;
    private static final double DELIVERY_FEES = 50.0;
    public Order createOrder(double amount) throws Exception {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // Amount in paise
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_123456");
        orderRequest.put("payment_capture", 1);

        return razorpayClient.Orders.create(orderRequest);
    }

//    public Transaction saveTransaction( String orderId,double amount, String currency, String status, Sell sell) {
//        Transaction transaction = new Transaction( amount, currency, LocalDateTime.now(), status, sell, quantity);
//        transaction.setrazorpay_orderId(orderId); // Set the orderId here
//        return   transactionRepository.save(transaction);
//    }


    public Iterable<Transaction> getAllTransactions() {
        return transactionRepository.findAll();

    }


}

