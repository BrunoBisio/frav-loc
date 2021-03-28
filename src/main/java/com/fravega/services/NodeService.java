package com.fravega.services;

import java.util.LinkedList;
import java.util.List;

import com.fravega.interfaces.INodeService;
import com.fravega.interfaces.IQuadtreeService;
import com.fravega.models.Node;
import com.fravega.models.Quadtree;
import com.fravega.models.Square;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodeService implements INodeService {
    
    @Autowired
    private IQuadtreeService quadtreeService;

    public Node getClosestNode(double longitude, double latitude) {
        List<Node> nearbyNodes = new LinkedList<Node>();
        Quadtree grid = quadtreeService.getRootOrInit();
        // search until at least one node is found or the max for latitude and longitude analized has been reached
        for (int i = 5; nearbyNodes.size() == 0 || (longitude+i >= 90 && latitude+i >= 180); i+=5) {
            Square searchBoundry = new Square(longitude, latitude, i, i);
            nearbyNodes = this.findNearbyNodes(searchBoundry, grid);
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

    public Node getNodeByPosition(double latitude, double longitude) {
        Quadtree grid = quadtreeService.getRootOrInit();
        Node node = this.findNearbyNodes(new Square(latitude, longitude, 0, 0), grid).get(0);
        return node;
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
