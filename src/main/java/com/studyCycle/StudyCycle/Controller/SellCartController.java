package com.studyCycle.StudyCycle.Controller;


import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.Payload.AllCartResponse;
import com.studyCycle.StudyCycle.Repository.*;
import com.studyCycle.StudyCycle.Service.SellCartService;
import com.studyCycle.StudyCycle.entity.DonateCart;
import com.studyCycle.StudyCycle.entity.RentCart;
import com.studyCycle.StudyCycle.entity.SellCart;
import com.studyCycle.StudyCycle.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SellCartController {

    @Autowired
    SellCartService sellCartService;

    @Autowired
    UserRepository userRepository;
@Autowired
CartRepository cartRepository;

    @Autowired
    RentCartRepository rentCartRepository;

    @Autowired
    DonateCartRepository donateCartRepository;

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/addToCart/{productId}"})
    public SellCart addToCart(@PathVariable(name = "productId") Integer productId) {

        return sellCartService.addToCart(productId);

    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/addToRentCart/{rentId}"})
    public RentCart addToCartRent(@PathVariable(name = "rentId") Integer rent_prod_id) {

        return sellCartService.addToCartRent(rent_prod_id);

    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/addToDonateCart/{donateId}"})
    public DonateCart addToDonateCart(@PathVariable(name = "donateId") Integer donation_prod_id) {

        return sellCartService.addToDonateCart(donation_prod_id);

    }
    @GetMapping("/getAllcartitem")
    public AllCartResponse getCartDetails() {
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User email = userRepository.findByEmail(currentUser);

        if (email == null) {
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        List<SellCart> sellCarts = cartRepository.findByEmail(email);
        List<DonateCart> donateCarts = donateCartRepository.findByEmail(email);
        List<RentCart> rentCarts = rentCartRepository.findByEmail(email);

        AllCartResponse response = new AllCartResponse();
        response.setSellCarts(sellCarts);
        response.setDonateCarts(donateCarts);
        response.setRentCarts(rentCarts);

        return response;
        //return  ResponseEntity.ok(response);
    }
}