package de.hawhamburg.microservices.core.revenue.jpa.service;

import de.hawhamburg.microservices.core.revenue.jpa.domain.Revenue;

import java.util.List;
import java.util.UUID;

/**
 * Created by unknown on 27.10.15.
 */
public interface RevenueService {
    Revenue revenueForFlight(UUID flightId);

    List<Revenue> findAllRevenues();

    Revenue createRevenue(Revenue price);

    boolean removeRevenue(UUID flightId);

    boolean updateRevenue(Revenue price);

}
