package com.geektrust.backend.appConfig;


import com.geektrust.backend.commands.AddGuests;
import com.geektrust.backend.commands.AllotWater;
import com.geektrust.backend.commands.Bill;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.repository.GeekHeightsRepository;
import com.geektrust.backend.repository.IGeekHeightsRepository;
import com.geektrust.backend.service.GeekHeightsService;
import com.geektrust.backend.service.IGeekHeightsService;
import com.geektrust.backend.service.TankerSlabService;

public class ApplicationConfig {
    
    private final IGeekHeightsRepository geekHeightsRepository = new GeekHeightsRepository();   
    private final TankerSlabService tankerSlabService = new TankerSlabService();

    private final IGeekHeightsService geekHeightsService = new GeekHeightsService(geekHeightsRepository, tankerSlabService);

    private final AllotWater allotWater = new AllotWater(geekHeightsService); 
    private final AddGuests addGuests = new AddGuests(geekHeightsService); 
    private final Bill bill = new Bill(geekHeightsService);  

    private final CommandInvoker commandInvoker = new CommandInvoker();  

    public CommandInvoker getCommandInvoker(){  
        commandInvoker.register("ALLOT_WATER", allotWater); 
        commandInvoker.register("ADD_GUESTS", addGuests); 
        commandInvoker.register("BILL", bill);
        return commandInvoker;
    }
}
