package com.geektrust.backend.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.backend.entities.GeekHeights;
import com.geektrust.backend.exceptions.BillGenerationException;
import com.geektrust.backend.exceptions.CannotAllotWaterException;
import com.geektrust.backend.repository.IGeekHeightsRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GeekHeightsServiceTest { 

    @Mock 
    IGeekHeightsRepository geekHeightsRepositoryMock; 

    @Mock 
    ITankerSlabService tankerSlabServiceMock;  

    @InjectMocks 
    private GeekHeightsService geekHeightsService;   

    @Test 
    @DisplayName("Should throw CannotAllotWaterException") 
    public void allotWaerTestException(){
        
        final List<GeekHeights> list = new ArrayList<GeekHeights>(){
            {
                add(new GeekHeights("1", 2, 3, 7)); 
                add(new GeekHeights("2", 3, 4, 5));
            }
        };  

        when(geekHeightsRepositoryMock.findAll()).thenReturn(list); 
        CannotAllotWaterException exception = Assertions.assertThrows(CannotAllotWaterException.class, () -> {
            geekHeightsService.allotWater(2, 3, 7);
        }, "Water can only be alloted once per month"); 

        Assertions.assertEquals("Water can only be alloted once per month",exception.getMessage());

        
    }
  
    @Test 
    @DisplayName("Should allot water once per month") 
    public void allotWaterTest(){
        GeekHeightsService.allotCount=0;
        final List<GeekHeights> list1 = new ArrayList<GeekHeights>();
        
        when(geekHeightsRepositoryMock.findAll()).thenReturn(list1);
        //verify(geekHeightsRepositoryMock,times(0)).findAll();
        GeekHeights geekHeightsExpected = new GeekHeights("1", 2, 3, 7);
        when(geekHeightsRepositoryMock.save(any(GeekHeights.class))).thenReturn(geekHeightsExpected); 
        GeekHeights geekHeightsActual = geekHeightsService.allotWater(2, 3, 7); 
        Assertions.assertEquals(geekHeightsExpected, geekHeightsActual);
      

    }   


    @Test 
    @DisplayName("Calculates total water consumed") 
    public void totalWaterConsumedTest(){

       
        GeekHeights geekHeights = new GeekHeights("2", 2, 3, 7);  
        List<GeekHeights> newList  = new ArrayList<>(); 
        newList.add(geekHeights); 
        GeekHeightsService.extraGuests=5;
        when(geekHeightsRepositoryMock.findAll()).thenReturn(newList);
        Integer waterAllocatedActual = geekHeightsService.totalWaterConsumed();  
        Assertions.assertEquals(2400, waterAllocatedActual);

    }   

    @Test 
    @DisplayName("Calculates Borewell bill")
    public void BorewellBillTest(){
        GeekHeights geekHeights = new GeekHeights("2", 2, 3, 7);   
        List<GeekHeights> newList  = new ArrayList<>(); 
        newList.add(geekHeights); 
        when(geekHeightsRepositoryMock.findAll()).thenReturn(newList);    
        Double borewellBill = geekHeightsService.calculateCorporationBorewellRate(7, 3, 1.5); 
        Assertions.assertEquals(945.0, borewellBill);

    } 

    @Test 
    @DisplayName("Calculates Corporation bill")
    public void CorporationBillTest(){
        GeekHeights geekHeights = new GeekHeights("2", 2, 3, 7);   
        List<GeekHeights> newList  = new ArrayList<>(); 
        newList.add(geekHeights); 
        when(geekHeightsRepositoryMock.findAll()).thenReturn(newList);    
        Double corporationBill = geekHeightsService.calculateCorporationBorewellRate(3, 7, 1.0); 
        Assertions.assertEquals(270.0, corporationBill);

    }  

    @Test 
    @DisplayName("calculates total numbers of guests") 
    public void totalGuestsTest(){
        GeekHeights geekHeights = new GeekHeights("3", 3, 3, 7);   
        List<GeekHeights> newList  = new ArrayList<>(); 
        newList.add(geekHeights);  
        when(geekHeightsRepositoryMock.findAll()).thenReturn(newList);      
        Integer numOfGuests = geekHeightsService.totalGuests(); 
        Assertions.assertEquals(5, numOfGuests);
    } 

    @Test 
    @DisplayName("calculates the final bill") 
    public void billTest(){ 

          
        final List<GeekHeights> list = new ArrayList<GeekHeights>(){
            {
                add(new GeekHeights("1", 2, 3, 7)); 
               
            }
        };  
        
        when(geekHeightsRepositoryMock.findAll()).thenReturn(list);  
        when(tankerSlabServiceMock.calculateTankerBill()).thenReturn(500.0); 
        List<String> finaBillActual = geekHeightsService.bill();
        List<String> finalBillExpected = new ArrayList<String>(){
            {
                add("2400"); 
                add("1715");
            }
        }; 

        Assertions.assertEquals(finalBillExpected, finaBillActual);


    } 

    @Test 
    @DisplayName("Should throw a BillGenerationException") 
    public void billTestException(){

        final List<GeekHeights> list = new ArrayList<GeekHeights>(){
            {
                add(new GeekHeights("1", 2, 3, 7)); 
                add(new GeekHeights("2", 3, 4, 5)); 
            }
        };    
        when(geekHeightsRepositoryMock.findAll()).thenReturn(list);   
        BillGenerationException exception = Assertions.assertThrows(BillGenerationException.class, () -> {
             geekHeightsService.bill();
         }, "Bill can only be alloted once a month"); 
 
         Assertions.assertEquals("Bill can only be alloted once a month",exception.getMessage());
    } 

    @Test 
    @DisplayName("Calculates the extra guests added") 
    public void addGuestsTest(){
        GeekHeightsService.extraGuests = 0; 
        Integer expected = 5; 
        Integer actual = geekHeightsService.addGuests(expected); 
        Assertions.assertEquals(expected, actual);
    }




   

    
}
