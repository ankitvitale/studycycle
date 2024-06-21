package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.Payload.ProductRequest;
import com.studyCycle.StudyCycle.Repository.DonationRepository;
import com.studyCycle.StudyCycle.entity.Donate;
import com.studyCycle.StudyCycle.entity.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
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
}
