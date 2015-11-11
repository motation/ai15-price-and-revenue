package de.hawhamburg.microservices.core.price.jpa.service;

import de.hawhamburg.microservices.core.price.jpa.domain.Price;
import de.hawhamburg.microservices.core.price.jpa.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by unknown on 27.10.15.
 */
@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @PostConstruct
    public void init(){
        //OF fill mysql with data if is empty :)
        if (priceRepository.findAll().isEmpty()) {
            for(int i=2;i<20;i++){
                Price price = new Price.PriceBuilder()
                        .withValue(i * 50 + 10.0)
                        .withFlightId(UUID.randomUUID())
                        .build();
                priceRepository.save(price);
            }
        }
    }

    @Override
    public Price priceForFlight(UUID flightId) {
        return priceRepository.findByFlightId(flightId);
    }

    @Override
    public List<Price> findAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public Price createPrice(Price price) {
        if(priceForFlight(price.getFlightId()) == null){
            return priceRepository.save(price);
        }
        return null;
    }

    @Override
    public void removePrice(UUID flightId){
        Price price = priceRepository.findByFlightId(flightId);
        priceRepository.delete(price);
    }

    @Override
    public void updatePrice(Price price) {
        priceRepository.save(price);
    }


}
