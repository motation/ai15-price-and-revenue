package de.hawhamburg.microservices.core.revenue.controller;

import de.hawhamburg.microservices.core.revenue.jpa.domain.Revenue;
import de.hawhamburg.microservices.core.revenue.jpa.service.RevenueService;
import de.hawhamburg.microservices.core.revenue.util.WrappedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by unknown on 27.10.15.
 */

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
public class RevenueController {

    private static final Logger LOG = LoggerFactory.getLogger(RevenueController.class);

    @Autowired
    private RevenueService revenueService;

    @RequestMapping(value = "/revenue/{flightId}", method = RequestMethod.GET)
    public Revenue getRevenue(@PathVariable final UUID flightId) {
        return revenueService.revenueForFlight(flightId);
    }

    @RequestMapping(value = "/revenues", method = RequestMethod.GET)
    public WrappedList findAllRevenues() {
        return WrappedList.getWrappedList(Revenue.class, revenueService.findAllRevenues());
    }

    @RequestMapping(value = "/revenue", method = RequestMethod.POST)
    public Revenue createRevenue(@RequestBody final Revenue revenue, HttpServletResponse response) throws IOException {
        Revenue savedRevenueItem = revenueService.createRevenue(revenue);
        // OF maybe build Error Entity!
        if (savedRevenueItem != null) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return savedRevenueItem;
    }

    @RequestMapping(value = "/revenues/{flightId}", method = RequestMethod.DELETE)
    public void deleteRevenue(@PathVariable final UUID flightId, HttpServletResponse response) throws IOException {
        try {
            revenueService.removeRevenue(flightId);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/revenue/{flightId}", method = RequestMethod.PUT)
    public void deleteRevenue(@RequestBody final Revenue revenue, HttpServletResponse response) throws IOException {
        try {
            revenueService.updateRevenue(revenue);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/revenues/{startTime}/{endTime}", method = RequestMethod.GET)
    public List<Revenue> getRevenuesForTime(@PathVariable long startTime, @PathVariable long endTime) {
        LOG.info("startTime=" + startTime + " endTime=" + endTime);
        List<Revenue> revenues = revenueService.revenuesForTime(startTime, endTime);
        return revenues;
    }

    @RequestMapping(value = "/rev", method = RequestMethod.GET)
    public List<Revenue> getRevenueFor(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME,
            pattern = "yyyy-MM-dd HH:mm:ss") final Date fromDate,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME,
                                         pattern = "yyyy-MM-dd HH:mm:ss") final Date toDate) {
        System.out.println(fromDate.toString());
        System.out.println(toDate.toString());
        return revenueService.revenuesForTime(fromDate.getTime(),toDate.getTime());
    }

}
