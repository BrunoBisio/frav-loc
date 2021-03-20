package com.fravega.models;

import javax.persistence.Entity;

@Entity
public class PickupPoint extends Node {
    
    private int capacity;

    public PickupPoint (int capacity, double longitude, double latitude) {
        super(longitude, latitude);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
