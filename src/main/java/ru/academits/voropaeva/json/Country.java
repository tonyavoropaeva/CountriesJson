package ru.academits.voropaeva.json;

import java.util.List;

public class Country {
    private String name;
    private int population;
    private List<Currencies> currencies;

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public List<Currencies> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currencies> currencies) {
        this.currencies = currencies;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", currencies=" + currencies +
                '}';
    }
}
