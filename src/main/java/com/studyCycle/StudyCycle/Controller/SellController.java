package com.studyCycle.StudyCycle.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyCycle.StudyCycle.Payload.ProductRequest;
import com.studyCycle.StudyCycle.Payload.SellProductRequest;
import com.studyCycle.StudyCycle.Service.ProductService;
import com.studyCycle.StudyCycle.Service.SellService;
import com.studyCycle.StudyCycle.entity.Product;
import com.studyCycle.StudyCycle.entity.Sell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class SellController {

    @Autowired
    private SellService sellService;

    @Autowired
    private ProductService productService;


    @PostMapping("/addSellProduct")
    @PreAuthorize("hasRole('User')")

    public ResponseEntity<Product> addProduct(@RequestParam("product") String productJson,
                                              @RequestParam("cost") int cost,
                                              @RequestParam("type") String type,
                                              @RequestParam("prod_image") MultipartFile prod_image) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ProductRequest productRequest = objectMapper.readValue(productJson, ProductRequest.class);
            productRequest.setProd_image(prod_image);
         //   productRequest.setCost(cost);
         //   productRequest.setType(type);

            Product product = productService.addProduct(productRequest);

            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
//    @PostMapping("/addSellProduct")
//    public void addSellProduct(@RequestBody SellProductRequest sellproductRequest ) throws IOException {
//        sellService.addSellProduct(sellproductRequest);
//    }

    @PostMapping("/updateSellProduct/{id}")
    public void updateSellProduct(@PathVariable("id") Long id,@RequestBody SellProductRequest sellproductRequest) throws IOException {
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
