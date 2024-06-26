package com.studyCycle.StudyCycle.Service;


import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.Payload.DonationReceipt;
import com.studyCycle.StudyCycle.Payload.ProductRequest;
import com.studyCycle.StudyCycle.Payload.ReceiptResponse;
import com.studyCycle.StudyCycle.Repository.DonateHistryRepository;
import com.studyCycle.StudyCycle.Repository.DonationRepository;
import com.studyCycle.StudyCycle.entity.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private DonateHistryRepository donateHistryRepository;
    @Autowired
    private PaymentService paymentService;
    public Donate addDonationProduct(ProductRequest productRequest) throws IOException {
       // String currentUser = JwtRequestFilter.CURRENT_USER;
       return donationRepository.save(new Donate(userService.findUser(JwtRequestFilter.CURRENT_USER),
                productService.addProduct(productRequest)
                              ));
    }

    public void updateDonationProduct(Long id, ProductRequest productRequest) throws IOException {
        Optional<Donate> donate= donationRepository.findById(id);
        if(donate.isPresent()){
            Donate donationprod= donate.get();
            donationprod.setProduct(productService.updateProduct(donationprod.getProduct().getId(),productRequest));
           donationRepository.save(donationprod);
        }
    }

    public String deleteRentProduct(Long id) {
        Optional<Donate> donate= donationRepository.findById(id);
        donate.ifPresent(value -> donationRepository.delete(value));
        return "removed";
    }
    public List<Donate> getDonationProducts() {
       return donationRepository.findAll();
    }

    public List<Donate> findMatching(String match) {
        return donationRepository.findMatching(match);
    }


    public DonationReceipt getReceipt(Donate orderProd) {
        DonationReceipt donationReceipt= new DonationReceipt();
       return donationReceipt;

    }
    public DonateHistry processPayment(Long id) throws Exception {
        //product
        Optional<Donate> donate = donationRepository.findById(id);
        Donate donationprod = null;
        if (donate.isPresent()) {
            donationprod = donate.get();
        }
        else{
            throw new Exception("wrong donation product");
        }
        //buyer
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User buyer = userService.findUser(JwtRequestFilter.CURRENT_USER);
        DonateHistry t = new DonateHistry();
        t.setDonate(donationprod);


        t.setBuyer(buyer);
        t.setDelivery(50.0);
        t.setPlatformfees(20.0);
        t.setTotalprice(70.0);
        t.setCurrency("INR");
        t.setStatus("Completed");
        t.setTimestamp(LocalDateTime.now());

        try {
            Order order = paymentService.createOrder(70.0);
            t.setRazorpay_orderId(order.get("id"));
          } catch (Exception e) {
           ResponseEntity.status(500).body(e.getMessage());
        }

        return donateHistryRepository.save(t);
    }

}
