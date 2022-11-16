package com.geektrust.backend.commands;

import static org.mockito.ArgumentMatchers.anyList;

import java.util.ArrayList;

import com.geektrust.backend.exceptions.NoSuchCommandException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommandInvokerTest { 

    private CommandInvoker commandInvoker; 

    @Mock 
    AllotWater allotWater;  

    @Mock 
    AddGuests addGuests; 

    @Mock 
    Bill bill; 

    @BeforeEach
    void setup(){  
        commandInvoker = new CommandInvoker();
        commandInvoker.register("ALLOT_WATER", allotWater); 
        commandInvoker.register("ADD_GUESTS", addGuests); 
        commandInvoker.register("BILL", bill);
    } 

    @Test 
    @DisplayName("Should execute all commands") 
    public void commandInvokertest()
    {
        commandInvoker.executeCommand("ALLOT_WATER", anyList()); 
        commandInvoker.executeCommand("ADD_GUESTS", anyList()); 
        commandInvoker.executeCommand("BILL", anyList());
    }  

    @Test 
    @DisplayName("Should throe No sucj command exception") 
    public void commandInvokertestException(){
        Assertions.assertThrows(NoSuchCommandException.class,() -> commandInvoker.executeCommand("RANDOM-COMMAND",new ArrayList<String>()));

    } 

    
}
