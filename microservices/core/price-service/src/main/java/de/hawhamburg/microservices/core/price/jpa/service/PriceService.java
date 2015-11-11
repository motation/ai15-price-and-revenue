package de.hawhamburg.microservices.core.price.jpa.service;

import de.hawhamburg.microservices.core.price.jpa.domain.Price;

import java.util.List;
import java.util.UUID;

/**
 * Created by unknown on 27.10.15.
 */
public interface PriceService {
    Price priceForFlight(UUID flightId);

    List<Price> findAllPrices();

    Price createPrice(Price price);

    void removePrice(UUID flightId);

    void updatePrice(Price price);

}
