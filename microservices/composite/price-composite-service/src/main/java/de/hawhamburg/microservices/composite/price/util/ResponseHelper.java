package de.hawhamburg.microservices.composite.price.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import de.hawhamburg.microservices.core.price.jpa.domain.Price;
import de.hawhamburg.microservices.core.price.util.*;
import de.hawhamburg.microservices.core.price.util.WrappedList;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * Created by Ole on 07.11.2015.
 */
public class ResponseHelper {

    private ResponseHelper(){

    }

    private ObjectReader reader = null;
    private ObjectReader getReader(Class clazz) {

        if (this.reader != null) return this.reader;

        ObjectMapper mapper = new ObjectMapper();
        return this.reader = mapper.reader(clazz);
    }


    public static Price response2Price(ResponseEntity<String> response) {
        ResponseHelper responseHelper = new ResponseHelper();
        try {
            return responseHelper.getReader(Price.class).readValue(response.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
