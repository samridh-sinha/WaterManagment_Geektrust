package com.geektrust.backend.commands;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.geektrust.backend.entities.GeekHeights;
import com.geektrust.backend.service.IGeekHeightsService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AllotWaterTest { 

   @Mock 
   IGeekHeightsService geekHeightsServiceMock;  

   @InjectMocks 
   private AllotWater allotWater;  

   
   @Test 
   @DisplayName("Allots water once per month")
   public void allotWaterTest(){ 

       GeekHeights geekHeights = new GeekHeights("1", 2, 3, 7); 
       //when(geekHeightsServiceMock.allotWater(anyInt(),anyInt(),anyInt())).thenReturn(geekHeights);

       boolean result = allotWater.execute(List.of("ALLOT_WATER", "2", "3:7"));
       verify(geekHeightsServiceMock).allotWater(2,3,7);
       Assertions.assertTrue(result);

   } 

  
    
}
