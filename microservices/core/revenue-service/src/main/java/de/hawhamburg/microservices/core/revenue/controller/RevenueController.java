package de.hawhamburg.microservices.core.revenue.controller;

import de.hawhamburg.microservices.core.revenue.jpa.domain.Revenue;
import de.hawhamburg.microservices.core.revenue.jpa.service.RevenueService;
import de.hawhamburg.microservices.core.revenue.util.WrappedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.io.*;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by unknown on 27.10.15.
 */

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @RequestMapping(value = "/revenue/{flightId}", method = RequestMethod.GET)
    public Revenue getRevenue(@PathVariable final UUID flightId){
        return revenueService.revenueForFlight(flightId);
    }

    @RequestMapping(value = "/revenues", method = RequestMethod.GET)
    public WrappedList findAllRevenues(){
        return WrappedList.getWrappedList(Revenue.class, revenueService.findAllRevenues());
    }

    @RequestMapping(value = "/revenue", method = RequestMethod.POST)
    public Revenue createRevenue(@RequestBody final Revenue revenue, HttpServletResponse response) throws IOException {
        Revenue savedRevenueItem = revenueService.createRevenue(revenue);
        // OF maybe build Error Entity!
        if(savedRevenueItem != null){
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return savedRevenueItem;
    }
    @RequestMapping(value="/revenues/{flightId}", method = RequestMethod.DELETE)
    public void deleteRevenue(@PathVariable final UUID flightId, HttpServletResponse response) throws IOException {
        try {
            revenueService.removeRevenue(flightId);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value="/revenue/{flightId}", method = RequestMethod.PUT)
    public void deleteRevenue(@RequestBody final Revenue revenue, HttpServletResponse response) throws IOException {
        try {
            revenueService.updateRevenue(revenue);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
