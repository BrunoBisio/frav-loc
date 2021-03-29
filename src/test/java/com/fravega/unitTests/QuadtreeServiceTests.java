package com.fravega.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fravega.models.Quadtree;
import com.fravega.models.Square;
import com.fravega.models.Subsidiary;
import com.fravega.repositories.QuadtreeRepository;
import com.fravega.services.QuadtreeService;
import com.fravega.utils.TestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class QuadtreeServiceTests {
    
    @InjectMocks
    private QuadtreeService service;
    @Mock
    private QuadtreeRepository repository;

    @Test
    public void addNodeToGridTest() {
        // Init
        Subsidiary sub = new Subsidiary("some address ", 8.0 , 16.0, TestUtils.getRandomLongitude(), TestUtils.getRandomLatitude());
        Quadtree testGrid = new Quadtree(new Square(0, 0, 180, 90), 4);
        
        // Mock
        Mockito
            .when(repository.findFirstByOrderByIdAsc())
            .thenReturn(testGrid);
        
        try {
            service.addNodeToGrid(sub);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        assertEquals(testGrid.getNodes().size(), 1);
    }

    @Test
    public void addNodeToGridOutOfRangeTest() {
        // Init
        Subsidiary sub = new Subsidiary("some address ", 8.0 , 16.0, TestUtils.getRandomLongitude(), TestUtils.getRandomLatitude());
        Quadtree testGrid = new Quadtree(new Square(0, 0, 5, 5), 4);

        // Mock
        Mockito
            .when(repository.findFirstByOrderByIdAsc())
            .thenReturn(testGrid);
        
        // execute
        Exception exception = assertThrows(Exception.class, () -> {
            service.addNodeToGrid(sub);
        });
    
        // Assert
        String expectedMessage = "No se pudo almacenar el nodo ingresado";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
