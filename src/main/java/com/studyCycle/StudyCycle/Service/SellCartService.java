package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.Repository.*;
import com.studyCycle.StudyCycle.entity.*;
import com.studyCycle.StudyCycle.entity.DonateCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellCartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    RentCartRepository rentCartRepository;

    @Autowired
    DonateCartRepository donateCartRepository;
    public SellCart addToCart(Integer id){

        Product product = productRepository.findById(Long.valueOf(id)).get();
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User email=null;
        if (currentUser != null) {
            email = userRepository.findByEmail(currentUser);
        }
        if (product != null && userRepository!=null) {

            SellCart cart = new SellCart(product ,email);

            return cartRepository.save(cart);

        }
        return null;

    }



    public RentCart addToCartRent(Integer rentProdId) {
        Product product = productRepository.findById(Long.valueOf(rentProdId)).get();
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User email=null;
        if (currentUser != null) {
            email = userRepository.findByEmail(currentUser);
        }
        if (product != null && userRepository!=null) {

            RentCart rentcart = new RentCart(product ,email);

            return rentCartRepository.save(rentcart);

        }
        return null;
    }

    public DonateCart addToDonateCart(Integer donationProdId) {
        Product product = productRepository.findById(Long.valueOf(donationProdId)).get();
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User email=null;
        if (currentUser != null) {
            email = userRepository.findByEmail(currentUser);
        }
        if (product != null && userRepository!=null) {

            DonateCart donatecart = new DonateCart(product ,email);

            return donateCartRepository.save(donatecart);

        }
        return null;
    }

}

