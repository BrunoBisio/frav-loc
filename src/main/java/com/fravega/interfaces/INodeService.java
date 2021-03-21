package com.fravega.interfaces;

import com.fravega.models.Node;

public interface INodeService {

    public Node pickClosest(long latitude, long longitude) throws Exception;

    public void updateGridForAdd(Node node);
    
}
