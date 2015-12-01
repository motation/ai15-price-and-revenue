package de.hawhamburg.microservices.core.revenue.jpa.repository;

import de.hawhamburg.microservices.core.revenue.jpa.domain.Revenue;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by unknown on 27.10.15.
 */
public interface RevenueRepository extends CrudRepository<Revenue,Long> {
    List<Revenue> findAll();

    Revenue findByFlightId(UUID flightId);

    //OF this is another solution
//    @Query("select p from Revenue p where p.flightId = ?1")
//    Revenue findFlights(UUID flighId);
}
