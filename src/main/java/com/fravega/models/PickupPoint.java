package com.fravega.models;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("1")
public class PickupPoint extends Node {
    
    private int capacity;

    public PickupPoint (int capacity, long longitude, long latitude) {
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
