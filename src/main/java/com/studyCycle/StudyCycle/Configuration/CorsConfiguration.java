// package com.studyCycle.StudyCycle.Configuration;

// import org.springframework.context.annotation.Bean;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// public class CorsConfiguration {
//     private static final String GET = "GET";
//     private static final String POST = "POST";
//     private static final String PUT = "PUT";
//     private static final String DELETE = "DELETE";

//     @Bean
//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                 .allowedOrigins("*")  // Allow all origins
//                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                 .allowedHeaders("*")
//                 .allowCredentials(true);
//             }
//         };
//     }
// }
