package com.fravega.interfaces;

import com.fravega.models.Node;
import com.fravega.models.Quadtree;

public interface IQuadtreeService {

    public void updateGridForAdd(Node node) throws Exception;

    public Quadtree getRootOrInit();

    public boolean addNode(Quadtree quadtree, Node node);

}
