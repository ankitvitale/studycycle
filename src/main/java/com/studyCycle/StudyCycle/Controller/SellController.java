package com.studyCycle.StudyCycle.Controller;

import com.studyCycle.StudyCycle.Payload.SellProductRequest;
import com.studyCycle.StudyCycle.Service.SellService;
import com.studyCycle.StudyCycle.entity.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SellController {

    @Autowired
    private SellService sellService;
    @PostMapping("/addSellProduct")
    public void addSellProduct(@RequestBody SellProductRequest sellproductRequest ) {
        sellService.addSellProduct(sellproductRequest);
    }

    @PostMapping("/updateSellProduct/{id}")
    public void updateSellProduct(@PathVariable("id") Long id,@RequestBody SellProductRequest sellproductRequest) {
        sellService.updateSellProduct(id,sellproductRequest);
    }
    @DeleteMapping("/deleteSellProduct/{id}")
    public String deleteSellProduct(@PathVariable("id") Long id) {
        return sellService.deleteSellProduct(id);
    }

    @GetMapping("/getNewSellProducts")
    public List<Sell> getNewSellProducts(){
        return  sellService.getNewSellProducts();
    }

    @GetMapping("/getOldSellProducts")
    public List<Sell> getOldSellProducts(){
        return  sellService.getOldSellProducts();
    }
}
