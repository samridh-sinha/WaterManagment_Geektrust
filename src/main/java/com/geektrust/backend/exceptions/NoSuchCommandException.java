package com.geektrust.backend.exceptions;

public class NoSuchCommandException extends RuntimeException {
    
    private static final long serialVersionUID = 2592740408491304878L; 

    
    public NoSuchCommandException()
    {
     super();
    }
    public NoSuchCommandException(String msg)
    {
     super(msg);
    }
}