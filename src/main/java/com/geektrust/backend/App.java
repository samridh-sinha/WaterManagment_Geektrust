package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.geektrust.backend.appConfig.ApplicationConfig;
import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.exceptions.NoSuchCommandException;

public class App {

	public static void main(String[] args) {
	
        List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args)); 
        try {
            
            run(commandLineArgs);
        } catch (Exception e) {
            
            System.out.println(e.getMessage());
        }
		
		
	} 

	public static void run(List<String> commandLineArgs) { 
		ApplicationConfig applicationConfig = new ApplicationConfig();
        CommandInvoker commandInvoker = applicationConfig.getCommandInvoker();
        BufferedReader reader;
		String inputFile = commandLineArgs.get(0);
        try{
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                List<String> tokens = Arrays.asList(line.split(" "));
                commandInvoker.executeCommand(tokens.get(0),tokens);
				// read next line 
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException | NoSuchCommandException e) {
           return ;
        }
	}

}
