package com.geektrust.backend.service;

public interface ITankerSlabService { 

    public static final Integer waterAllocatedPerPerson = 10; 
    public static final Integer totalNoOfBillableDays = 30;   


    public Double calculateTankerBill();
}
