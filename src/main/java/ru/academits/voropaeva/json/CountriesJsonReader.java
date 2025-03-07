package ru.academits.voropaeva.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

public class CountriesJsonReader {
    public static void main(String[] args) {
        try {
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
            List<Currency> currencies = countries.stream()
                    .flatMap(country -> country.getCurrencies().stream())
                    .distinct()
                    .toList();

            System.out.println("Список валют: " + currencies);

            // Сохраните в новый JSON файл все страны, у которых
            // численность населения не менее 1 млн
            File countiesWithPopulationAtLeast = new File("src/main/resources/countiesWithPopulationAtLeast.json");

            final int MINIMUM_COUNTRY_POPULATION = 1000000;

            objectMapper.writeValue(
                    countiesWithPopulationAtLeast,
                    countries.stream()
                            .filter(country -> country.getPopulation() >= MINIMUM_COUNTRY_POPULATION)
                            .toList()
            );
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода данных: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}