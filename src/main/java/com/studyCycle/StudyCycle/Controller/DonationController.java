package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Payload.ProductRequest;
import com.studyCycle.StudyCycle.Service.DonationService;
import com.studyCycle.StudyCycle.entity.Donate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class DonationController {

    @Autowired
    private DonationService donationService;
    @PostMapping("/addDonationProduct")
    @PreAuthorize("hasRole('User')")
    public void addDonationProduct(@RequestBody ProductRequest productRequest) throws IOException {
         donationService.addDonationProduct(productRequest);
    }

    @PostMapping("/updateDonationProduct/{id}")
    @PreAuthorize("hasRole('User')")

    public void updateDonationProduct(@PathVariable("id") Long id,@RequestBody ProductRequest productRequest) throws IOException {
        donationService.updateDonationProduct(id,productRequest);
    }
    @DeleteMapping("/deleteDonationProduct/{id}")
    @PreAuthorize("hasRole('User')")

    public String deleteRentProduct(@PathVariable("id") Long id) {
        return donationService.deleteRentProduct(id);
    }

    @GetMapping("/getDonationProducts")
    @PreAuthorize("hasRole('User')")

    public List<Donate> getDonationProducts(){
        return  donationService.getDonationProducts();
    }
}
