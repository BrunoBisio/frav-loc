package com.fravega.services;

import com.fravega.interfaces.IQuadtreeService;
import com.fravega.models.Node;
import com.fravega.models.Quadtree;
import com.fravega.models.Square;
import com.fravega.repositories.QuadtreeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuadtreeService implements IQuadtreeService {
    
    @Autowired
    private QuadtreeRepository quadtreeRepository;

    public void addNodeToGrid(Node node) throws Exception {
        Quadtree grid = this.getRootOrInit();
        if (this.addNode(grid, node)) {
            quadtreeRepository.save(grid);
        } else {
            throw new Exception("No se pudo almacenar el nodo ingresado");
        }
    }

    public Quadtree getRootOrInit() {
        Quadtree quadtree = quadtreeRepository.findFirstByOrderByIdAsc();
        if (quadtree == null) {
            // The root will have the max for latitude and longitude
            Square boundry = new Square(0, 0, 180, 90);
            quadtree = quadtreeRepository.save(new Quadtree(boundry, 4));
        }
        return quadtree;
    }

    private boolean addNode(Quadtree quadtree, Node node) {
        // if the node is does not belong to the boundry return false
        if (!quadtree.contains(node))
            return false;

        if (!quadtree.isDivided() && !quadtree.isFull()) {
            quadtree.addNode(node);
        } else {
            if (!quadtree.isDivided())
                quadtree = subDivide(quadtree);
            
            Quadtree northEast = quadtree.getNorthEast();
            if (northEast.contains(node)) {
                this.addNode(northEast, node);
                northEast = quadtreeRepository.save(northEast);
                return true;
            }
            Quadtree northWest = quadtree.getNorthWest();
            if (northWest.contains(node)) {
                this.addNode(northWest, node);
                northWest = quadtreeRepository.save(northWest);
                return true;
            }
            Quadtree southEast = quadtree.getSouthEast();
            if (southEast.contains(node)) {
                this.addNode(southEast, node);
                southEast = quadtreeRepository.save(southEast);
                return true;
            }
            Quadtree southWest = quadtree.getSouthWest();
            if (southWest.contains(node)) {
                this.addNode(southWest, node);
                southWest = quadtreeRepository.save(southWest);
                return true;
            }
        }
        return false;
    }

    private Quadtree subDivide(Quadtree quadtree) {
        Square boundary = quadtree.getBoundary();
        // init sub quadtrees
        // north east
        Square ne = new Square(
            boundary.getX() + boundary.getWidth() / 2, 
            boundary.getY() + boundary.getHeight() / 2, 
            boundary.getWidth() / 2,
            boundary.getHeight() / 2);
        
        // north west
        Square nw = new Square(
            boundary.getX() - boundary.getWidth() / 2, 
            boundary.getY() + boundary.getHeight() / 2, 
            boundary.getWidth() / 2,
            boundary.getHeight() / 2);
        
        // south east
        Square se = new Square(
            boundary.getX() + boundary.getWidth() / 2,
            boundary.getY() - boundary.getHeight() / 2, 
            boundary.getWidth() / 2,
            boundary.getHeight() / 2);
        
        // south west
        Square sw = new Square(
            boundary.getX() - boundary.getWidth() / 2,
            boundary.getY() - boundary.getHeight() / 2,
            boundary.getWidth() / 2,
            boundary.getHeight() / 2);
        
        
        Quadtree northEast = new Quadtree(ne, quadtree.getMaxNodes());
        Quadtree northWest = new Quadtree(nw, quadtree.getMaxNodes());
        Quadtree southEast = new Quadtree(se, quadtree.getMaxNodes());
        Quadtree southWest = new Quadtree(sw, quadtree.getMaxNodes());
        
        quadtree.setDivided(true);

        // re insert all stored nodes
        for (Node storedNode : quadtree.getNodes()) {
            if (northEast.contains(storedNode)) {
                northEast.addNode(storedNode);
            } else if (northWest.contains(storedNode)) {
                northWest.addNode(storedNode);
            } else if (southEast.contains(storedNode)) {
                southEast.addNode(storedNode);
            } else if (southWest.contains(storedNode)) {
                southWest.addNode(storedNode);
            }
        }

        quadtree.getNodes().clear();

        quadtree.setNorthEast(quadtreeRepository.save(northEast));
        quadtree.setNorthWest(quadtreeRepository.save(northWest));
        quadtree.setSouthEast(quadtreeRepository.save(southEast));
        quadtree.setSouthWest(quadtreeRepository.save(southWest));

        quadtree = quadtreeRepository.save(quadtree);

        return quadtree;
    }
}
