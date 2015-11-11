package de.hawhamburg.microservices.core.price.jpa.repository;

import de.hawhamburg.microservices.core.price.jpa.domain.Price;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by unknown on 27.10.15.
 */
public interface PriceRepository extends CrudRepository<Price,Long> {
    List<Price> findAll();

    Price findByFlightId(UUID flightId);

    //OF this is another solution
//    @Query("select p from Price p where p.flightId = ?1")
//    Price findFlights(UUID flighId);
}
