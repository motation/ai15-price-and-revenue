package de.hawhamburg.microservices.composite.price;

import com.netflix.hystrix.strategy.HystrixPlugins;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import se.callista.microservices.util.MDCHystrixConcurrencyStrategy;

/**
 * Created by unknown on 27.10.15.
 */
//SOF TODO enable client in live system
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({"de.hawhamburg.microservices.composite.price", "se.callista.microservices.util"})
public class PriceCompositeServiceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(PriceCompositeServiceApplication.class);

    @Value("${app.rabbitmq.host:localhost}")
    String rabbitMqHost;

    @Bean
    public ConnectionFactory connectionFactory() {
        LOG.info("Create RabbitMqCF for host: {}", rabbitMqHost);
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMqHost);
        return connectionFactory;
    }

    public static void main(String[] args) {
        LOG.info("Register MDCHystrixConcurrencyStrategy");
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new MDCHystrixConcurrencyStrategy());
        SpringApplication.run(PriceCompositeServiceApplication.class,args);
    }
}
