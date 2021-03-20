package com.fravega.models;

import javax.persistence.Entity;

@Entity
public class Subsidiary extends Node {
    
    private String address;
    private double openFrom;
    private double openTo;

    public Subsidiary(String address, double openFrom, double openTo, double longitude, double latitude) {
        super(longitude, latitude);
        this.address = address;
        this.openFrom = openFrom;
        this.openTo = openTo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(double openFrom) {
        this.openFrom = openFrom;
    }

    public double getOpenTo() {
        return openTo;
    }

    public void setOpenTo(double openTo) {
        this.openTo = openTo;
    }
}
