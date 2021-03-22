package com.fravega.models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Quadtree {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long Id;
    // the first boundry should be (0, 0, 180, 90)
    @OneToOne
    private Square boundary;
    @OneToMany
    private List<Node> nodes;
    private boolean divided = false;
    private int maxNodes;
    @OneToOne(fetch = FetchType.EAGER)
    private Quadtree northEast;
    @OneToOne(fetch = FetchType.EAGER)
    private Quadtree northWest;
    @OneToOne(fetch = FetchType.EAGER)
    private Quadtree southEast;
    @OneToOne(fetch = FetchType.EAGER)
    private Quadtree southWest;
   
    public Quadtree(Square boundry, int maxNodes) {
        this.boundary = boundry;
        this.maxNodes = maxNodes;
        this.nodes = new LinkedList<Node>();
    }
    
    public Square getBoundary() {
        return boundary;
    }
    
    public List<Node> getNodes() {
        return nodes;
    }

    public Node getNode(int index) {
        return nodes.get(index);
    }

    public int getMaxNodes() {
        return maxNodes;
    }

    public boolean isDivided() {
        return this.divided;
    }
    
    public Quadtree getNorthEast() {
        return northEast;
    }

    public Quadtree getNorthWest() {
        return northWest;
    }

    public Quadtree getSouthEast() {
        return southEast;
    }

    public Quadtree getSouthWest() {
        return southWest;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setDivided(boolean divided) {
        this.divided = divided;
    }

    public void setMaxNodes(int maxNodes) {
        this.maxNodes = maxNodes;
    }

    public void setNorthEast(Quadtree northEast) {
        this.northEast = northEast;
    }

    public void setNorthWest(Quadtree northWest) {
        this.northWest = northWest;
    }

    public void setSouthEast(Quadtree southEast) {
        this.southEast = southEast;
    }

    public void setSouthWest(Quadtree southWest) {
        this.southWest = southWest;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public boolean contains(Node node) {
        return this.boundary.contains(node);
    }

    public boolean isFull(){
        return this.maxNodes <= this.nodes.size();
    }
}