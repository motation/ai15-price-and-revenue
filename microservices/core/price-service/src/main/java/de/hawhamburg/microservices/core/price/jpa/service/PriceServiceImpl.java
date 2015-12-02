package de.hawhamburg.microservices.core.price.jpa.service;

import de.hawhamburg.microservices.core.price.jpa.domain.Price;
import de.hawhamburg.microservices.core.price.jpa.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
        Price priceSame = new Price.PriceBuilder()
                .withValue(2000.0)
                .withFlightId(UUID.fromString("9aacad96-6730-4443-b6f6-33325b00ce39"))
                .build();
        priceRepository.save(priceSame);
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
    public boolean removePrice(UUID flightId){
        try{
            Price price = priceRepository.findByFlightId(flightId);
            if(price == null) return false;
            priceRepository.delete(price);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updatePrice(UUID flightId, double value) {
        Price temp = priceRepository.findByFlightId(flightId);
        Price priceToUpdate = new Price.PriceBuilder()
                .withFlightId(flightId)
                .withId(temp.getId())
                .withValue(value)
                .build();
        Price savePrice = priceRepository.save(priceToUpdate);
        return ((savePrice.getValue() == value) && (savePrice.getFlightId() == flightId) && (savePrice.getId() == temp.getId()));
    }
}
