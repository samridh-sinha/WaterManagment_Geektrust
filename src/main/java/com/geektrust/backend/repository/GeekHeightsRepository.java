package com.geektrust.backend.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.geektrust.backend.entities.GeekHeights;

public class GeekHeightsRepository implements IGeekHeightsRepository{

    private final Map<String,GeekHeights> geekHeightsMap;
    private Integer autoIncrement = 0; 


    public GeekHeightsRepository(){

        this.geekHeightsMap = new HashMap<String,GeekHeights>();
    } 

    public GeekHeightsRepository(Map<String, GeekHeights> geekHeightsMap) {
        this.geekHeightsMap = geekHeightsMap;
        this.autoIncrement = geekHeightsMap.size();
    }
    
    @Override
    public GeekHeights save(GeekHeights entity) {
        if( entity.getId() == null ){
            autoIncrement++;
            GeekHeights geekHeights = new GeekHeights(Integer.toString(autoIncrement), entity.getApartmentType(), entity.getCorporationRatio(), entity.getBoreWellratio());
            geekHeightsMap.put(geekHeights.getId(),geekHeights);
            return geekHeights;
        }
        geekHeightsMap.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public List<GeekHeights> findAll() {
        return geekHeightsMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void delete(GeekHeights entity) {
        geekHeightsMap.remove(entity.getId()); 

        
    }
    
}
