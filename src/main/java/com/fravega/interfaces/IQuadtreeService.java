package com.fravega.interfaces;

import com.fravega.models.Node;
import com.fravega.models.Quadtree;

public interface IQuadtreeService {

    public void addNodeToGrid(Node node) throws Exception;

    public Quadtree getRootOrInit();

}
