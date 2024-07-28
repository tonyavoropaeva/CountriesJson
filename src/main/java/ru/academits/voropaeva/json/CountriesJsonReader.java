package ru.academits.voropaeva.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CountriesJsonReader {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        File countriesJsonFile = new File("src/main/resources/countries.json");

        // При помощи jackson-databind загрузите JSON из файла в
        // программу, получите список объектов стран +
        List<Country> countries = List.of(objectMapper.readValue(countriesJsonFile, Country[].class));
        System.out.println(countries);

        // Посчитайте суммарную численность по этим странам  +
        int totalPopulation = 0;

        for (Country country : countries) {
            totalPopulation += country.getPopulation();
        }

        System.out.println("Суммарная численность населения по всем странам списка: " + totalPopulation);

        // Получите перечень всех валют из файла +
        List<List<Currencies>> currencies = new ArrayList<>();

        for (Country country : countries) {
            List<Currencies> currency = country.getCurrencies();
            currencies.add(currency);
        }

        System.out.println("Список валют: " + currencies);

        // Сохраните в новый JSON файл все страны, у которых
        // численность населения не менее 1 млн
        File populationCountriesMoreOneMillion = new File("src/main/resources/populationCountriesMoreOneMillion.json");
        List<Country> result = new ArrayList<>();

        for (Country country : countries) {
            if (country.getPopulation() >= 1000000) {
                result.add(country);
            }
        }

        objectMapper.writeValue(populationCountriesMoreOneMillion, result);
    }
}
