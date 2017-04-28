package com.example.dprtenjaca.carservices.com.example.dprtenjaca.model;

/**
 * Created by mrudman on 22.1.2017..
 */

public class CarService {

    private String description;
    private Double price;

    public CarService() {
    }

    public CarService(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
