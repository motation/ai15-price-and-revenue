package de.hawhamburg.microservices.core.revenue.service;

import de.hawhamburg.microservices.core.revenue.controller.RevenueController;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by unknown on 27.10.15.
 */

// Integrationtest

@Test
public class PriceControllerTest {
    private RevenueController revenueController;

    @BeforeTest
    public void init(){
        this.revenueController = new RevenueController();
    }

    public void justATest(){
//        assertEquals("priceRevenueService", revenueController.priceRevenueService());
    }

}