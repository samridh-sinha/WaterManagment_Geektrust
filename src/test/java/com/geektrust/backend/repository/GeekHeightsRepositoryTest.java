package com.geektrust.backend.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.geektrust.backend.entities.GeekHeights;

import org.junit.jupiter.api.*;


public class GeekHeightsRepositoryTest {

    private GeekHeightsRepository geekHeightsRepository;

    @BeforeEach
    void setup(){ 
        final Map<String,GeekHeights> geekHeightsMap = new HashMap<String,GeekHeights>();
         
        geekHeightsRepository = new GeekHeightsRepository(geekHeightsMap);


    } 
    @Test
    @DisplayName("Save should create and return new GeekHeights object")
    public void save(){ 

        final GeekHeights gh1 = new GeekHeights("1", 3, 2, 1);

        GeekHeights expected = geekHeightsRepository.save(gh1);

        Assertions.assertEquals(expected, gh1);

    } 
    @Test 
    @DisplayName("Find all should return list if size 1")
    public void finalAll(){ 

        int expectedCount = 1; 
        final GeekHeights gh1 = new GeekHeights("1", 3, 2, 1);  
         geekHeightsRepository.save(gh1);  

        List<GeekHeights> list = geekHeightsRepository.findAll(); 
        
        Assertions.assertEquals(expectedCount, list.size()); 
    } 

    @Test 
    @DisplayName("Delete Geekheights object in repository")
    public void deleteCheck(){

        final GeekHeights gh1 = new GeekHeights("1", 3, 2, 1);   
         geekHeightsRepository.save(gh1); 
        GeekHeights geekHeights = geekHeightsRepository.findAll().get(0); 
        geekHeightsRepository.delete(geekHeights); 
        int expectedCount = 0; 
        List<GeekHeights> list = geekHeightsRepository.findAll();
        Assertions.assertEquals(expectedCount, list.size());

        
        
    }
}
