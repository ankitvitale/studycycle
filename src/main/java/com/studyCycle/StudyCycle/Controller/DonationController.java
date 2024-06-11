package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Payload.ProductRequest;
import com.studyCycle.StudyCycle.Service.DonationService;
import com.studyCycle.StudyCycle.entity.Donate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DonationController {

    @Autowired
    private DonationService donationService;
    @PostMapping("/addDonationProduct")
    public void addDonationProduct(@RequestBody ProductRequest productRequest) {
         donationService.addDonationProduct(productRequest);
    }

    @PostMapping("/updateDonationProduct/{id}")
    public void updateDonationProduct(@PathVariable("id") Long id,@RequestBody ProductRequest productRequest) {
        donationService.updateDonationProduct(id,productRequest);
    }
    @DeleteMapping("/deleteDonationProduct/{id}")
    public String deleteRentProduct(@PathVariable("id") Long id) {
        return donationService.deleteRentProduct(id);
    }

    @GetMapping("/getDonationProducts")
    public List<Donate> getDonationProducts(){
        return  donationService.getDonationProducts();
    }
}
