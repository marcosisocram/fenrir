package com.mrpavllo.rinhadebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RinhaDeBackend2023Q3Application {

    public static void main( String[] args ) {
        SpringApplication.run( RinhaDeBackend2023Q3Application.class, args );
    }

}
