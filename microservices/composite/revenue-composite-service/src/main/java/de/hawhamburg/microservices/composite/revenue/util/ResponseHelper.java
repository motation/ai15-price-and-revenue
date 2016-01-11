package de.hawhamburg.microservices.composite.revenue.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import de.hawhamburg.microservices.composite.revenue.model.CalculatedPrice;
import de.hawhamburg.microservices.composite.revenue.model.Flight;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ole on 07.11.2015.
 */
public class ResponseHelper {

    public ResponseHelper(){

    }

    private ObjectReader reader = null;
    private ObjectReader getReader(Class clazz) {

        if (this.reader != null) return this.reader;

        ObjectMapper mapper = new ObjectMapper();
        return this.reader = mapper.reader(clazz);
    }


    public static CalculatedPrice response2Revenue(ResponseEntity<String> response) {
        ResponseHelper responseHelper = new ResponseHelper();
        try {
            return responseHelper.getReader(CalculatedPrice.class).readValue(response.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Flight> convert(ResponseEntity<Flight[]> arr){

        List<Flight> test = new ArrayList<>();
        for(int i=0;i<arr.getBody().length;i++){
            test.add(arr.getBody()[i]);
        }
        return test;
    }
}
