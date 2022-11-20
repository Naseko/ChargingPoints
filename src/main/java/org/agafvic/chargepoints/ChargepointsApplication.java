package org.agafvic.chargepoints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.agafvic.chargepoints.repository")
public class ChargepointsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChargepointsApplication.class, args);
    }
}

