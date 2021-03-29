package com.fravega.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import com.fravega.models.Node;
import com.fravega.models.PickupPoint;
import com.fravega.models.Quadtree;
import com.fravega.models.Square;
import com.fravega.models.Subsidiary;
import com.fravega.services.NodeService;
import com.fravega.services.QuadtreeService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class NodeServiceTests {

	@InjectMocks
	private NodeService nodeService;
	@Mock
	private QuadtreeService quadtreeService;
	
	List<Node> nodes;
	Quadtree quadtree;

	@Before
	public void setUp() {
		// init
		nodes = new LinkedList<Node>();
		quadtree = new Quadtree(new Square(0, 0, 180, 90), 4);
		buildQuadtree();
		
		// Mock
		Mockito
			.when(quadtreeService.getRootOrInit())
			.thenReturn(quadtree);

		// create and add nodes
		quadtree.getNorthEast().addNode(new Subsidiary("some address 1" , 8.0 , 16.0, 10, 10));
		quadtree.getNorthEast().addNode(new PickupPoint(5, 20, 20));
		quadtree.getNorthEast().addNode(new PickupPoint(10, 4, 4));
		quadtree.getNorthWest().addNode(new PickupPoint(5, -25, 25));
		quadtree.getSouthEast().addNode(new PickupPoint(10, 160, -20));
		quadtree.getSouthWest().addNode(new Subsidiary("some address 2" , 8.0 , 16.0, -10, -30));
		quadtree.getSouthWest().addNode(new Subsidiary("some address 3" , 8.0 , 16.0, -70, -30));
	}


	private void buildQuadtree() {
		Square boundary = quadtree.getBoundary();
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
		
		quadtree.setNorthEast(new Quadtree(ne, quadtree.getMaxNodes()));
		quadtree.setNorthWest(new Quadtree(nw, quadtree.getMaxNodes()));
		quadtree.setSouthEast(new Quadtree(se, quadtree.getMaxNodes()));
		quadtree.setSouthWest(new Quadtree(sw, quadtree.getMaxNodes()));
		quadtree.setDivided(true);
	}


	@Test
	public void GetClosestNodeTest() {
		Subsidiary expectedNode = new Subsidiary("some address expected", 8.0, 16.0, 5, 5);
		quadtree.getNorthEast().addNode(expectedNode);

		// search position
		Node nodeFound = nodeService.getClosestNode(4.9999, 4.99999);
		
		assertEquals(expectedNode, nodeFound);
	}
}
