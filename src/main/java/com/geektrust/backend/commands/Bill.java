package com.geektrust.backend.commands;

import java.util.List;

import com.geektrust.backend.exceptions.BillGenerationException;
import com.geektrust.backend.service.IGeekHeightsService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Bill implements ICommand {

    private final IGeekHeightsService geekHeightsService; 

    @Override
    public boolean execute(List<String> tokens) {
        try {
            List<String> bill = geekHeightsService.bill();   
            String waterConsumed = bill.get(0); 
            String totalCost = bill.get(1); 
    
            System.out.println(waterConsumed+" "+totalCost); 
            return true;
    
            
        } catch (BillGenerationException e) {
            System.out.println("Bill can be generated only once per month.");
           return false;
        }
        
       
    }
    
}