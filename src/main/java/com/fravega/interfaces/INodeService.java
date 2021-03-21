package com.fravega.interfaces;

import com.fravega.models.Node;

public interface INodeService {

    public Node getClosestNode(double latitude, double longitude) throws Exception;

    public void updateGridForAdd(Node node);
    
}
