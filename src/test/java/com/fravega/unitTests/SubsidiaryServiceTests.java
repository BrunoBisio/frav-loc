package com.fravega.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fravega.models.Subsidiary;
import com.fravega.repositories.SubsidiaryRepository;
import com.fravega.services.NodeService;
import com.fravega.services.QuadtreeService;
import com.fravega.services.SubsidiaryService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SubsidiaryServiceTests {

    @Mock
    private SubsidiaryRepository subsidiaryRepository;
    @Mock
    private QuadtreeService quadtreeService;
    @Mock
    private NodeService nodeService;
    @InjectMocks
    private SubsidiaryService subsidiaryService;
    

    @Test
    public void createSubsidiaryTest() {
        // Prepare
        Subsidiary sub = new Subsidiary("some address ", 8.0 , 16.0, 20, 20);
        Subsidiary savedSubsidiary = null;
        
        // Mock
        Mockito
            .when(nodeService.getNodeByPosition(Mockito.anyDouble(), Mockito.anyDouble()))
            .thenReturn(null);
        Mockito
            .when(subsidiaryRepository.save(Mockito.any(Subsidiary.class)))
            .thenReturn(sub);
       
        // createEntity
        try {
            savedSubsidiary = subsidiaryService.createSubsidiary(sub);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        assertEquals(sub, savedSubsidiary);
    }

    @Test
    public void createSubsidiaryDuplicatedTest() {
        // Prepare
        Subsidiary sub = new Subsidiary("some address ", 8.0 , 16.0, 20, 20);

        // Mock
        Mockito
            .when(nodeService.getNodeByPosition(Mockito.anyDouble(), Mockito.anyDouble()))
            .thenReturn(sub);

        // execute
        Exception exception = assertThrows(Exception.class, () -> {
            subsidiaryService.createSubsidiary(sub);
        });
    
        // Assert
        String expectedMessage = "Ya se ha registrado una sucursal o punto de venta en esta ubicación";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
}
