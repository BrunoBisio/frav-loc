package com.fravega.fravega;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.fravega.models.Node;
import com.fravega.models.PickupPoint;
import com.fravega.models.Subsidiary;
import com.fravega.repositories.NodeRepository;
import com.fravega.repositories.PickupPointRepository;
import com.fravega.repositories.QuadtreeRepository;
import com.fravega.repositories.SubsidiaryRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class NodeIntegrationTest {

	@Autowired
	private NodeRepository<Node> nodeRepository;

	@Test
	public void GetClosestNodeTest() {
		Random r = new Random();
		List<Node> nodes = new LinkedList<Node>();
		// create nodes
		for (int i = 0; i < 10; i++) {
			nodes.add(new Subsidiary("some address " + i, 8.0 , 16.0, getRandomLongitude(), getRandomLatitude()));
		}
		for (int i = 0; i < 10; i++) {
			nodes.add(new PickupPoint(5 + i, getRandomLongitude(), getRandomLatitude()));
		}
		Subsidiary expectedNode = new Subsidiary("some address expected", 8.0, 16.0, 5, 5);
		nodes.add(expectedNode);
		nodes = nodeRepository.saveAll(nodes);
		
		// search position
		// check node retrieved
	}

	private double getRandomLongitude(){
		return ThreadLocalRandom.current().nextDouble(-180, 180);
	}

	private double getRandomLatitude(){
		return ThreadLocalRandom.current().nextDouble(-90, 90);
	}
}
