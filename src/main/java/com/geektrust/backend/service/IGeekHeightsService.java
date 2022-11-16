package com.geektrust.backend.service;

import java.util.List;

import com.geektrust.backend.entities.GeekHeights;

public interface IGeekHeightsService { 

    public static final Integer waterAllocatedPerPerson = 10; 
    public static final Integer totalNoOfBillableDays = 30;  
    public static final Double corporationRate = 1.0; 
    public static final Double borewellRate = 1.5; 
    public int maxallowableCount = 1;

    public  GeekHeights allotWater(Integer apartmentType, Integer corporationRate, Integer borewellRate);
    public  Integer addGuests(Integer noOfPeople);
    public  List<String> bill();
    
}
