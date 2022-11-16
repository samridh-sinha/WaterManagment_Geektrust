package com.geektrust.backend.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TenantsTest {
    @Test 
    public void apartmentTypeTwoBedrromTest(){
        assertEquals(Tenants.TWO_BEDROOM_TENANTS.getTenants(),3); 

    } 

    @Test 
    public void apartmentTypeThreeBedroomTest(){
        assertEquals(Tenants.THREE_BEDROOM_TENANTS.getTenants(),5);
    }



}
