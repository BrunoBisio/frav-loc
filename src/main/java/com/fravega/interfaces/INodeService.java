package com.fravega.interfaces;

import com.fravega.models.Node;

public interface INodeService {

    public Node getClosestNode(double longitude, double latitude);

    public Node getNodeByPosition(double longitude, double latitude);

}
