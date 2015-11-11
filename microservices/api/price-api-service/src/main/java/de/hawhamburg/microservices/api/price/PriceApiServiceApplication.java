package de.hawhamburg.microservices.api.price;

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
import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import se.callista.microservices.util.MDCHystrixConcurrencyStrategy;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Ole on 08.11.2015.
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableOAuth2Resource
@SpringBootApplication
@ComponentScan({"de.hawhamburg.microservices.api.price","se.callista.microservices.util"})
public class PriceApiServiceApplication {


    private static final Logger LOG = LoggerFactory.getLogger(PriceApiServiceApplication.class);

    static {
        // for localhost testing only
        LOG.warn("Will now disable hostname check in SSL, only to be used during development");
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, sslSession) -> true);
    }

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
        SpringApplication.run(PriceApiServiceApplication.class,args);
    }
}

