package de.hawhamburg.microservices.core.revenue;

import de.hawhamburg.microservices.core.revenue.jpa.repository.RevenueRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

/**
 * Created by unknown on 27.10.15.
 */
//SOF TODO enable client in live system
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("de.hawhamburg.microservices.core.revenue")
public class RevenueServiceApplication {

    @Bean
    public RevenueRepository revenueRepository(RevenueRepository priceRepository){
        return priceRepository;
    }

    public static void main(String[] args) {
        setupTimeZone();
        SpringApplication.run(RevenueServiceApplication.class,args);
    }

    private static void setupTimeZone(){
        System.setProperty("user.timezone", "CET");
        TimeZone.setDefault(TimeZone.getTimeZone("CET"));
    }
}
