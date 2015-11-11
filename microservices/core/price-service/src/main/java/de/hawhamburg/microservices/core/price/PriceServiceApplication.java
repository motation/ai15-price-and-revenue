package de.hawhamburg.microservices.core.price;

import de.hawhamburg.microservices.core.price.jpa.repository.PriceRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by unknown on 27.10.15.
 */
//SOF TODO enable client in live system
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("de.hawhamburg.microservices.core.price")
public class PriceServiceApplication {

    @Bean
    public PriceRepository priceRepository(PriceRepository priceRepository){
        return priceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PriceServiceApplication.class,args);
    }
}
