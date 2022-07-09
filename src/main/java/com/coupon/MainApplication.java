package com.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication()
@EnableSwagger2
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public Docket swaggerConfiguration(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/*"))
                .apis(RequestHandlerSelectors.basePackage("com.coupon"))
                .build()
                .apiInfo(apiDetails());

    }

    private ApiInfo apiDetails()
    {
        return new ApiInfo(
                "Coupon APIs",
                "APIs for Coupon Management",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Harish Chandra","nourl.in","harish1234chandra21@gmail.com"),
                "Demo API License",
                "demolicenseurl.com",
                Collections.emptyList());
    }

}

