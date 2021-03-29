package com.fravega.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Square {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long Id;
    private double x;
    private double y;
    private double width;
    private double height;
    
    public Square(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }

    public boolean contains(Node node) {
        return !(
            this.x + this.width / 2 < node.getLongitude() ||
            this.x - this.width / 2 > node.getLongitude() ||
            this.y + this.height / 2 < node.getLatitude() ||
            this.y - this.height / 2 > node.getLatitude()
        );
    }

    public boolean intersects(Square square) {
        return !(
            this.x + this.width/2 < square.getX() - square.getWidth() ||
            this.x - this.width/2 > square.getX() + square.getWidth() ||
            this.y + this.height/2 < square.getY() - square.getHeight() ||
            this.y - this.height/2 > square.getY() + square.getHeight()
        );
    }
    
}
