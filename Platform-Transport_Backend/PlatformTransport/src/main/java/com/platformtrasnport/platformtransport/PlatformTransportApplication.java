package com.platformtrasnport.platformtransport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class PlatformTransportApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformTransportApplication.class, args);
    }

}