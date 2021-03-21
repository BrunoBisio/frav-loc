package com.fravega.services;

import java.util.LinkedList;
import java.util.List;

import com.fravega.interfaces.INodeService;
import com.fravega.models.Node;
import com.fravega.models.Quadtree;
import com.fravega.models.Square;
import com.fravega.repositories.QuadtreeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeService implements INodeService {
    
    // esto deberia correr cuando levanta la aplicacion
    private Square boundry = new Square(0, 0, 180, 90);
    private Quadtree quadTree = new Quadtree(boundry, 4);
    
    @Autowired
    private QuadtreeRepository quadtreeRepository;

    public void updateGridForAdd(Node node) {
        Quadtree grid = quadtreeRepository.findFirstByOrderByIdAsc();
        grid.addNode(node);
    }

    public Node pickClosest(long latitude, long longitude) throws Exception {
        List<Node> nearbyNodes = new LinkedList<Node>();
        List<Quadtree> grid = quadtreeRepository.findAll();
        // search until at least one node is found or the max for latitude and longitude analized has been reached
        for (int i = 5; nearbyNodes.size() == 0 || (latitude+i >= 180 && longitude+i >= 90); i+=5) {
            Square searchBoundry = new Square(latitude, longitude, i, i);
            nearbyNodes = this.findNearbyNodes(searchBoundry, grid.get(0));
        }
        
        if (nearbyNodes.size() == 0)
            return null;
        // if more than one node is found, compare their distances
        if (nearbyNodes.size() > 1) {
            double minDistance = Long.MAX_VALUE;
            double currentDistance = 0;
            Node closestNode = nearbyNodes.get(0);
            for (Node node : nearbyNodes) {
                currentDistance = node.getDistanceToPoint(latitude, longitude);
                if (currentDistance < minDistance) {
                    closestNode = node;
                    minDistance = currentDistance;
                }
            }
            return closestNode;
        }
        
        return nearbyNodes.get(0);
    }

    private List<Node> findNearbyNodes(Square boundry, Quadtree quadtree) {
        List<Node> nodes = new LinkedList<Node>();
        if (boundry.intersects(quadtree.getBoundary())) {
            if (!quadtree.isDivided()) {
                for (Node node : quadtree.getNodes()) {
                    if (boundry.contains(node)) {
                        nodes.add(node);
                    }
                }
            } else {
                nodes.addAll(findNearbyNodes(boundry, quadtree.getNorthEast()));
                nodes.addAll(findNearbyNodes(boundry, quadtree.getNorthWest()));
                nodes.addAll(findNearbyNodes(boundry, quadtree.getSouthEast()));
                nodes.addAll(findNearbyNodes(boundry, quadtree.getSouthWest()));
            }
        }
        return nodes;
    }

}
