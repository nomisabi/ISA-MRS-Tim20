package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;



public class IntegrationTestUtils {
	 
    public static String convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

}
