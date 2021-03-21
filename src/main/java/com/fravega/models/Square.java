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
    private long x;
    private long y;
    private long width;
    private long height;
    
    public Square(long x, long y, long width, long height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }
    
    public long getWidth() {
        return width;
    }
    
    public long getHeight() {
        return height;
    }

    public boolean contains(Node node) {
        return (
            this.x + this.width / 2 < node.getLongitude() ||
            this.x - this.width / 2 > node.getLongitude() ||
            this.y + this.height / 2 < node.getLatitude() ||
            this.y - this.height / 2 > node.getLatitude()
        );
    }

    public boolean intersects(Square square) {
        return !(
            this.x + this.width < square.getX() - square.getWidth() ||
            this.x - this.width > square.getX() + square.getWidth() ||
            this.y + this.height < square.getY() - square.getHeight() ||
            this.y - this.height > square.getY() + square.getHeight()
        );
    }
    
}
