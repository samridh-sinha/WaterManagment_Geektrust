package com.geektrust.backend.commands;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
public class AddGuestsTest { 


    @Mock 
    IGeekHeightsService geekHeightsServiceMock; 

    @InjectMocks 
    private AddGuests addGuests; 

    @Test 
    @DisplayName("The command adds extra guests") 
    public void addGueststest(){ 
       
        Integer expectedGuests = 5;
        when(geekHeightsServiceMock.addGuests(anyInt())).thenReturn(expectedGuests);  
        boolean result = addGuests.execute(List.of("ADD_GUESTS", "5"));  
        Assertions.assertTrue(result);

        
        
    } 

   
    
}
