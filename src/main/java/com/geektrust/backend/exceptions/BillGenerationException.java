package com.geektrust.backend.exceptions;

public class BillGenerationException extends RuntimeException{

    private static final long serialVersionUID = 2592740408491304878L; 

    public BillGenerationException(){
        super();
    } 

    public BillGenerationException(String msg){
        super(msg);
    }
    
}