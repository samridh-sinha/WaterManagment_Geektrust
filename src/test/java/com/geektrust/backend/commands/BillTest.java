package com.geektrust.backend.commands;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.backend.service.IGeekHeightsService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BillTest {
    

    @Mock 
    IGeekHeightsService geekHeightsServiceMock;    

    @InjectMocks 
    private Bill bill;  

    @Test 
    @DisplayName("Calculates the final bill") 
    public void billTest(){
        
       final List<String> expectedList = new ArrayList<String>(){
           {
               add("2400"); 
               add("5215");
           }
       }; 

       when(geekHeightsServiceMock.bill()).thenReturn(expectedList); 
       boolean result = bill.execute(List.of("BILL")); 
       Assertions.assertTrue(result); 
    }
}
