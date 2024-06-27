package com.studyCycle.StudyCycle.Controller;

import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.studyCycle.StudyCycle.Payload.*;
import com.studyCycle.StudyCycle.Repository.SellRepository;
import com.studyCycle.StudyCycle.Repository.TransactionRepository;
import com.studyCycle.StudyCycle.Service.DonationService;
import com.studyCycle.StudyCycle.Service.PaymentService;
import com.studyCycle.StudyCycle.Service.RentService;
import com.studyCycle.StudyCycle.Service.SellService;
import com.studyCycle.StudyCycle.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private SellService sellService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private RentService rentService;
    @Autowired
    private TransactionRepository transactionRepository;


    public void PaymentConroller(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/sellreceipt")
    @PreAuthorize("hasRole('User')")
    public ReceiptResponse getSellinput(@RequestBody List<Sellinput> order_prod) {
        // Fetch the Sellinput data from the service
        return sellService.getReceipt(order_prod);
    }

    @PostMapping("/createOrdersell")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<String> createOrder(@RequestParam double amount, @RequestParam String orderId) {
        try {

            Order order = paymentService.createOrder(amount);

            List<Transaction> transactions = transactionRepository.findAllByOrderId(orderId);
            for(Transaction t: transactions){
                t.setAmount(amount);
                t.setCurrency("INR");
                t.setStatus("Completed");
                t.setTimestamp(LocalDateTime.now());
                t.setrazorpay_orderId(order.get("id"));
                transactionRepository.save(t);
            }
                   // paymentService.saveTransaction(order.get("id"), amount, "INR", "CREATED", sell);
           // paymentService.createOrder(amount);
            return ResponseEntity.ok("Order created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    //Donate
    @PostMapping("/donatereceipt")
    @PreAuthorize("hasRole('User')")
    public DonationReceipt getDonateinput(@RequestBody Donate order_prod) {
        // Fetch the Sellinput data from the service
        return donationService.getReceipt(order_prod);
    }
    @PostMapping("/donateOrder/{id}")
    @PreAuthorize("hasRole('User')")
    public DonateHistry processPayment(@PathVariable("id") Long id) throws Exception {
       return donationService.processPayment(id);

    }

    //Rent
    @PostMapping("/rentreceipt")
    @PreAuthorize("hasRole('User')")
    public RentReceipt getRentinput(@RequestBody RentInput order_prod) {
        return rentService.getReceipt(order_prod);
    }

    @PostMapping("/rentOrder/{id}")
    @PreAuthorize("hasRole('User')")
    public RentHistory creatRentOrder(@PathVariable("id") Long id) throws Exception {
        return rentService.processPayment(id);

    }
    @GetMapping("/getorderdetails")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {
        Iterable<Transaction> transactions = paymentService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }


}
