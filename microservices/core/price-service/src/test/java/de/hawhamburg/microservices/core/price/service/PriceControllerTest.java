package de.hawhamburg.microservices.core.price.service;

import de.hawhamburg.microservices.core.price.controller.PriceController;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by unknown on 27.10.15.
 */

// Integrationtest

@Test
public class PriceControllerTest {
    private PriceController priceController;

    @BeforeTest
    public void init(){
        this.priceController = new PriceController();
    }

    public void justATest(){
//        assertEquals("priceRevenueService", priceController.priceRevenueService());
    }

}