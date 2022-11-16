package com.geektrust.backend.commands;

import java.util.List;

import com.geektrust.backend.service.IGeekHeightsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddGuests implements ICommand{

   
       
        private final IGeekHeightsService geekHeightsService; 

        @Override
        public boolean execute(List<String> tokens) { 
            
            Integer noOfPeople = Integer.parseInt(tokens.get(1));

            if(noOfPeople!=null){
                geekHeightsService.addGuests(noOfPeople);   
                return true;
            } 
            return false;
           
           
        }
        
    
    
}
