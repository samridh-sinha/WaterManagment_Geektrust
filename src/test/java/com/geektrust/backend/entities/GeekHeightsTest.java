package com.geektrust.backend.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeekHeightsTest {
    

    @Test 
    @DisplayName("Apartment type should return correct number of tenants")
    public void checkForTenantsTwpBedroom(){ 

         GeekHeights geekHeights1 = new GeekHeights("1", 2, 3, 7); 
         
        //For 2 bedroom number of tennats should be 3
        Assertions.assertEquals(geekHeights1.getTenants(),3);   
       


    }  

    @Test 
    @DisplayName("Apartment type should return correct number of tenants")
    public void checkForTenantsThreeBedroom(){  
         //For 3 bedroom number of tenants should be 5 
        
        GeekHeights geekHeights2 = new GeekHeights("2",3,2,1);  
        Assertions.assertEquals(geekHeights2.getTenants(), 5);
    } 

    @Test 
    @DisplayName("Borewell ratio  check") 
    public void borewellRatioCheck(){

        GeekHeights geekHeights = new GeekHeights("3", 3, 4, 7); 
        Integer borewellRatioExpected = 7; 
        Assertions.assertEquals(geekHeights.getBoreWellratio(), borewellRatioExpected); 
    } 

    @Test 
    @DisplayName("Corporation ratio  check") 
    public void corporationRatioCheck(){

        GeekHeights geekHeights = new GeekHeights("4", 3, 4, 7); 
        Integer corporationRatioExpected = 4; 
        Assertions.assertEquals(geekHeights.getCorporationRatio(),  corporationRatioExpected); 
    } 

    @Test 
    @DisplayName("Should return the ID of the object") 
    public void objectIdTest(){ 

        String expected = "5";
        GeekHeights geekHeights = new GeekHeights("5", 3, 4, 7);   
        Assertions.assertEquals(expected, geekHeights.getId());

    } 

    



}
