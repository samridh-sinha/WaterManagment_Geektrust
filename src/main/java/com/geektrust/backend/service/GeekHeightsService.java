package com.geektrust.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.geektrust.backend.entities.GeekHeights;
import com.geektrust.backend.exceptions.BillGenerationException;
import com.geektrust.backend.exceptions.CannotAllotWaterException;
import com.geektrust.backend.repository.IGeekHeightsRepository;

public class GeekHeightsService implements IGeekHeightsService{

    private IGeekHeightsRepository geekHeightsRepository;  
    private ITankerSlabService tankerSlabService;
    private static Integer autoIncrement = 0;   
    protected static Integer extraGuests = 0;    
    protected static int billCount = 0; 
    protected static int allotCount =0; 
   
  
    public GeekHeightsService(IGeekHeightsRepository geekHeightsRepository, ITankerSlabService tankerSlabService) {
        this.geekHeightsRepository = geekHeightsRepository; 
        this.tankerSlabService = tankerSlabService;
    }


    @Override
    public GeekHeights allotWater(Integer apartmentType, Integer corporationRate, Integer borewellRate) { 
        allotCount+=1;
        List<GeekHeights> geekHeightsList = geekHeightsRepository.findAll(); 
        if(geekHeightsList.size()>=maxallowableCount || allotCount>maxallowableCount){
            throw new CannotAllotWaterException("Water can only be alloted once per month");
        }
        GeekHeights geekHeights = new GeekHeights(Integer.toString(GeekHeightsService.autoIncrement+1), apartmentType, corporationRate, borewellRate); 
        geekHeights = geekHeightsRepository.save(geekHeights); 
        return geekHeights; 
    }

    @Override
    public Integer addGuests(Integer noOfPeople) {
        extraGuests += noOfPeople; 
        return extraGuests;
    }

    @Override
    public List<String> bill() {
       
        billCount+=1;
        List<String> bill = new ArrayList<>();  
        List<GeekHeights> geekHeightsList = geekHeightsRepository.findAll();  
        if(billCount>maxallowableCount) 
        {
            throw new BillGenerationException("Bill can only be alloted once a month");
        }
        GeekHeights geekHeights = geekHeightsList.get(0);
        Double corporationBill = Math.ceil(calculateCorporationBorewellRate(geekHeights.getCorporationRatio(), 
                                 geekHeights.getBoreWellratio(),corporationRate));  
        Double borewellBill = Math.ceil(calculateCorporationBorewellRate(geekHeights.getBoreWellratio(), 
                                geekHeights.getCorporationRatio(),borewellRate)); 
        Double tankerBill =  Math.ceil(tankerSlabService.calculateTankerBill());
        Integer totalBill = (corporationBill.intValue()+
                            borewellBill.intValue()+
                            tankerBill.intValue()); 
        
        Integer waterConsumed = totalWaterConsumed(); 
        
        bill.add(waterConsumed.toString()); 
        bill.add(totalBill.toString());  
        geekHeightsRepository.delete(geekHeights) ;
        return bill;
    } 

    Integer totalGuests(){
        return geekHeightsRepository.findAll().get(0).getTenants()+extraGuests;
    } 

    Integer totalWaterConsumed(){
        
        return totalGuests()*waterAllocatedPerPerson*totalNoOfBillableDays;
    }  


    Double calculateCorporationBorewellRate(int r1,int r2, Double rate){
        int sumOfRatio = (r1+r2);   
        GeekHeights geekHeights = geekHeightsRepository.findAll().get(0);
        int totalWaterConsumed =  waterAllocatedPerPerson * totalNoOfBillableDays * geekHeights.getTenants();
        Double billRatio = (r1*totalWaterConsumed*rate)/sumOfRatio; 
        return billRatio;
    } 


    
}
