package de.hawhamburg.microservices.core.revenue.jpa.service;

import de.hawhamburg.microservices.core.revenue.jpa.domain.Revenue;
import de.hawhamburg.microservices.core.revenue.jpa.repository.RevenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

/**
 * Created by unknown on 27.10.15.
 */
@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    @PostConstruct
    public void init(){
        //OF fill mysql with data if is empty :)
        if (revenueRepository.findAll().isEmpty()) {
            for(double i=2;i<20;i++){
                Revenue revenue = new Revenue.RevenueBuilder()
                        // TODO später durch Response von Reservation ersetzen
                        .withValue(12)
                        .withSoldTicketsFirstClassInternet(i+40)
                        .withSoldTicketsEconomyClassInternet(i+30)
                        .withSoldTicketsFirstClassTravelOffice(i+25)
                        .withSoldTicketsEconomyClassTravelOffice(i+47)
                        .withSoldTicketsFirstClassCounter(i+40)
                        .withSoldTicketsEconomyClassCounter(i+23)
                        .withFlightId(UUID.randomUUID())
                        .build();
                revenueRepository.save(revenue);
            }
        }

        Revenue revenueSame = new Revenue.RevenueBuilder()
                .withValue(200)
                .withSoldTicketsFirstClassInternet(50)
                .withSoldTicketsEconomyClassInternet(80)
                .withSoldTicketsFirstClassTravelOffice(90)
                .withSoldTicketsEconomyClassTravelOffice(100)
                .withSoldTicketsFirstClassCounter(500)
                .withSoldTicketsEconomyClassCounter(100)
                .withFlightId(UUID.fromString("9aacad96-6730-4443-b6f6-33325b00ce39"))
                .build();
        revenueRepository.save(revenueSame);
    }

    @Override
    public Revenue revenueForFlight(UUID flightId) {
        return revenueRepository.findByFlightId(flightId);
    }

    @Override
    public List<Revenue> findAllRevenues() {
        return revenueRepository.findAll();
    }

    @Override
    public Revenue createRevenue(Revenue revenue) {
        if(revenueForFlight(revenue.getFlightId()) == null){
            return revenueRepository.save(revenue);
        }
        return null;
    }

    @Override
    public boolean removeRevenue(UUID flightId){
        try{
            Revenue revenue = revenueRepository.findByFlightId(flightId);
            if(revenue == null) return false;
            revenueRepository.delete(revenue);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateRevenue(Revenue revenue) {
        revenueRepository.save(revenue);
        return true;
    }


}
