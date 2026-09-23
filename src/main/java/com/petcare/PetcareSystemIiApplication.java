package com.petcare;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "PetCare API",
                version = "1.0",
                description = "API REST para gerenciamento de uma clínica veterinária",
                contact = @Contact(
                        name = "PetCare",
                        email = "suporte@petcare.com"
                )
        )
)

@SpringBootApplication
public class PetcareSystemIiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetcareSystemIiApplication.class, args);
    }
}