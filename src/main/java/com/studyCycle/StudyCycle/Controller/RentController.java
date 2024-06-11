package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Payload.RentProductRequest;
import com.studyCycle.StudyCycle.Service.RentService;
import com.studyCycle.StudyCycle.entity.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentController {

    @Autowired
    private RentService rentService;
    @PostMapping("/addRentProduct")
    @PreAuthorize("hasRole('User')")
    public String addRentProduct(@RequestBody RentProductRequest rentProductRequest) {
        return rentService.addRentProduct(rentProductRequest);
    }

    @PutMapping("/updateRentProduct/{id}")
    public String updateRentProduct(@PathVariable("id") Long id,@RequestBody RentProductRequest rentProductRequest) {
        return rentService.updateRentProduct(id, rentProductRequest);
    }
    @DeleteMapping("/deleteRentProduct/{id}")
    public String deleteRentProduct(@PathVariable("id") Long id) {
        return rentService.deleteRentProduct(id);
    }

    @GetMapping("/getAvailableRentals")
    public List<Rent> getRentals(){
        return  rentService.getRentals();
    }
}
