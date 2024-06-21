package com.studyCycle.StudyCycle.Payload;

import com.studyCycle.StudyCycle.entity.Category;
import org.springframework.web.multipart.MultipartFile;


public class ProductRequest {
    public String prod_name;
    public String description;
    public MultipartFile prod_image;

    public String category;

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getProd_image() {
        return prod_image;
    }

    public void setProd_image(MultipartFile prod_image) {
        this.prod_image = prod_image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
