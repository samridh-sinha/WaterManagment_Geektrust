package com.geektrust.backend.commands;

import java.util.List;

import com.geektrust.backend.exceptions.CannotAllotWaterException;
import com.geektrust.backend.service.IGeekHeightsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AllotWater implements ICommand{

    private final IGeekHeightsService geekHeightsService; 


    @Override
    public boolean execute(List<String> tokens) {
        Integer apartmentType = Integer.parseInt(tokens.get(1)); 
        String[] ratios = tokens.get(2).split(":"); 
        Integer corporationRate = Integer.parseInt(ratios[0]); 
        Integer borewellRate = Integer.parseInt(ratios[1]); 

        try { 

             geekHeightsService.allotWater(apartmentType, corporationRate, borewellRate);
             return true;
            
        } catch (CannotAllotWaterException e) {
            System.out.println("Water can only be alloted once per month.");
                return false;
           
        }
       
        
        
        
    }
    
}
