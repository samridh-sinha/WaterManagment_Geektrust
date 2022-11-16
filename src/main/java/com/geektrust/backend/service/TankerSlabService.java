package com.geektrust.backend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public class TankerSlabService implements ITankerSlabService{
    
    public static final Integer waterAllocatedPerPerson = 10; 
    public static final Integer totalNoOfBillableDays = 30;   
   
    private Map<Integer,Double> tankerSlabs;   

    public TankerSlabService(){
        this.tankerSlabs = new LinkedHashMap<>();
    }    

    private Map<Integer,Double> updateMap(){
        
        tankerSlabs.put(500,  2.0); 
        tankerSlabs.put(1500, 3.0); 
        tankerSlabs.put(3000, 5.0); 
        tankerSlabs.put(3001, 8.0);
        return tankerSlabs;

    }  

    @Override    
    public Double calculateTankerBill(){

        Map<Integer,Double> tankerSlabs = updateMap();
        List<Integer> slabs = tankerSlabs.keySet().stream().collect(Collectors.toList());    
        int totalConsumption = GeekHeightsService.extraGuests * waterAllocatedPerPerson * totalNoOfBillableDays;
        Double tankerBill = 0.0;
        int prevSlab = 0;

        for (int tankerSlab : slabs) {
             int slabDiff = tankerSlab - prevSlab;
            double slabRate = tankerSlabs.get(tankerSlab);

            if (totalConsumption > slabDiff) {
                totalConsumption-= slabDiff;
                tankerBill+= slabDiff*slabRate;
            } else {
                tankerBill+= totalConsumption*slabRate;
                totalConsumption = 0;
            }
            prevSlab = tankerSlab;
        }
        if (totalConsumption > 0) {
            tankerBill+= totalConsumption*tankerSlabs.get(3001);
            totalConsumption = 0;
        }
        return tankerBill; 
    }



}
