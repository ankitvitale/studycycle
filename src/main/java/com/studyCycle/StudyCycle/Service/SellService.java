package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Configuration.JwtRequestFilter;
import com.studyCycle.StudyCycle.Payload.SellProductRequest;
import com.studyCycle.StudyCycle.Repository.SellRepository;
import com.studyCycle.StudyCycle.entity.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellService {

    @Autowired
    private SellRepository sellRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    public void addSellProduct(SellProductRequest sellproductRequest ) {
        //String currentUser = JwtRequestFilter.CURRENT_USER;
        sellRepository.save(new Sell(productService.addProduct(sellproductRequest.product),
                                        userService.findUser(JwtRequestFilter.CURRENT_USER),
                                        sellproductRequest.cost,
                                        sellproductRequest.type));
    }

    public void updateSellProduct(Long id, SellProductRequest sellproductRequest) {
        Optional<Sell> sell= sellRepository.findById(id);
        if(sell.isPresent()){
            Sell sellprod= sell.get();
            sellprod.setProduct(productService.updateProduct(sellprod.getProduct().getId(),sellproductRequest.product));
            sellprod.setCost(sellproductRequest.cost);
            sellprod.setType(sellproductRequest.type);
            sellRepository.save(sellprod);
        }
    }

    public String deleteSellProduct(Long id) {
        Optional<Sell> sell= sellRepository.findById(id);
        sell.ifPresent(value -> sellRepository.delete(value));
        return "removed";
    }
    public List<Sell> getNewSellProducts() {
       return sellRepository.findAllByType("New");
    }

    public List<Sell> getOldSellProducts() {
        return sellRepository.findAllByType("Used");
    }

    public List<Sell> findNewMatching(String match) {
        return sellRepository.findNewMatching(match);
    }
    public List<Sell> findOldMatching(String match) {
        return sellRepository.findOldMatching(match);
    }
}
