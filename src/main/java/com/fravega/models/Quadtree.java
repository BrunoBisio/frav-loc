package com.fravega.models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private int maxNodes;
    @OneToMany
    private List<Node> nodes;
    private boolean divided = false;
    @OneToOne
    private Quadtree northEast;
    @OneToOne
    private Quadtree northWest;
    @OneToOne
    private Quadtree southEast;
    @OneToOne
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

    public boolean addNode(Node node) {
        // if the node is does not belong to the boundry return false
        if (!boundary.contains(node))
            return false;

        if (!this.divided && nodes.size() < maxNodes) {
            nodes.add(node);
        } else {
            if (!this.divided)
                this.subDivide();
            
            if (this.northEast.addNode(node)) return true;
            if (this.northWest.addNode(node)) return true;
            if (this.southEast.addNode(node)) return true;
            if (this.southWest.addNode(node)) return true;
        }
        return false;
    }
    
    private void subDivide() {
        // init sub quadtrees
        // north east
        Square ne = new Square(
            this.boundary.getX() + this.boundary.getWidth() / 2, 
            this.boundary.getY() - this.boundary.getHeight() / 2, 
            this.boundary.getWidth() / 2,
            this.boundary.getHeight() / 2);
        
        // north west
        Square nw = new Square(
            this.boundary.getX() - this.boundary.getWidth() / 2, 
            this.boundary.getY() - this.boundary.getHeight() / 2, 
            this.boundary.getWidth() / 2,
            this.boundary.getHeight() / 2);
        
        // south east
        Square se = new Square(
            this.boundary.getX() + this.boundary.getWidth() / 2, 
            this.boundary.getY() + this.boundary.getHeight() / 2, 
            this.boundary.getWidth() / 2,
            this.boundary.getHeight() / 2);
        
        // south west
        Square sw = new Square(
            this.boundary.getX() - this.boundary.getWidth() / 2, 
            this.boundary.getY() + this.boundary.getHeight() / 2, 
            this.boundary.getWidth() / 2,
            this.boundary.getHeight() / 2);
        
        this.northEast = new Quadtree(ne, this.maxNodes);
        this.northWest = new Quadtree(nw, this.maxNodes);
        this.southWest = new Quadtree(sw, this.maxNodes);
        this.southEast = new Quadtree(se, this.maxNodes);
        this.divided = true;

        // re insert all stored nodes
        for (Node storedNode : nodes) {
            if (this.northEast.addNode(storedNode)) { continue; }
            else if (this.northWest.addNode(storedNode)) { continue; }
            else if (this.southEast.addNode(storedNode)) { continue; }
            else if (this.southWest.addNode(storedNode)) { continue; }
        }

        this.nodes.clear();
    }

    public ch.qos.logback.core.pattern.parser.Node getNodeByLocation(long latitude, long longitude) {
        return null;
    }

}
