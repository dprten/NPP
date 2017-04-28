package com.example.dprtenjaca.carservices.com.example.dprtenjaca.model;


import java.util.List;

public class CarServiceCompany {

    private String name;

    private List<CarService> carServiceList;

    public CarServiceCompany() {
    }

    public CarServiceCompany(String name, List<CarService> carServiceList) {
        this.name = name;
        this.carServiceList = carServiceList;
    }

    public CarServiceCompany(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CarService> getCarServiceList() {
        return carServiceList;
    }

    public void setCarServiceList(List<CarService> carServiceList) {
        this.carServiceList = carServiceList;
    }
}
