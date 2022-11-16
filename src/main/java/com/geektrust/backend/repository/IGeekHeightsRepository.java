package com.geektrust.backend.repository;

import java.util.List;

import com.geektrust.backend.entities.GeekHeights;

public interface IGeekHeightsRepository { 

    public GeekHeights save(GeekHeights entity);
    public List<GeekHeights> findAll(); 
    public void delete(GeekHeights entity);
    
}
