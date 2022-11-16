package com.geektrust.backend.exceptions;

public class CannotAllotWaterException extends RuntimeException{ 

    private static final long serialVersionUID = 2592740408491304878L; 

    public CannotAllotWaterException(){
        super();
    } 

    public CannotAllotWaterException(String msg){ 
        super(msg);

    }
    
}
