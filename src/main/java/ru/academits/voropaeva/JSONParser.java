package ru.academits.voropaeva;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParser {
    ObjectMapper objectMapper = new ObjectMapper();
    countries countries = objectMapper.readValue(
            new File("src/main/resources/countries.json"),
            countries.class);


}
