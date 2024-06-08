package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Payload.ProductRequest;
import com.studyCycle.StudyCycle.Repository.CategoryRepository;
import com.studyCycle.StudyCycle.Repository.ProductRepository;
import com.studyCycle.StudyCycle.entity.Category;
import com.studyCycle.StudyCycle.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    public Product addProduct(ProductRequest productRequest){
        return productRepository.save(new Product(productRequest.prod_name,productRequest.description,productRequest.prod_image,categoryRepository.findByCategoryName(productRequest.category)));
    }
    public Product updateProduct(Long id,ProductRequest productRequest){
        Optional<Product> product= productRepository.findById(id);
        if(product.isPresent()){
           Product prod=product.get();
       prod.setProd_name(productRequest.prod_name);
       prod.setDescription(productRequest.description);
       prod.setProd_image(productRequest.prod_image);
       prod.setCategory(categoryRepository.findByCategoryName(productRequest.category));
            return productRepository.save(prod);
        }
       return null;
    }
}
