package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Payload.RentProductRequest;
import com.studyCycle.StudyCycle.Repository.RentRepository;
import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.entity.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public String addRentProduct(RentProductRequest rentProductRequest) {
       // String currentUser = JwtRequestFilter.CURRENT_USER;

        rentRepository.save(new Rent(userService.findUser(JwtRequestFilter.CURRENT_USER)  ,
                              productService.addProduct(rentProductRequest.product),
                              rentProductRequest.getRent_per_day(),
                              rentProductRequest.getAvailability(),
                              rentProductRequest.getDeposit_money(),
                              rentProductRequest.getDue_per_day(),
                              Date.valueOf(LocalDate.now()),
                              "available"));

   return "yes";
    }

    public String updateRentProduct(Long id, RentProductRequest rentProductRequest) {
        Optional<Rent> rent= rentRepository.findById(id);
        if(rent.isPresent()){
            Rent rentprod= rent.get();
           //update this

            rentprod.setProduct(productService.updateProduct(rentprod.getProduct().getId(),rentProductRequest.product));
            rentprod.setDeposit_money(rentProductRequest.getDeposit_money());
            rentprod.setAvailability(rentProductRequest.getAvailability());
            rentprod.setRent_per_day(rentProductRequest.getRent_per_day());
            rentprod.setDue_per_day(rentProductRequest.getDue_per_day());
            rentprod.setStatus(rentProductRequest.getStatus());

        }
        return "yes";
    }

    public String deleteRentProduct(Long id) {
        Optional<Rent> rent= rentRepository.findById(id);
        rent.ifPresent(value -> rentRepository.delete(value));
        return "removed";
    }


    public List<Rent> getRentals() {
        return rentRepository.findAllByStatus("available");
    }

    public List<Rent> findMatching(String match) {
        return rentRepository.findMatching(match);
    }
}

