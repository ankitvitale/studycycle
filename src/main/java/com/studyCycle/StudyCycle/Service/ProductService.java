package com.studyCycle.StudyCycle.Service;


import com.studyCycle.StudyCycle.Payload.ProductRequest;
import com.studyCycle.StudyCycle.Repository.CategoryRepository;
import com.studyCycle.StudyCycle.Repository.ProductImageRepository;
import com.studyCycle.StudyCycle.Repository.ProductRepository;
import com.studyCycle.StudyCycle.entity.Category;
import com.studyCycle.StudyCycle.entity.Product;
import com.studyCycle.StudyCycle.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
   private  CategoryService categoryService;
    public Product addProduct(ProductRequest productRequest)throws IOException {
        Product savedProduct =productRepository.save(new Product(productRequest.prod_name,productRequest.description,categoryRepository.findByCategoryName(productRequest.category)));
        List<ProductImage> images = new ArrayList<>();

        for(MultipartFile m: productRequest.prod_image){
            String prod_image = categoryService.uploadFile(m);
            ProductImage image = new ProductImage(prod_image, savedProduct);
            images.add(image);
        }
        productImageRepository.saveAll(images);
        savedProduct.setImages(images);
        return productRepository.save(savedProduct);
    }
    public Product updateProduct(Long id,ProductRequest productRequest) throws IOException {
        Optional<Product> product= productRepository.findById(id);
        List<ProductImage> images = new ArrayList<>();


        if(product.isPresent()){
           Product prod=product.get();
            for(MultipartFile m: productRequest.prod_image){
                String prod_image = categoryService.uploadFile(m);
                ProductImage image = new ProductImage(prod_image, prod);
                images.add(image);
            }
            //delete first
            productImageRepository.saveAll(images);
            prod.setImages(images);
       prod.setProd_name(productRequest.prod_name);
       prod.setDescription(productRequest.description);
       prod.setImages( images);
       prod.setCategory(categoryRepository.findByCategoryName(productRequest.category));
            return productRepository.save(prod);
        }
       return null;
    }
}
