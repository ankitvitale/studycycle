package com.studyCycle.StudyCycle.Service;


import com.razorpay.Order;
import com.studyCycle.StudyCycle.Payload.RentInput;
import com.studyCycle.StudyCycle.Payload.RentProductRequest;
import com.studyCycle.StudyCycle.Payload.RentReceipt;
import com.studyCycle.StudyCycle.Repository.RentHistoryRepository;
import com.studyCycle.StudyCycle.Repository.RentRepository;
import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.entity.Rent;
import com.studyCycle.StudyCycle.entity.RentHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RentHistoryRepository rentHistoryRepository;
    public String addRentProduct(RentProductRequest rentProductRequest) throws IOException {
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

    public String updateRentProduct(Long id, RentProductRequest rentProductRequest) throws IOException {
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
public RentReceipt getReceipt(RentInput orderProd) {
    Double deposit = 0.0d, delivery = 0.0d, app_fees = 0.0d;

    Optional<Rent> rentOptional = rentRepository.findById(orderProd.rent_id);
    if (rentOptional.isPresent()) {
        Rent rentProd = rentOptional.get();
        deposit += rentProd.getDeposit_money();
        delivery += 70.0;
        app_fees += 0.10 * rentProd.getDeposit_money();

        rentProd.setStatus("rented");
        rentProd.setProduct_renting(Date.valueOf(LocalDate.now()));
        rentProd.setTenant(userService.findUser(JwtRequestFilter.CURRENT_USER));
        rentRepository.save(rentProd);

        Double rentCost = orderProd.rent_days * rentProd.getRent_per_day();
        Double returnAmt = rentProd.getDeposit_money() - rentCost;
        LocalDateTime returnDate = LocalDateTime.now().plusDays(orderProd.rent_days);

        Double total= deposit + delivery + app_fees;
        RentHistory rentHistory = new RentHistory(
                "Incomplete",
                orderProd.rent_days,
                rentCost,
                returnAmt,
                returnDate,
                returnAmt,
                total,
                rentProd,
                app_fees,
                delivery
        );
        RentHistory savedEntity = rentHistoryRepository.save(rentHistory);



        return new RentReceipt(savedEntity.getRent_order_id(), deposit, app_fees, delivery, total);
    } else {
        // Handle the case where the rent is not found
        throw new RuntimeException("Rent not found with id: " + orderProd.rent_id);
    }
}

    public RentHistory processPayment(Long id) {
        Optional<RentHistory> rentHistoryOptional = rentHistoryRepository.findById(id);
        if (rentHistoryOptional.isPresent()) {
            RentHistory rentHistory = rentHistoryOptional.get();

            try {
                Order order = paymentService.createOrder(rentHistory.getTotal());
                rentHistory.setRazorpay_orderId(order.get("id"));
                rentHistory.setStatus("Successful");
                rentHistory.setTimestamp(LocalDateTime.now());

                return rentHistoryRepository.save(rentHistory);
            } catch (Exception e) {
                rentHistory.setStatus("Failed");
                rentHistoryRepository.save(rentHistory);
                throw new RuntimeException("Payment processing failed: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Rent history not found with id: " + id);
        }
    }

}


