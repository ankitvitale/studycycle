package com.studyCycle.StudyCycle.Service;

//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
import com.studyCycle.StudyCycle.Repository.CategoryRepository;
import com.studyCycle.StudyCycle.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import java.util.List;

@Service
public class CategoryService {

   @Autowired
    private CategoryRepository categoryRepository;



    private final S3Client s3Client;
    private final String bucketName = "studycycle";
    private final String spaceUrl = "https://studycycle.blr1.digitaloceanspaces.com/";

    public CategoryService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + StringUtils.cleanPath(file.getOriginalFilename());

        // Validate file
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        Path tempFile = Files.createTempFile("upload-", fileName);

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }

        // Upload to DigitalOcean Spaces
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .acl("public-read")
                .build();

        s3Client.putObject(putObjectRequest, tempFile);

        // Clean up temporary file
        Files.deleteIfExists(tempFile);

        // Return the file URL
        return spaceUrl + fileName;
    }

//@Autowired
//    private AmazonS3 amazonS3;
//
//    @Value("${aws.s3.bucketName}")
//    private String bucketName;
//
//    @Value("${region}")
//    private String region;

//    public String uploadFile(MultipartFile file) throws IOException {
//        String key = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentLength(file.getSize());
//        amazonS3.putObject(new PutObjectRequest(bucketName, key, file.getInputStream(), metadata));
//
//        return "https://" + bucketName + ".s3.amazonaws.com/" + key;
//    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();

    }

    public List<Category> getCategory(){
        return categoryRepository.findAll();
    }


}
