package de.hawhamburg.microservices.core.price.controller;

import de.hawhamburg.microservices.core.price.jpa.domain.Price;
import de.hawhamburg.microservices.core.price.jpa.service.PriceService;
import de.hawhamburg.microservices.core.price.util.WrappedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by unknown on 27.10.15.
 */

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RestController
public class PriceController {

    @Autowired
    private PriceService priceService;

    @RequestMapping(value = "/price/{flightId}", method = RequestMethod.GET)
    public Price getPrice(@PathVariable final UUID flightId){
        return priceService.priceForFlight(flightId);
    }

    @RequestMapping(value = "/prices", method = RequestMethod.GET)
    public WrappedList findAllPrices(){
        return WrappedList.getWrappedList(Price.class, priceService.findAllPrices());
    }

    @RequestMapping(value = "/price", method = RequestMethod.POST)
    public Price createPrice(@RequestBody final Price price, HttpServletResponse response) throws IOException {
        Price savedPriceItem = priceService.createPrice(price);
        // OF maybe build Error Entity!
        if(savedPriceItem != null){
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
        return savedPriceItem;
    }
    @RequestMapping(value="/price/{flightId}", method = RequestMethod.DELETE)
    public void deletePrice(@PathVariable final UUID flightId, HttpServletResponse response) throws IOException {
        try {
            priceService.removePrice(flightId);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @RequestMapping(value="/price/{flightId}", method = RequestMethod.PUT)
    public void deletePrice(@RequestBody final Price price, HttpServletResponse response) throws IOException {
        try {
            priceService.updatePrice(price);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }



    public static void main(String[] args) throws IOException {
        String path ="http://localhost:8080/price";
        HttpURLConnection con = (HttpURLConnection) new URL(path).openConnection();
        con.setRequestMethod("DELETE");
//        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","application/json");
        con.setDoInput(true);
        con.setDoOutput(true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
        String uuid = "95fbe489-9217-469f-8771-cf15ee1051b5";
//        UUID uuid = UUID.randomUUID();
        String json = "{\"value\":260.0,\"flightId\":\""+uuid+"\"}";
        System.out.println(json);
        writer.write(json);
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = null;

        while((line=reader.readLine() ) != null){
            System.out.println(line);
        }
        reader.close();
        writer.close();
        con.disconnect();
    }
}
