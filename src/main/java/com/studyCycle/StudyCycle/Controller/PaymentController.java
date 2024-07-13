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
import java.util.Optional;

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
    public ReceiptResponse getSellinput(@RequestBody SellRequest order_prod) {
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
                t.setStatus("Pending");
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
    @PostMapping("/cancelSellOrder/{orderid}")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<String>  cancelSellOrder(@PathVariable String orderid) {
        try {

            List<Transaction> transactions = transactionRepository.findAllByOrderId(orderid);
            int i=0;
            for(Transaction t: transactions){
                if(i==0){
                    t.getBuyer().setWallet( t.getBuyer().getWallet()+ t.getOrderAmount());
                    i++;
                }
                t.setStatus("Cancelled");
                t.setTimestamp(LocalDateTime.now());
                t.getSell().setQuantity(t.getSell().getQuantity()+ t.getOrderquantity());
                transactionRepository.save(t);
            }

            return ResponseEntity.ok("Order cancelled successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
       }
        @PostMapping("/SucessSellOrder/{transactionid}")
        @PreAuthorize("hasRole('Admin')")
        public Transaction  SucessSellOrder(@PathVariable Long transactionid) {
                Optional<Transaction> transaction_opt = transactionRepository.findById(transactionid);
                if(transaction_opt.isPresent()){
                    Transaction transaction=transaction_opt.get();
                    transaction.getSell().getUser().setWallet( transaction.getSell().getUser().getWallet()+transaction.getToseller());
                    transaction.setStatus("Successful");
                   return transactionRepository.save(transaction);
                }

//                for(Transaction t: transactions){
//                    if(i==0){
//                        t.getSell().getUser().setWallet( t.getBuyer().getWallet()+ toseller);
//                        i++;
//                    }
//                    toseller+=t.getToseller();
//                    // t.setCurrency("INR");
//                    t.setStatus("Successful");
//                    t.setTimestamp(LocalDateTime.now());
//                    // t.setrazorpay_orderId(order.get("id"));
//                    transactionRepository.save(t);
//                }
               // return ResponseEntity.ok("Order cancelled successfully");
           else {
                throw new RuntimeException("Error !! No Transaction");
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
    public DonateHistry processPayment(@PathVariable("id") Long id, @RequestParam Long address_id) throws Exception {
       return donationService.processPayment(id,address_id);

    }

    @PostMapping("/cancelDonateOrder/{id}")
    @PreAuthorize("hasRole('User')")
    public DonateHistry cancelDonateOrder(@PathVariable("id") Long id) throws Exception {
        return donationService.cancelDonateOrder(id);}

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

    @PostMapping("/cancelRentOrder/{id}")
    @PreAuthorize("hasRole('User')")
    public RentHistory cancelRentOrder(@PathVariable("id") Long id) throws Exception {
        return rentService.cancelRentOrder(id);

    }
//    @PostMapping("/SucessRentOrder/{orderid}")
//    @PreAuthorize("hasRole('User')")
//    public RentHistory SucessRentOrder(@PathVariable("id") Long id) throws Exception {
//        return rentService.SucessRentOrder(id);
//
//    }
    @GetMapping("/getorderdetails")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {
        Iterable<Transaction> transactions = paymentService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }


}
