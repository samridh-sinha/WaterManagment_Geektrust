package com.geektrust.backend.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TankerSlabServiceTest {
    private ITankerSlabService tankerSlabService; 

    @BeforeEach 
    void setup(){

        tankerSlabService = new TankerSlabService(); 
        GeekHeightsService.extraGuests = 3;
    }

    @Test 
    @DisplayName("calculates tanker cost")
    public void tankerCost(){ 

        Double cost = tankerSlabService.calculateTankerBill(); 
        Double expectedCost = 2200.0; 
        Assertions.assertEquals(expectedCost, cost);

    } 

    @AfterEach
    void decouple(){
        GeekHeightsService.extraGuests = 0;
    }
}
